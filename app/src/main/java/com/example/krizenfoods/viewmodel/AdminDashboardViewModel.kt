
package com.example.krizenfoods.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.krizenfoods.helpers.NotificationHelper
import com.example.krizenfoods.model.Food
import com.example.krizenfoods.model.Order
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.krizenfoods.controllers.AuthController

class AdminDashboardViewModel : ViewModel() {

    var totalUsers by mutableStateOf("0")
    var totalOrders by mutableStateOf("0")
    var isLoading by mutableStateOf(true)
    var errorMessage by mutableStateOf("")
    var successMessage by mutableStateOf("")
    var deletingFoodId by mutableStateOf<String?>(null)

    // 📦 ORDER MANAGEMENT STATES
    var allOrders by mutableStateOf<List<Order>>(emptyList())
        private set

    var pendingOrders by mutableStateOf<List<Order>>(emptyList())
        private set

    var confirmedOrders by mutableStateOf<List<Order>>(emptyList())
        private set

    var deliveredOrders by mutableStateOf<List<Order>>(emptyList())
        private set

    var rejectedOrders by mutableStateOf<List<Order>>(emptyList())
        private set

    var isOrdersLoading by mutableStateOf(true)
    var processingOrderId by mutableStateOf<String?>(null)

    private val database = FirebaseDatabase.getInstance().reference

    private val authController = AuthController()

    init {
        loadAdminData()
        loadOrders()
    }

    private fun loadAdminData() {
        // Fetch total users count
        database.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val count = snapshot.childrenCount
                totalUsers = count.toString()
                isLoading = false
            }

            override fun onCancelled(error: DatabaseError) {
                totalUsers = "0"
                isLoading = false
            }
        })

        // Fetch total orders count
        database.child("orders").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val count = snapshot.childrenCount
                totalOrders = count.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                totalOrders = "0"
            }
        })
    }

    // 📦 LOAD ALL ORDERS
    private fun loadOrders() {
        isOrdersLoading = true

        database.child("orders")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val orders = mutableListOf<Order>()

                    for (orderSnapshot in snapshot.children) {
                        val order = orderSnapshot.getValue(Order::class.java)
                        if (order != null) {
                            orders.add(order)
                        }
                    }

                    // Sort by date (newest first)
                    allOrders = orders.sortedByDescending { it.orderDate }

                    // Group by status
                    pendingOrders = orders.filter { it.status == "pending" }
                        .sortedByDescending { it.orderDate }

                    confirmedOrders = orders.filter { it.status == "confirmed" }
                        .sortedByDescending { it.orderDate }

                    deliveredOrders = orders.filter { it.status == "delivered" }
                        .sortedByDescending { it.orderDate }

                    rejectedOrders = orders.filter { it.status == "rejected" }
                        .sortedByDescending { it.orderDate }

                    isOrdersLoading = false

                    Log.d("AdminVM", "📦 Loaded ${orders.size} orders")
                    Log.d("AdminVM", "⏳ Pending: ${pendingOrders.size}")
                    Log.d("AdminVM", "✅ Confirmed: ${confirmedOrders.size}")
                    Log.d("AdminVM", "🎊 Delivered: ${deliveredOrders.size}")
                    Log.d("AdminVM", "❌ Rejected: ${rejectedOrders.size}")
                }

                override fun onCancelled(error: DatabaseError) {
                    isOrdersLoading = false
                    Log.e("AdminVM", "❌ Error loading orders: ${error.message}")
                }
            })
    }

    // ✅ CONFIRM ORDER
    fun confirmOrder(order: Order) {
        processingOrderId = order.orderId

        val updates = mapOf(
            "status" to "confirmed",
            "updatedAt" to System.currentTimeMillis()
        )

        database.child("orders")
            .child(order.orderId)
            .updateChildren(updates)
            .addOnSuccessListener {
                // Send notification to user
                NotificationHelper.sendNotification(
                    database = database,
                    userId = order.userId,
                    orderId = order.orderId,
                    type = "order_confirmed",
                    onSuccess = {
                        processingOrderId = null
                        successMessage = "✅ Order confirmed!"
                        clearMessageAfterDelay()
                        Log.d("AdminVM", "✅ Order ${order.orderId} confirmed")
                    },
                    onError = { error ->
                        processingOrderId = null
                        errorMessage = error
                        clearMessageAfterDelay()
                    }
                )
            }
            .addOnFailureListener { error ->
                processingOrderId = null
                errorMessage = "❌ Failed to confirm: ${error.message}"
                clearMessageAfterDelay()
            }
    }

    // ❌ REJECT ORDER
    fun rejectOrder(order: Order) {
        processingOrderId = order.orderId

        val updates = mapOf(
            "status" to "rejected",
            "updatedAt" to System.currentTimeMillis()
        )

        database.child("orders")
            .child(order.orderId)
            .updateChildren(updates)
            .addOnSuccessListener {
                // Send notification to user
                NotificationHelper.sendNotification(
                    database = database,
                    userId = order.userId,
                    orderId = order.orderId,
                    type = "order_rejected",
                    onSuccess = {
                        processingOrderId = null
                        successMessage = "Order rejected"
                        clearMessageAfterDelay()
                        Log.d("AdminVM", "❌ Order ${order.orderId} rejected")
                    },
                    onError = { error ->
                        processingOrderId = null
                        errorMessage = error
                        clearMessageAfterDelay()
                    }
                )
            }
            .addOnFailureListener { error ->
                processingOrderId = null
                errorMessage = "❌ Failed to reject: ${error.message}"
                clearMessageAfterDelay()
            }
    }

    // 🎊 MARK AS DELIVERED
    fun markAsDelivered(order: Order) {
        processingOrderId = order.orderId

        val updates = mapOf(
            "status" to "delivered",
            "updatedAt" to System.currentTimeMillis()
        )

        database.child("orders")
            .child(order.orderId)
            .updateChildren(updates)
            .addOnSuccessListener {
                // Send notification to user
                NotificationHelper.sendNotification(
                    database = database,
                    userId = order.userId,
                    orderId = order.orderId,
                    type = "order_delivered",
                    onSuccess = {
                        processingOrderId = null
                        successMessage = "🎊 Marked as delivered!"
                        clearMessageAfterDelay()
                        Log.d("AdminVM", "🎊 Order ${order.orderId} delivered")
                    },
                    onError = { error ->
                        processingOrderId = null
                        errorMessage = error
                        clearMessageAfterDelay()
                    }
                )
            }
            .addOnFailureListener { error ->
                processingOrderId = null
                errorMessage = "❌ Failed to update: ${error.message}"
                clearMessageAfterDelay()
            }
    }

    // Add Food Function
    fun addFood(
        name: String,
        description: String,
        price: String,
        category: String,
        imageUrl: String,
        isAvailable: Boolean,
        onSuccess: () -> Unit
    ) {
        errorMessage = ""
        successMessage = ""

        if (name.isBlank()) {
            errorMessage = "Please enter food name"
            return
        }

        if (description.isBlank()) {
            errorMessage = "Please enter description"
            return
        }

        if (price.isBlank()) {
            errorMessage = "Please enter price"
            return
        }

        val priceValue = price.toDoubleOrNull()
        if (priceValue == null || priceValue <= 0) {
            errorMessage = "Please enter a valid price"
            return
        }

        if (imageUrl.isBlank()) {
            errorMessage = "Please enter image URL"
            return
        }

        isLoading = true

        val foodId = database.child("foods").push().key ?: return

        val food = Food(
            id = foodId,
            name = name,
            description = description,
            price = priceValue,
            category = category,
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        database.child("foods").child(foodId).setValue(food)
            .addOnSuccessListener {
                isLoading = false
                successMessage = "✅ Food item added successfully!"
                onSuccess()
            }
            .addOnFailureListener { error ->
                isLoading = false
                errorMessage = "Failed: ${error.message}"
            }
    }

    // Delete Food Function
    fun deleteFood(foodId: String) {
        deletingFoodId = foodId

        database.child("foods").child(foodId).removeValue()
            .addOnSuccessListener {
                deletingFoodId = null
                successMessage = "✅ Food item deleted successfully!"
                clearMessageAfterDelay()
            }
            .addOnFailureListener { error ->
                deletingFoodId = null
                errorMessage = "❌ Failed to delete: ${error.message}"
                clearMessageAfterDelay()
            }
    }

    // Helper to clear messages after delay
    private fun clearMessageAfterDelay() {
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            successMessage = ""
            errorMessage = ""
        }, 3000)
    }

    fun logout(onSuccess: () -> Unit) {
        authController.logout()
        LoginViewModel.isHardcodedAdmin = false // ✅ RESET BYPASS

        // Clear ALL local data
        totalUsers = "0"
        totalOrders = "0"
        allOrders = emptyList()
        pendingOrders = emptyList()
        confirmedOrders = emptyList()
        deliveredOrders = emptyList()
        rejectedOrders = emptyList()

        onSuccess()
    }
}
