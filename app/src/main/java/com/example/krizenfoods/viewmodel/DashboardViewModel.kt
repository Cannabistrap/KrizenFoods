
package com.example.krizenfoods.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.krizenfoods.controllers.AuthController
import com.example.krizenfoods.model.CartItem
import com.example.krizenfoods.model.Food
import com.example.krizenfoods.model.Notification
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    var userName by mutableStateOf("User")
    var userEmail by mutableStateOf("")
    var userId by mutableStateOf("")
    var isLoading by mutableStateOf(true)
    var isAdmin by mutableStateOf(false)

    // Food related states
    var allFoods by mutableStateOf<List<Food>>(emptyList())
    var foodsByCategory by mutableStateOf<Map<String, List<Food>>>(emptyMap())
    var isFoodsLoading by mutableStateOf(true)

    // 🛒 CART RELATED STATES
    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
        private set

    val cartTotal: Double
        get() = cartItems.sumOf { it.totalPrice }

    val cartItemCount: Int
        get() = cartItems.sumOf { it.quantity }

    // 🔔 NOTIFICATION STATES
    var notifications by mutableStateOf<List<Notification>>(emptyList())
        private set

    val unreadNotificationCount: Int
        get() = notifications.count { !it.isRead }

    private val authController = AuthController()
    private val database = FirebaseDatabase.getInstance().reference

    // 🔹 Single listener for notifications
    private var notificationsListener: ValueEventListener? = null

    // ✅ Flag to prevent listener conflicts
    private var isUpdatingNotifications = false

    init {
        loadUserData()
        loadFoods()
        loadNotifications()
    }

    private fun loadUserData() {
        // ✅ HARDCODED ADMIN BYPASS CHECK
        if (LoginViewModel.isHardcodedAdmin) {
            isAdmin = true
            userName = "Admin (Bypass)"
            userEmail = "admin@gmail.com"
            userId = "admin_bypass_id"
            isLoading = false
            return
        }

        val currentUser = authController.getCurrentUser()
        if (currentUser != null) {
            userId = currentUser.uid
            userEmail = currentUser.email ?: ""
            isAdmin = userEmail == "admin@gmail.com"

            database.child("users").child(userId).child("fullName")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val fullName = snapshot.getValue(String::class.java)
                        userName = fullName ?: userEmail.substringBefore("@").replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase() else it.toString()
                        }
                        isLoading = false
                    }

                    override fun onCancelled(error: DatabaseError) {
                        userName = userEmail.substringBefore("@").replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase() else it.toString()
                        }
                        isLoading = false
                    }
                })
        } else {
            isLoading = false
        }
    }

    private fun loadFoods() {
        isFoodsLoading = true

        database.child("foods").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val foods = mutableListOf<Food>()

                for (foodSnapshot in snapshot.children) {
                    val food = foodSnapshot.getValue(Food::class.java)
                    if (food != null && food.isAvailable) {
                        foods.add(food)
                    }
                }

                allFoods = foods
                foodsByCategory = foods.groupBy { it.category }
                isFoodsLoading = false
            }

            override fun onCancelled(error: DatabaseError) {
                isFoodsLoading = false
            }
        })
    }

    fun searchFoods(query: String): List<Food> {
        if (query.isBlank()) return allFoods

        return allFoods.filter { food ->
            food.name.contains(query, ignoreCase = true) ||
                    food.description.contains(query, ignoreCase = true) ||
                    food.category.contains(query, ignoreCase = true)
        }
    }

    // 🛒 CART FUNCTIONS

    fun addToCart(food: Food) {
        val existingItem = cartItems.find { it.food.id == food.id }

        if (existingItem != null) {
            updateQuantity(food.id, existingItem.quantity + 1)
        } else {
            cartItems = cartItems + CartItem(food, quantity = 1)
        }
    }

    fun removeFromCart(foodId: String) {
        cartItems = cartItems.filter { it.food.id != foodId }
    }

    fun updateQuantity(foodId: String, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeFromCart(foodId)
            return
        }

        cartItems = cartItems.map { item ->
            if (item.food.id == foodId) {
                item.copy(quantity = newQuantity)
            } else {
                item
            }
        }
    }

    fun clearCart() {
        cartItems = emptyList()
    }

    fun isInCart(foodId: String): Boolean {
        return cartItems.any { it.food.id == foodId }
    }

    fun getCartQuantity(foodId: String): Int {
        return cartItems.find { it.food.id == foodId }?.quantity ?: 0
    }

    // 🔔 NOTIFICATION FUNCTIONS

    private fun loadNotifications() {
        if (userId.isEmpty()) return

        // Remove previous listener if exists
        notificationsListener?.let {
            database.child("notifications").child(userId).removeEventListener(it)
        }

        notificationsListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (isUpdatingNotifications) return

                val notifs = mutableListOf<Notification>()
                for (notifSnapshot in snapshot.children) {
                    val notification = notifSnapshot.getValue(Notification::class.java)
                    if (notification != null) {
                        notifs.add(notification)
                    }
                }

                notifications = notifs.sortedByDescending { it.timestamp }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DashboardVM", "❌ Error loading notifications: ${error.message}")
            }
        }

        database.child("notifications").child(userId).addValueEventListener(notificationsListener!!)
    }

    fun markNotificationAsRead(notificationId: String) {
        if (userId.isEmpty() || notificationId.isEmpty() || LoginViewModel.isHardcodedAdmin) return

        notifications = notifications.map { notif ->
            if (notif.notificationId == notificationId) notif.copy(isRead = true)
            else notif
        }

        database.child("notifications")
            .child(userId)
            .child(notificationId)
            .child("isRead")
            .setValue(true)
    }

    fun markAllNotificationsAsRead() {
        if (userId.isEmpty() || LoginViewModel.isHardcodedAdmin) return

        val unreadList = notifications.filter { !it.isRead }
        if (unreadList.isEmpty()) return

        notifications = notifications.map { it.copy(isRead = true) }
        isUpdatingNotifications = true

        viewModelScope.launch {
            try {
                val updates = mutableMapOf<String, Any>()
                unreadList.forEach { notification ->
                    updates["notifications/$userId/${notification.notificationId}/isRead"] = true
                }

                database.updateChildren(updates)
                    .addOnSuccessListener {
                        viewModelScope.launch {
                            delay(1500)
                            isUpdatingNotifications = false
                        }
                    }
                    .addOnFailureListener {
                        isUpdatingNotifications = false
                    }
            } catch (e: Exception) {
                isUpdatingNotifications = false
            }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        notificationsListener?.let {
            database.child("notifications").child(userId).removeEventListener(it)
        }

        authController.logout()
        LoginViewModel.isHardcodedAdmin = false // ✅ RESET BYPASS

        // Clear ALL local data
        cartItems = emptyList()
        notifications = emptyList()
        userName = "User"
        userEmail = ""
        userId = ""
        isAdmin = false
        allFoods = emptyList()
        foodsByCategory = emptyMap()
        notificationsListener = null
        isUpdatingNotifications = false

        onSuccess()
    }
}
