//
//package com.example.krizenfoods.viewmodel
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.example.krizenfoods.controllers.AuthController
//import com.example.krizenfoods.model.CartItem
//import com.example.krizenfoods.model.Food
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class DashboardViewModel : ViewModel() {
//
//    var userName by mutableStateOf("User")
//    var userEmail by mutableStateOf("")
//    var userId by mutableStateOf("")
//    var isLoading by mutableStateOf(true)
//    var isAdmin by mutableStateOf(false)
//
//    // Food related states
//    var allFoods by mutableStateOf<List<Food>>(emptyList())
//    var foodsByCategory by mutableStateOf<Map<String, List<Food>>>(emptyMap())
//    var isFoodsLoading by mutableStateOf(true)
//
//    // 🛒 CART RELATED STATES
//    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
//        private set
//
//    val cartTotal: Double
//        get() = cartItems.sumOf { it.totalPrice }
//
//    val cartItemCount: Int
//        get() = cartItems.sumOf { it.quantity }
//
//    // 🔔 NOTIFICATION STATES
//    var notifications by mutableStateOf<List<com.example.krizenfoods.model.Notification>>(emptyList())
//        private set
//
//    val unreadNotificationCount: Int
//        get() = notifications.count { !it.isRead }
//
//    private val authController = AuthController()
//    private val database = FirebaseDatabase.getInstance().reference
//
//    init {
//        loadUserData()
//        loadFoods()
//        loadNotifications()
//    }
//
//    private fun loadUserData() {
//        val currentUser = authController.getCurrentUser()
//        if (currentUser != null) {
//            userId = currentUser.uid
//            userEmail = currentUser.email ?: ""
//            isAdmin = userEmail == "admin@gmail.com"
//
//            // Fetch real fullName from Firebase
//            database.child("users").child(userId).child("fullName")
//                .addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        val fullName = snapshot.getValue(String::class.java)
//                        userName = fullName ?: userEmail.substringBefore("@").replaceFirstChar {
//                            if (it.isLowerCase()) it.titlecase() else it.toString()
//                        }
//                        isLoading = false
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        // Fallback to email prefix if fetch fails
//                        userName = userEmail.substringBefore("@").replaceFirstChar {
//                            if (it.isLowerCase()) it.titlecase() else it.toString()
//                        }
//                        isLoading = false
//                    }
//                })
//        } else {
//            isLoading = false
//        }
//    }
//
//    private fun loadFoods() {
//        isFoodsLoading = true
//
//        database.child("foods").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val foods = mutableListOf<Food>()
//
//                for (foodSnapshot in snapshot.children) {
//                    val food = foodSnapshot.getValue(Food::class.java)
//                    if (food != null && food.isAvailable) {
//                        foods.add(food)
//                    }
//                }
//
//                allFoods = foods
//                foodsByCategory = foods.groupBy { it.category }
//                isFoodsLoading = false
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                isFoodsLoading = false
//            }
//        })
//    }
//
//    fun searchFoods(query: String): List<Food> {
//        if (query.isBlank()) return allFoods
//
//        return allFoods.filter { food ->
//            food.name.contains(query, ignoreCase = true) ||
//                    food.description.contains(query, ignoreCase = true) ||
//                    food.category.contains(query, ignoreCase = true)
//        }
//    }
//
//    // 🛒 CART FUNCTIONS
//
//    fun addToCart(food: Food) {
//        val existingItem = cartItems.find { it.food.id == food.id }
//
//        if (existingItem != null) {
//            updateQuantity(food.id, existingItem.quantity + 1)
//        } else {
//            cartItems = cartItems + CartItem(food, quantity = 1)
//        }
//    }
//
//    fun removeFromCart(foodId: String) {
//        cartItems = cartItems.filter { it.food.id != foodId }
//    }
//
//    fun updateQuantity(foodId: String, newQuantity: Int) {
//        if (newQuantity <= 0) {
//            removeFromCart(foodId)
//            return
//        }
//
//        cartItems = cartItems.map { item ->
//            if (item.food.id == foodId) {
//                item.copy(quantity = newQuantity)
//            } else {
//                item
//            }
//        }
//    }
//
//    fun clearCart() {
//        cartItems = emptyList()
//    }
//
//    fun isInCart(foodId: String): Boolean {
//        return cartItems.any { it.food.id == foodId }
//    }
//
//    fun getCartQuantity(foodId: String): Int {
//        return cartItems.find { it.food.id == foodId }?.quantity ?: 0
//    }
//
//    // 🔔 NOTIFICATION FUNCTIONS
//
//    private fun loadNotifications() {
//        if (userId.isEmpty()) return
//
//        database.child("notifications").child(userId)
//            .addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val notifs = mutableListOf<com.example.krizenfoods.model.Notification>()
//
//                    for (notifSnapshot in snapshot.children) {
//                        val notification = notifSnapshot.getValue(com.example.krizenfoods.model.Notification::class.java)
//                        if (notification != null) {
//                            notifs.add(notification)
//                        }
//                    }
//
//                    // Sort by timestamp (newest first)
//                    notifications = notifs.sortedByDescending { it.timestamp }
//                }
//
//                override fun onCancelled(error: DatabaseError) {}
//            })
//    }
//
//    fun markNotificationAsRead(notificationId: String) {
//        database.child("notifications").child(userId).child(notificationId)
//            .child("isRead").setValue(true)
//    }
//
//    fun markAllNotificationsAsRead() {
//        notifications.forEach { notification ->
//            if (!notification.isRead) {
//                markNotificationAsRead(notification.notificationId)
//            }
//        }
//    }
//}

//
//package com.example.krizenfoods.viewmodel
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.example.krizenfoods.controllers.AuthController
//import com.example.krizenfoods.model.CartItem
//import com.example.krizenfoods.model.Food
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class DashboardViewModel : ViewModel() {
//
//    var userName by mutableStateOf("User")
//    var userEmail by mutableStateOf("")
//    var userId by mutableStateOf("")
//    var isLoading by mutableStateOf(true)
//    var isAdmin by mutableStateOf(false)
//
//    // Food related states
//    var allFoods by mutableStateOf<List<Food>>(emptyList())
//    var foodsByCategory by mutableStateOf<Map<String, List<Food>>>(emptyMap())
//    var isFoodsLoading by mutableStateOf(true)
//
//    // 🛒 CART RELATED STATES
//    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
//        private set
//
//    val cartTotal: Double
//        get() = cartItems.sumOf { it.totalPrice }
//
//    val cartItemCount: Int
//        get() = cartItems.sumOf { it.quantity }
//
//    // 🔔 NOTIFICATION STATES
//    var notifications by mutableStateOf<List<com.example.krizenfoods.model.Notification>>(emptyList())
//        private set
//
//    val unreadNotificationCount: Int
//        get() = notifications.count { !it.isRead }
//
//    private val authController = AuthController()
//    private val database = FirebaseDatabase.getInstance().reference
//
//    init {
//        loadUserData()
//        loadFoods()
//        loadNotifications()
//    }
//
//    private fun loadUserData() {
//        val currentUser = authController.getCurrentUser()
//        if (currentUser != null) {
//            userId = currentUser.uid
//            userEmail = currentUser.email ?: ""
//            isAdmin = userEmail == "admin@gmail.com"
//
//            // Fetch real fullName from Firebase
//            database.child("users").child(userId).child("fullName")
//                .addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        val fullName = snapshot.getValue(String::class.java)
//                        userName = fullName ?: userEmail.substringBefore("@").replaceFirstChar {
//                            if (it.isLowerCase()) it.titlecase() else it.toString()
//                        }
//                        isLoading = false
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        // Fallback to email prefix if fetch fails
//                        userName = userEmail.substringBefore("@").replaceFirstChar {
//                            if (it.isLowerCase()) it.titlecase() else it.toString()
//                        }
//                        isLoading = false
//                    }
//                })
//        } else {
//            isLoading = false
//        }
//    }
//
//    private fun loadFoods() {
//        isFoodsLoading = true
//
//        database.child("foods").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val foods = mutableListOf<Food>()
//
//                for (foodSnapshot in snapshot.children) {
//                    val food = foodSnapshot.getValue(Food::class.java)
//                    if (food != null && food.isAvailable) {
//                        foods.add(food)
//                    }
//                }
//
//                allFoods = foods
//                foodsByCategory = foods.groupBy { it.category }
//                isFoodsLoading = false
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                isFoodsLoading = false
//            }
//        })
//    }
//
//    fun searchFoods(query: String): List<Food> {
//        if (query.isBlank()) return allFoods
//
//        return allFoods.filter { food ->
//            food.name.contains(query, ignoreCase = true) ||
//                    food.description.contains(query, ignoreCase = true) ||
//                    food.category.contains(query, ignoreCase = true)
//        }
//    }
//
//    // 🛒 CART FUNCTIONS
//
//    fun addToCart(food: Food) {
//        val existingItem = cartItems.find { it.food.id == food.id }
//
//        if (existingItem != null) {
//            updateQuantity(food.id, existingItem.quantity + 1)
//        } else {
//            cartItems = cartItems + CartItem(food, quantity = 1)
//        }
//    }
//
//    fun removeFromCart(foodId: String) {
//        cartItems = cartItems.filter { it.food.id != foodId }
//    }
//
//    fun updateQuantity(foodId: String, newQuantity: Int) {
//        if (newQuantity <= 0) {
//            removeFromCart(foodId)
//            return
//        }
//
//        cartItems = cartItems.map { item ->
//            if (item.food.id == foodId) {
//                item.copy(quantity = newQuantity)
//            } else {
//                item
//            }
//        }
//    }
//
//    fun clearCart() {
//        cartItems = emptyList()
//    }
//
//    fun isInCart(foodId: String): Boolean {
//        return cartItems.any { it.food.id == foodId }
//    }
//
//    fun getCartQuantity(foodId: String): Int {
//        return cartItems.find { it.food.id == foodId }?.quantity ?: 0
//    }
//
//    // 🔔 NOTIFICATION FUNCTIONS
//
//    private fun loadNotifications() {
//        if (userId.isEmpty()) return
//
//        database.child("notifications").child(userId)
//            .addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val notifs = mutableListOf<com.example.krizenfoods.model.Notification>()
//
//                    for (notifSnapshot in snapshot.children) {
//                        val notification = notifSnapshot.getValue(com.example.krizenfoods.model.Notification::class.java)
//                        if (notification != null) {
//                            notifs.add(notification)
//                        }
//                    }
//
//                    // Sort by timestamp (newest first)
//                    notifications = notifs.sortedByDescending { it.timestamp }
//                }
//
//                override fun onCancelled(error: DatabaseError) {}
//            })
//    }
//
//    fun markNotificationAsRead(notificationId: String) {
//        if (userId.isEmpty() || notificationId.isEmpty()) return
//
//        database.child("notifications").child(userId).child(notificationId)
//            .child("isRead").setValue(true)
//            .addOnSuccessListener {
//                // Successfully marked as read
//            }
//            .addOnFailureListener { error ->
//                // Handle error if needed
//                println("Error marking notification as read: ${error.message}")
//            }
//    }
//
//    fun markAllNotificationsAsRead() {
//        if (userId.isEmpty()) return
//
//        // Get all unread notifications
//        val unreadNotifications = notifications.filter { !it.isRead }
//
//        if (unreadNotifications.isEmpty()) return
//
//        // Create a map of updates for batch operation
//        val updates = mutableMapOf<String, Any>()
//
//        unreadNotifications.forEach { notification ->
//            updates["notifications/$userId/${notification.notificationId}/isRead"] = true
//        }
//
//        // Perform batch update
//        database.updateChildren(updates)
//            .addOnSuccessListener {
//                // Successfully marked all as read
//            }
//            .addOnFailureListener { error ->
//                // Handle error if needed
//                println("Error marking all notifications as read: ${error.message}")
//            }
//    }
//}


package com.example.krizenfoods.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.krizenfoods.controllers.AuthController
import com.example.krizenfoods.model.CartItem
import com.example.krizenfoods.model.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

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
    var notifications by mutableStateOf<List<com.example.krizenfoods.model.Notification>>(emptyList())
        private set

    val unreadNotificationCount: Int
        get() = notifications.count { !it.isRead }

    private val authController = AuthController()
    private val database = FirebaseDatabase.getInstance().reference

    init {
        loadUserData()
        loadFoods()
        loadNotifications()
    }

    private fun loadUserData() {
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

        database.child("notifications").child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val notifs = mutableListOf<com.example.krizenfoods.model.Notification>()

                    for (notifSnapshot in snapshot.children) {
                        val notification = notifSnapshot.getValue(com.example.krizenfoods.model.Notification::class.java)
                        if (notification != null) {
                            notifs.add(notification)
                        }
                    }

                    notifications = notifs.sortedByDescending { it.timestamp }

                    Log.d("DashboardVM", "📨 Loaded ${notifs.size} notifications")
                    Log.d("DashboardVM", "🔴 Unread count: ${notifications.count { !it.isRead }}")
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("DashboardVM", "❌ Error loading notifications: ${error.message}")
                }
            })
    }

    fun markNotificationAsRead(notificationId: String) {
        if (userId.isEmpty() || notificationId.isEmpty()) return

        database.child("notifications")
            .child(userId)
            .child(notificationId)
            .child("isRead")
            .setValue(true)
    }

    // 🎯 THE FIXED VERSION - SYNCHRONOUS APPROACH
    fun markAllNotificationsAsRead() {
        if (userId.isEmpty()) {
            Log.e("DashboardVM", "❌ userId is empty!")
            return
        }

        val unreadList = notifications.filter { !it.isRead }

        if (unreadList.isEmpty()) {
            Log.d("DashboardVM", "ℹ️ No unread notifications")
            return
        }

        Log.d("DashboardVM", "🔵 Marking ${unreadList.size} notifications as read...")
        Log.d("DashboardVM", "🔵 UserId: $userId")

        // Method 1: Direct loop (most reliable)
        var completed = 0
        unreadList.forEach { notification ->
            Log.d("DashboardVM", "→ Processing: ${notification.notificationId}")

            database.child("notifications")
                .child(userId)
                .child(notification.notificationId)
                .child("isRead")
                .setValue(true)
                .addOnSuccessListener {
                    completed++
                    Log.d("DashboardVM", "✅ Success ($completed/${unreadList.size}): ${notification.notificationId}")
                }
                .addOnFailureListener { e ->
                    Log.e("DashboardVM", "❌ Failed: ${notification.notificationId} - ${e.message}")
                }
        }
    }
}