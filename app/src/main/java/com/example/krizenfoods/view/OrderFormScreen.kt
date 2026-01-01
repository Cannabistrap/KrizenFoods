//
//
//package com.example.krizenfoods.view
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.krizenfoods.model.Order
//import com.example.krizenfoods.model.OrderItem
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//import com.google.firebase.database.FirebaseDatabase
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun OrderFormScreen(
//    viewModel: DashboardViewModel,
//    onNavigateBack: () -> Unit,
//    onOrderSuccess: () -> Unit
//) {
//    var phoneNumber by remember { mutableStateOf("") }
//    var location by remember { mutableStateOf("") }
//    var remarks by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf("") }
//    var isSubmitting by remember { mutableStateOf(false) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Order Details",
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = onNavigateBack) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Back",
//                            tint = Color.White
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFF57C00)
//                )
//            )
//        }
//    ) { paddingValues ->
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .verticalScroll(rememberScrollState())
//                .padding(16.dp)
//        ) {
//            // Order Summary Card
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(16.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFFFFF3E0)
//                )
//            ) {
//                Column(
//                    modifier = Modifier.padding(20.dp)
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(
//                            text = "Order Summary",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black
//                        )
//                        Text(
//                            text = "${viewModel.cartItemCount} items",
//                            fontSize = 14.sp,
//                            color = Color.Gray
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Divider(color = Color.LightGray)
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = "Total Amount:",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black
//                        )
//                        Text(
//                            text = "NPR ${viewModel.cartTotal.toInt()}",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color(0xFFF57C00)
//                        )
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Delivery Information
//            Text(
//                text = "Delivery Information",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Phone Number Field
//            OutlinedTextField(
//                value = phoneNumber,
//                onValueChange = { phoneNumber = it },
//                label = { Text("Phone Number") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Phone,
//                        contentDescription = null,
//                        tint = Color(0xFFF57C00)
//                    )
//                },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color(0xFFF57C00),
//                    unfocusedBorderColor = Color.LightGray,
//                    focusedLabelColor = Color(0xFFF57C00)
//                ),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Location Field
//            OutlinedTextField(
//                value = location,
//                onValueChange = { location = it },
//                label = { Text("Delivery Location") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.LocationOn,
//                        contentDescription = null,
//                        tint = Color(0xFFF57C00)
//                    )
//                },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color(0xFFF57C00),
//                    unfocusedBorderColor = Color.LightGray,
//                    focusedLabelColor = Color(0xFFF57C00)
//                ),
//                minLines = 2,
//                maxLines = 3
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Remarks Field (Optional)
//            OutlinedTextField(
//                value = remarks,
//                onValueChange = { remarks = it },
//                label = { Text("Special Instructions (Optional)") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Edit,
//                        contentDescription = null,
//                        tint = Color(0xFFF57C00)
//                    )
//                },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color(0xFFF57C00),
//                    unfocusedBorderColor = Color.LightGray,
//                    focusedLabelColor = Color(0xFFF57C00)
//                ),
//                minLines = 3,
//                maxLines = 5
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Error Message
//            if (errorMessage.isNotEmpty()) {
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFFFFEBEE)
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Row(
//                        modifier = Modifier.padding(12.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Info,
//                            contentDescription = null,
//                            tint = Color(0xFFD32F2F),
//                            modifier = Modifier.size(20.dp)
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(
//                            text = errorMessage,
//                            color = Color(0xFFD32F2F),
//                            fontSize = 14.sp
//                        )
//                    }
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Place Order Button
//            Button(
//                onClick = {
//                    // Validation
//                    when {
//                        phoneNumber.isBlank() -> {
//                            errorMessage = "Please enter your phone number"
//                        }
//                        phoneNumber.length < 10 -> {
//                            errorMessage = "Please enter a valid phone number"
//                        }
//                        location.isBlank() -> {
//                            errorMessage = "Please enter your delivery location"
//                        }
//                        else -> {
//                            errorMessage = ""
//                            isSubmitting = true
//
//                            // Submit Order
//                            submitOrder(
//                                viewModel = viewModel,
//                                phoneNumber = phoneNumber,
//                                location = location,
//                                remarks = remarks,
//                                onSuccess = {
//                                    isSubmitting = false
//                                    viewModel.clearCart()
//                                    onOrderSuccess()
//                                },
//                                onError = { error ->
//                                    isSubmitting = false
//                                    errorMessage = error
//                                }
//                            )
//                        }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFF57C00)
//                ),
//                shape = RoundedCornerShape(12.dp),
//                enabled = !isSubmitting
//            ) {
//                if (isSubmitting) {
//                    CircularProgressIndicator(
//                        color = Color.White,
//                        modifier = Modifier.size(24.dp),
//                        strokeWidth = 2.dp
//                    )
//                } else {
//                    Icon(
//                        imageVector = Icons.Default.Check,
//                        contentDescription = null,
//                        tint = Color.White
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Place Order",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//        }
//    }
//}
//
//private fun submitOrder(
//    viewModel: DashboardViewModel,
//    phoneNumber: String,
//    location: String,
//    remarks: String,
//    onSuccess: () -> Unit,
//    onError: (String) -> Unit
//) {
//    val database = FirebaseDatabase.getInstance().reference
//    val orderId = "order_${System.currentTimeMillis()}"
//    val currentTime = System.currentTimeMillis()
//
//    // Convert cart items to order items
//    val orderItems = viewModel.cartItems.map { cartItem ->
//        OrderItem(
//            foodId = cartItem.food.id,
//            foodName = cartItem.food.name,
//            price = cartItem.food.price,
//            quantity = cartItem.quantity,
//            subtotal = cartItem.totalPrice
//        )
//    }
//
//    // Create order object
//    val order = Order(
//        orderId = orderId,
//        userId = viewModel.userId,
//        userName = viewModel.userName,
//        userEmail = viewModel.userEmail,
//        phoneNumber = phoneNumber,
//        location = location,
//        remarks = remarks,
//        items = orderItems,
//        totalAmount = viewModel.cartTotal,
//        status = "pending",
//        orderDate = currentTime,
//        updatedAt = currentTime
//    )
//
//    // Save to Firebase
//    database.child("orders").child(orderId).setValue(order)
//        .addOnSuccessListener {
//            // Create notification for order placed
//            createOrderNotification(
//                database = database,
//                userId = viewModel.userId,
//                orderId = orderId,
//                type = "order_placed"
//            )
//            onSuccess()
//        }
//        .addOnFailureListener { error ->
//            onError(error.message ?: "Failed to place order")
//        }
//}
//
//private fun createOrderNotification(
//    database: com.google.firebase.database.DatabaseReference,
//    userId: String,
//    orderId: String,
//    type: String
//) {
//    val notificationId = "notif_${System.currentTimeMillis()}"
//    val (title, message) = when (type) {
//        "order_placed" -> Pair(
//            "Order Placed Successfully! 🎉",
//            "Your order has been placed. Order ID: $orderId"
//        )
//        "order_confirmed" -> Pair(
//            "Order Confirmed ✅",
//            "Your order has been confirmed and is being prepared!"
//        )
//        "order_completed" -> Pair(
//            "Order Completed 🎊",
//            "Your order has been completed. Enjoy your meal!"
//        )
//        else -> Pair("Order Update", "Your order status has been updated")
//    }
//
//    val notification = com.example.krizenfoods.model.Notification(
//        notificationId = notificationId,
//        userId = userId,
//        orderId = orderId,
//        title = title,
//        message = message,
//        type = type,
//        timestamp = System.currentTimeMillis(),
//        isRead = false
//    )
//
//    database.child("notifications").child(userId).child(notificationId)
//        .setValue(notification)
//}


package com.example.krizenfoods.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.krizenfoods.helpers.NotificationHelper
import com.example.krizenfoods.model.Order
import com.example.krizenfoods.model.OrderItem
import com.example.krizenfoods.viewmodel.DashboardViewModel
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderFormScreen(
    viewModel: DashboardViewModel,
    onNavigateBack: () -> Unit,
    onOrderSuccess: () -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var remarks by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Order Details",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF57C00)
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Order Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF3E0)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Order Summary",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "${viewModel.cartItemCount} items",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    HorizontalDivider(color = Color.LightGray)

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Amount:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "NPR ${viewModel.cartTotal.toInt()}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF57C00)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Delivery Information
            Text(
                text = "Delivery Information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Phone Number Field
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = Color(0xFFF57C00)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF57C00),
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color(0xFFF57C00)
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Location Field
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Delivery Location") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFFF57C00)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF57C00),
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color(0xFFF57C00)
                ),
                minLines = 2,
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Remarks Field (Optional)
            OutlinedTextField(
                value = remarks,
                onValueChange = { remarks = it },
                label = { Text("Special Instructions (Optional)") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color(0xFFF57C00)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF57C00),
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color(0xFFF57C00)
                ),
                minLines = 3,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Error Message
            if (errorMessage.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFEBEE)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFFD32F2F),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = errorMessage,
                            color = Color(0xFFD32F2F),
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Place Order Button
            Button(
                onClick = {
                    // Validation
                    when {
                        phoneNumber.isBlank() -> {
                            errorMessage = "Please enter your phone number"
                        }
                        phoneNumber.length < 10 -> {
                            errorMessage = "Please enter a valid phone number"
                        }
                        location.isBlank() -> {
                            errorMessage = "Please enter your delivery location"
                        }
                        else -> {
                            errorMessage = ""
                            isSubmitting = true

                            // Submit Order
                            submitOrder(
                                viewModel = viewModel,
                                phoneNumber = phoneNumber,
                                location = location,
                                remarks = remarks,
                                onSuccess = {
                                    isSubmitting = false
                                    viewModel.clearCart()
                                    onOrderSuccess()
                                },
                                onError = { error ->
                                    isSubmitting = false
                                    errorMessage = error
                                }
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF57C00)
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = !isSubmitting
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Place Order",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

private fun submitOrder(
    viewModel: DashboardViewModel,
    phoneNumber: String,
    location: String,
    remarks: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val database = FirebaseDatabase.getInstance().reference
    val orderId = "order_${System.currentTimeMillis()}"
    val currentTime = System.currentTimeMillis()

    // Convert cart items to order items
    val orderItems = viewModel.cartItems.map { cartItem ->
        OrderItem(
            foodId = cartItem.food.id,
            foodName = cartItem.food.name,
            price = cartItem.food.price,
            quantity = cartItem.quantity,
            subtotal = cartItem.totalPrice
        )
    }

    // Create order object
    val order = Order(
        orderId = orderId,
        userId = viewModel.userId,
        userName = viewModel.userName,
        userEmail = viewModel.userEmail,
        phoneNumber = phoneNumber,
        location = location,
        remarks = remarks,
        items = orderItems,
        totalAmount = viewModel.cartTotal,
        status = "pending",
        orderDate = currentTime,
        updatedAt = currentTime
    )

    // Save to Firebase
    database.child("orders").child(orderId).setValue(order)
        .addOnSuccessListener {
            // ✅ Use NotificationHelper to send notification
            NotificationHelper.sendNotification(
                database = database,
                userId = viewModel.userId,
                orderId = orderId,
                type = "order_placed",
                onSuccess = { onSuccess() },
                onError = onError
            )
        }
        .addOnFailureListener { error ->
            onError(error.message ?: "Failed to place order")
        }
}