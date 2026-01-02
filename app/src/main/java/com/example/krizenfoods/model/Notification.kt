package com.example.krizenfoods.model



data class Notification(
    val notificationId: String = "",
    val userId: String = "",
    val orderId: String = "",
    val title: String = "",
    val message: String = "",
    val type: String = "", // "order_placed", "order_confirmed", "order_completed"
    val timestamp: Long = 0L,
    val isRead: Boolean = false
)
//package com.example.krizenfoods.model
//
//data class Notification(
//    val notificationId: String = "",
//    val userId: String = "",
//    val orderId: String = "",
//    val title: String = "",
//    val message: String = "",
//    val type: String = "", // "order_placed", "order_confirmed", "order_completed"
//    val timestamp: Long = 0L,
//    val read: Boolean = false  // ✅ CHANGED from "isRead" to "read"
//)