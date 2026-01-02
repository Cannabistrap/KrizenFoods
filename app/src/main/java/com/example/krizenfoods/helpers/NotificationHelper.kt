package com.example.krizenfoods.helpers

import com.example.krizenfoods.model.Notification
import com.google.firebase.database.DatabaseReference

object NotificationHelper {

    /**
     * Creates and sends a notification to a user
     */
    fun sendNotification(
        database: DatabaseReference,
        userId: String,
        orderId: String,
        type: String,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        val notificationId = "notif_${System.currentTimeMillis()}"

        val (title, message) = getNotificationContent(type, orderId)

        val notification = Notification(
            notificationId = notificationId,
            userId = userId,
            orderId = orderId,
            title = title,
            message = message,
            type = type,
            timestamp = System.currentTimeMillis(),
            isRead = false
        )

        database.child("notifications")
            .child(userId)
            .child(notificationId)
            .setValue(notification)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { error ->
                onError(error.message ?: "Failed to send notification")
            }
    }

    /**
     * Get notification title and message based on type
     */
    private fun getNotificationContent(type: String, orderId: String): Pair<String, String> {
        return when (type) {
            "order_placed" -> Pair(
                "Order Placed Successfully! 🎉",
                "Your order has been placed. Order ID: $orderId"
            )
            "order_confirmed" -> Pair(
                "Order Confirmed ✅",
                "Your order #$orderId has been confirmed and is being prepared!"
            )
            "order_rejected" -> Pair(
                "Order Rejected ❌",
                "Sorry, your order #$orderId has been rejected. Please contact support for details."
            )
            "order_delivered" -> Pair(
                "Order Delivered 🎊",
                "Your order #$orderId has been delivered! Enjoy your meal!"
            )
            else -> Pair(
                "Order Update",
                "Your order #$orderId status has been updated"
            )
        }
    }
}

//package com.example.krizenfoods.helpers
//
//import com.example.krizenfoods.model.Notification
//import com.google.firebase.database.DatabaseReference
//
//object NotificationHelper {
//
//    /**
//     * Creates and sends a notification to a user
//     */
//    fun sendNotification(
//        database: DatabaseReference,
//        userId: String,
//        orderId: String,
//        type: String,
//        onSuccess: () -> Unit = {},
//        onError: (String) -> Unit = {}
//    ) {
//        val notificationId = "notif_${System.currentTimeMillis()}"
//
//        val (title, message) = getNotificationContent(type, orderId)
//
//        val notification = Notification(
//            notificationId = notificationId,
//            userId = userId,
//            orderId = orderId,
//            title = title,
//            message = message,
//            type = type,
//            timestamp = System.currentTimeMillis(),
//            read = false  // ✅ CHANGED from "isRead" to "read"
//        )
//
//        database.child("notifications")
//            .child(userId)
//            .child(notificationId)
//            .setValue(notification)
//            .addOnSuccessListener { onSuccess() }
//            .addOnFailureListener { error ->
//                onError(error.message ?: "Failed to send notification")
//            }
//    }
//
//    /**
//     * Get notification title and message based on type
//     */
//    private fun getNotificationContent(type: String, orderId: String): Pair<String, String> {
//        return when (type) {
//            "order_placed" -> Pair(
//                "Order Placed Successfully! 🎉",
//                "Your order has been placed. Order ID: $orderId"
//            )
//            "order_confirmed" -> Pair(
//                "Order Confirmed ✅",
//                "Your order #$orderId has been confirmed and is being prepared!"
//            )
//            "order_rejected" -> Pair(
//                "Order Rejected ❌",
//                "Sorry, your order #$orderId has been rejected. Please contact support for details."
//            )
//            "order_delivered" -> Pair(
//                "Order Delivered 🎊",
//                "Your order #$orderId has been delivered! Enjoy your meal!"
//            )
//            else -> Pair(
//                "Order Update",
//                "Your order #$orderId status has been updated"
//            )
//        }
//    }
//}