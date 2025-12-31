package com.example.krizenfoods.model



data class Order(
    val orderId: String = "",
    val userId: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val phoneNumber: String = "",
    val location: String = "",
    val remarks: String = "",
    val items: List<OrderItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val status: String = "pending", // pending, confirmed, completed
    val orderDate: Long = 0L,
    val updatedAt: Long = 0L
)

data class OrderItem(
    val foodId: String = "",
    val foodName: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val subtotal: Double = 0.0
)