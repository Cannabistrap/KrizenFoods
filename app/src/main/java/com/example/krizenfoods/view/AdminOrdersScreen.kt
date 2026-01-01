package com.example.krizenfoods.view



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.krizenfoods.model.Order
import com.example.krizenfoods.viewmodel.AdminDashboardViewModel
import com.example.krizenfoods.viewmodel.DashboardViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminOrdersScreen(
    dashboardViewModel: DashboardViewModel,
    adminViewModel: AdminDashboardViewModel,
    onNavigateToFoods: () -> Unit = {},
    onNavigateToStats: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToAddFood: () -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("pending") }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showRejectDialog by remember { mutableStateOf(false) }
    var showDeliverDialog by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<Order?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Order Management",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Manage Customer Orders",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2)
                ),
                actions = {
                    IconButton(onClick = onNavigateToAddFood) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Food",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Manage Foods") },
                    label = { Text("Foods") },
                    selected = false,
                    onClick = onNavigateToFoods,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1976D2),
                        selectedTextColor = Color(0xFF1976D2),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )
                NavigationBarItem(
                    icon = {
                        if (adminViewModel.pendingOrders.isNotEmpty()) {
                            BadgedBox(
                                badge = {
                                    Badge(
                                        containerColor = Color.Red,
                                        contentColor = Color.White
                                    ) {
                                        Text(
                                            text = adminViewModel.pendingOrders.size.toString(),
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            ) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Orders")
                            }
                        } else {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Orders")
                        }
                    },
                    label = { Text("Orders") },
                    selected = true,
                    onClick = { /* Already on Orders screen */ },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1976D2),
                        selectedTextColor = Color(0xFF1976D2),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Stats") },
                    label = { Text("Stats") },
                    selected = false,
                    onClick = onNavigateToStats,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1976D2),
                        selectedTextColor = Color(0xFF1976D2),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = onNavigateToProfile,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1976D2),
                        selectedTextColor = Color(0xFF1976D2),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Success/Error Message
            if (adminViewModel.successMessage.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E9)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = adminViewModel.successMessage,
                            color = Color(0xFF2E7D32),
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            if (adminViewModel.errorMessage.isNotEmpty()) {
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
                            text = adminViewModel.errorMessage,
                            color = Color(0xFFD32F2F),
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Filter Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedFilter == "pending",
                    onClick = { selectedFilter = "pending" },
                    label = { Text("Pending (${adminViewModel.pendingOrders.size})") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
                FilterChip(
                    selected = selectedFilter == "confirmed",
                    onClick = { selectedFilter = "confirmed" },
                    label = { Text("Confirmed (${adminViewModel.confirmedOrders.size})") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
                FilterChip(
                    selected = selectedFilter == "delivered",
                    onClick = { selectedFilter = "delivered" },
                    label = { Text("Delivered (${adminViewModel.deliveredOrders.size})") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Orders List
            if (adminViewModel.isOrdersLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF1976D2))
                }
            } else {
                val ordersToShow = when (selectedFilter) {
                    "pending" -> adminViewModel.pendingOrders
                    "confirmed" -> adminViewModel.confirmedOrders
                    "delivered" -> adminViewModel.deliveredOrders
                    else -> adminViewModel.pendingOrders
                }

                if (ordersToShow.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                tint = Color.LightGray,
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "No ${selectedFilter} orders",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(ordersToShow) { order ->
                            AdminOrderCard(
                                order = order,
                                isProcessing = adminViewModel.processingOrderId == order.orderId,
                                onConfirmClick = {
                                    selectedOrder = order
                                    showConfirmDialog = true
                                },
                                onRejectClick = {
                                    selectedOrder = order
                                    showRejectDialog = true
                                },
                                onDeliverClick = {
                                    selectedOrder = order
                                    showDeliverDialog = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    // Dialogs
    if (showConfirmDialog && selectedOrder != null) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            icon = { Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF4CAF50)) },
            title = { Text("Confirm Order?", fontWeight = FontWeight.Bold) },
            text = { Text("Confirm order from ${selectedOrder?.userName}? The user will be notified.") },
            confirmButton = {
                Button(
                    onClick = {
                        selectedOrder?.let { adminViewModel.confirmOrder(it) }
                        showConfirmDialog = false
                        selectedOrder = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) { Text("Confirm Order") }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false; selectedOrder = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showRejectDialog && selectedOrder != null) {
        AlertDialog(
            onDismissRequest = { showRejectDialog = false },
            icon = { Icon(Icons.Default.Close, contentDescription = null, tint = Color(0xFFD32F2F)) },
            title = { Text("Reject Order?", fontWeight = FontWeight.Bold) },
            text = { Text("Reject order from ${selectedOrder?.userName}? The user will be notified.") },
            confirmButton = {
                Button(
                    onClick = {
                        selectedOrder?.let { adminViewModel.rejectOrder(it) }
                        showRejectDialog = false
                        selectedOrder = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                ) { Text("Reject Order") }
            },
            dismissButton = {
                TextButton(onClick = { showRejectDialog = false; selectedOrder = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showDeliverDialog && selectedOrder != null) {
        AlertDialog(
            onDismissRequest = { showDeliverDialog = false },
            icon = { Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF1976D2)) },
            title = { Text("Mark as Delivered?", fontWeight = FontWeight.Bold) },
            text = { Text("Mark this order as delivered? The user will be notified.") },
            confirmButton = {
                Button(
                    onClick = {
                        selectedOrder?.let { adminViewModel.markAsDelivered(it) }
                        showDeliverDialog = false
                        selectedOrder = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) { Text("Mark Delivered") }
            },
            dismissButton = {
                TextButton(onClick = { showDeliverDialog = false; selectedOrder = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun AdminOrderCard(
    order: Order,
    isProcessing: Boolean,
    onConfirmClick: () -> Unit,
    onRejectClick: () -> Unit,
    onDeliverClick: () -> Unit
) {
    val statusColor = when (order.status) {
        "pending" -> Color(0xFFFF9800)
        "confirmed" -> Color(0xFF2196F3)
        "delivered" -> Color(0xFF4CAF50)
        "rejected" -> Color(0xFFD32F2F)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(order.userName, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(order.orderId, fontSize = 12.sp, color = Color.Gray)
                }
                Box(
                    modifier = Modifier
                        .background(statusColor.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(order.status.uppercase(), fontSize = 12.sp, fontWeight = FontWeight.Bold, color = statusColor)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = Color.LightGray)
            Spacer(modifier = Modifier.height(12.dp))

            // Contact Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Phone, contentDescription = null, tint = Color(0xFF1976D2), modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(order.phoneNumber, fontSize = 14.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFF1976D2), modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(order.location, fontSize = 14.sp, color = Color.Black, lineHeight = 20.sp)
            }

            if (order.remarks.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = Color(0xFF1976D2), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(order.remarks, fontSize = 14.sp, color = Color.Gray, lineHeight = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = Color.LightGray)
            Spacer(modifier = Modifier.height(12.dp))

            // Order Items
            Text("Order Items:", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))

            order.items.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${item.quantity}x ${item.foodName}", fontSize = 14.sp, color = Color.Black, modifier = Modifier.weight(1f))
                    Text("NPR ${item.subtotal.toInt()}", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.LightGray)
            Spacer(modifier = Modifier.height(8.dp))

            // Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total:", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text("NPR ${order.totalAmount.toInt()}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Ordered: ${formatTimestamp(order.orderDate)}", fontSize = 12.sp, color = Color.Gray)

            // Action Buttons
            if (order.status == "pending") {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onRejectClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                        enabled = !isProcessing
                    ) {
                        if (isProcessing) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                        } else {
                            Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Reject")
                        }
                    }
                    Button(
                        onClick = onConfirmClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                        enabled = !isProcessing
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Confirm")
                    }
                }
            }

            if (order.status == "confirmed") {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onDeliverClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                    enabled = !isProcessing
                ) {
                    if (isProcessing) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                    } else {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mark as Delivered")
                    }
                }
            }
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a", Locale.getDefault())
    return sdf.format(Date(timestamp))
}