//package com.example.krizenfoods.view
//
//
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.krizenfoods.model.Notification
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//import java.text.SimpleDateFormat
//import java.util.*
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NotificationScreen(
//    viewModel: DashboardViewModel,
//    onNavigateBack: () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Notifications",
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
//                actions = {
//                    if (viewModel.unreadNotificationCount > 0) {
//                        TextButton(
//                            onClick = { viewModel.markAllNotificationsAsRead() }
//                        ) {
//                            Text(
//                                text = "Mark all read",
//                                color = Color.White,
//                                fontSize = 14.sp
//                            )
//                        }
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFF57C00)
//                )
//            )
//        }
//    ) { paddingValues ->
//
//        if (viewModel.notifications.isEmpty()) {
//            // Empty state
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues),
//                contentAlignment = Alignment.Center
//            ) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Notifications,
//                        contentDescription = null,
//                        tint = Color.LightGray,
//                        modifier = Modifier.size(80.dp)
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(
//                        text = "No Notifications",
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Gray
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "You'll see notifications here",
//                        fontSize = 14.sp,
//                        color = Color.LightGray
//                    )
//                }
//            }
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp)
//            ) {
//                items(viewModel.notifications) { notification ->
//                    NotificationCard(
//                        notification = notification,
//                        onMarkAsRead = {
//                            viewModel.markNotificationAsRead(notification.notificationId)
//                        }
//                    )
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun NotificationCard(
//    notification: Notification,
//    onMarkAsRead: () -> Unit
//) {
//    val backgroundColor = if (notification.isRead) {
//        Color.White
//    } else {
//        Color(0xFFFFF3E0) // Unread highlight
//    }
//
//    val iconColor = when (notification.type) {
//        "order_placed" -> Color(0xFF2196F3)
//        "order_confirmed" -> Color(0xFFFF9800)
//        "order_completed" -> Color(0xFF4CAF50)
//        else -> Color.Gray
//    }
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                if (!notification.isRead) {
//                    onMarkAsRead()
//                }
//            },
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = if (notification.isRead) 2.dp else 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = backgroundColor
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.Top
//        ) {
//            // Icon
//            Box(
//                modifier = Modifier
//                    .size(48.dp)
//                    .background(
//                        color = iconColor.copy(alpha = 0.1f),
//                        shape = CircleShape
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = when (notification.type) {
//                        "order_placed" -> Icons.Default.ShoppingCart
//                        "order_confirmed" -> Icons.Default.Check
//                        "order_completed" -> Icons.Default.CheckCircle
//                        else -> Icons.Default.Notifications
//                    },
//                    contentDescription = null,
//                    tint = iconColor,
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            // Content
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = notification.title,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black,
//                        modifier = Modifier.weight(1f)
//                    )
//
//                    if (!notification.isRead) {
//                        Box(
//                            modifier = Modifier
//                                .size(8.dp)
//                                .background(
//                                    color = Color(0xFFF57C00),
//                                    shape = CircleShape
//                                )
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(4.dp))
//
//                Text(
//                    text = notification.message,
//                    fontSize = 14.sp,
//                    color = Color.Gray,
//                    lineHeight = 20.sp
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = formatTimestamp(notification.timestamp),
//                    fontSize = 12.sp,
//                    color = Color.LightGray
//                )
//            }
//        }
//    }
//}
//
//private fun formatTimestamp(timestamp: Long): String {
//    val now = System.currentTimeMillis()
//    val diff = now - timestamp
//
//    return when {
//        diff < 60000 -> "Just now"
//        diff < 3600000 -> "${diff / 60000} minutes ago"
//        diff < 86400000 -> "${diff / 3600000} hours ago"
//        diff < 604800000 -> "${diff / 86400000} days ago"
//        else -> {
//            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
//            sdf.format(Date(timestamp))
//        }
//    }
//}

//package com.example.krizenfoods.view
//
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.krizenfoods.model.Notification
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//import java.text.SimpleDateFormat
//import java.util.*
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NotificationScreen(
//    viewModel: DashboardViewModel,
//    onNavigateBack: () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Notifications",
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
//                actions = {
//                    if (viewModel.unreadNotificationCount > 0) {
//                        TextButton(
//                            onClick = {
//                                Log.d("NotifScreen", "🔴 BUTTON CLICKED!")
//                                Log.d("NotifScreen", "🔴 Unread count BEFORE: ${viewModel.unreadNotificationCount}")
//
//                                viewModel.markAllNotificationsAsRead()
//
//                                Log.d("NotifScreen", "🔴 Function called")
//                            }
//                        ) {
//                            Text(
//                                text = "Mark all read",
//                                color = Color.White,
//                                fontSize = 14.sp
//                            )
//                        }
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFF57C00)
//                )
//            )
//        }
//    ) { paddingValues ->
//
//        if (viewModel.notifications.isEmpty()) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues),
//                contentAlignment = Alignment.Center
//            ) {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Notifications,
//                        contentDescription = null,
//                        tint = Color.LightGray,
//                        modifier = Modifier.size(80.dp)
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(
//                        text = "No Notifications",
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Gray
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "You'll see notifications here",
//                        fontSize = 14.sp,
//                        color = Color.LightGray
//                    )
//                }
//            }
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp)
//            ) {
//                items(viewModel.notifications) { notification ->
//                    NotificationCard(
//                        notification = notification,
//                        onMarkAsRead = {
//                            if (!notification.isRead) {
//                                viewModel.markNotificationAsRead(notification.notificationId)
//                            }
//                        }
//                    )
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun NotificationCard(
//    notification: Notification,
//    onMarkAsRead: () -> Unit
//) {
//    val backgroundColor = if (notification.isRead) {
//        Color.White
//    } else {
//        Color(0xFFFFF3E0)
//    }
//
//    val iconColor = when (notification.type) {
//        "order_placed" -> Color(0xFF2196F3)
//        "order_confirmed" -> Color(0xFFFF9800)
//        "order_completed" -> Color(0xFF4CAF50)
//        else -> Color.Gray
//    }
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                if (!notification.isRead) {
//                    onMarkAsRead()
//                }
//            },
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = if (notification.isRead) 2.dp else 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = backgroundColor
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.Top
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(48.dp)
//                    .background(
//                        color = iconColor.copy(alpha = 0.1f),
//                        shape = CircleShape
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = when (notification.type) {
//                        "order_placed" -> Icons.Default.ShoppingCart
//                        "order_confirmed" -> Icons.Default.Check
//                        "order_completed" -> Icons.Default.CheckCircle
//                        else -> Icons.Default.Notifications
//                    },
//                    contentDescription = null,
//                    tint = iconColor,
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = notification.title,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black,
//                        modifier = Modifier.weight(1f)
//                    )
//
//                    if (!notification.isRead) {
//                        Box(
//                            modifier = Modifier
//                                .size(8.dp)
//                                .background(
//                                    color = Color(0xFFF57C00),
//                                    shape = CircleShape
//                                )
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(4.dp))
//
//                Text(
//                    text = notification.message,
//                    fontSize = 14.sp,
//                    color = Color.Gray,
//                    lineHeight = 20.sp
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = formatTimestamp(notification.timestamp),
//                    fontSize = 12.sp,
//                    color = Color.LightGray
//                )
//            }
//        }
//    }
//}
//
//private fun formatTimestamp(timestamp: Long): String {
//    val now = System.currentTimeMillis()
//    val diff = now - timestamp
//
//    return when {
//        diff < 60000 -> "Just now"
//        diff < 3600000 -> "${diff / 60000} minutes ago"
//        diff < 86400000 -> "${diff / 3600000} hours ago"
//        diff < 604800000 -> "${diff / 86400000} days ago"
//        else -> {
//            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
//            sdf.format(Date(timestamp))
//        }
//    }
//}

package com.example.krizenfoods.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.krizenfoods.model.Notification
import com.example.krizenfoods.viewmodel.DashboardViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    viewModel: DashboardViewModel,
    onNavigateBack: () -> Unit
) {
    // ✅ AUTO CLEAR - Automatically mark all as read when screen opens
    LaunchedEffect(Unit) {
        Log.d("NotifScreen", "========================================")
        Log.d("NotifScreen", "🟢 SCREEN OPENED")
        Log.d("NotifScreen", "📊 Total notifications: ${viewModel.notifications.size}")
        Log.d("NotifScreen", "🔴 Unread count: ${viewModel.unreadNotificationCount}")
        Log.d("NotifScreen", "👤 UserId: ${viewModel.userId}")

        // Print each notification status
        viewModel.notifications.forEachIndexed { index, notif ->
            Log.d("NotifScreen", "[$index] ${notif.title} - isRead: ${notif.isRead}")
        }

        if (viewModel.unreadNotificationCount > 0) {
            Log.d("NotifScreen", "🔵 AUTO-MARKING all as read...")
            viewModel.markAllNotificationsAsRead()

            // Check again after 2 seconds
            kotlinx.coroutines.delay(2000)
            Log.d("NotifScreen", "⏱️ After 2s - Unread count: ${viewModel.unreadNotificationCount}")
        }
        Log.d("NotifScreen", "========================================")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notifications",
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
        ) {
            // ✅ BIG MANUAL BUTTON - If auto-clear doesn't work, user can click this
            if (viewModel.unreadNotificationCount > 0) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            Log.d("NotifScreen", "🔴 BIG BUTTON CLICKED!")
                            viewModel.markAllNotificationsAsRead()
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF57C00)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Mark All ${viewModel.unreadNotificationCount} as Read",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Notification List
            if (viewModel.notifications.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No Notifications",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "You'll see notifications here",
                            fontSize = 14.sp,
                            color = Color.LightGray
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    items(viewModel.notifications) { notification ->
                        NotificationCard(
                            notification = notification,
                            onMarkAsRead = {
                                if (!notification.isRead) {
                                    Log.d("NotifScreen", "🔘 Marking single: ${notification.notificationId}")
                                    viewModel.markNotificationAsRead(notification.notificationId)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: Notification,
    onMarkAsRead: () -> Unit
) {
    val backgroundColor = if (notification.isRead) {
        Color.White
    } else {
        Color(0xFFFFF3E0)
    }

    val iconColor = when (notification.type) {
        "order_placed" -> Color(0xFF2196F3)
        "order_confirmed" -> Color(0xFFFF9800)
        "order_completed" -> Color(0xFF4CAF50)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (!notification.isRead) {
                    onMarkAsRead()
                }
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (notification.isRead) 2.dp else 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = iconColor.copy(alpha = 0.1f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (notification.type) {
                        "order_placed" -> Icons.Default.ShoppingCart
                        "order_confirmed" -> Icons.Default.Check
                        "order_completed" -> Icons.Default.CheckCircle
                        else -> Icons.Default.Notifications
                    },
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = notification.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    if (!notification.isRead) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(
                                    color = Color(0xFFF57C00),
                                    shape = CircleShape
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = notification.message,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = formatTimestamp(notification.timestamp),
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    return when {
        diff < 60000 -> "Just now"
        diff < 3600000 -> "${diff / 60000} minutes ago"
        diff < 86400000 -> "${diff / 3600000} hours ago"
        diff < 604800000 -> "${diff / 86400000} days ago"
        else -> {
            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            sdf.format(Date(timestamp))
        }
    }
}

//package com.example.krizenfoods.view
//
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.krizenfoods.model.Notification
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//import java.text.SimpleDateFormat
//import java.util.*
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NotificationScreen(
//    viewModel: DashboardViewModel,
//    onNavigateBack: () -> Unit
//) {
//    // ✅ AUTO CLEAR - Automatically mark all as read when screen opens
//    LaunchedEffect(Unit) {
//        Log.d("NotifScreen", "========================================")
//        Log.d("NotifScreen", "🟢 SCREEN OPENED")
//        Log.d("NotifScreen", "📊 Total notifications: ${viewModel.notifications.size}")
//        Log.d("NotifScreen", "🔴 Unread count: ${viewModel.unreadNotificationCount}")
//        Log.d("NotifScreen", "👤 UserId: ${viewModel.userId}")
//
//        // Print each notification status
//        viewModel.notifications.forEachIndexed { index, notif ->
//            Log.d("NotifScreen", "[$index] ${notif.title} - read: ${notif.read}")
//        }
//
//        if (viewModel.unreadNotificationCount > 0) {
//            Log.d("NotifScreen", "🔵 AUTO-MARKING all as read...")
//            viewModel.markAllNotificationsAsRead()
//
//            // Check again after 2 seconds
//            kotlinx.coroutines.delay(2000)
//            Log.d("NotifScreen", "⏱️ After 2s - Unread count: ${viewModel.unreadNotificationCount}")
//        }
//        Log.d("NotifScreen", "========================================")
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Notifications",
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
//        ) {
//            // ✅ BIG MANUAL BUTTON - If auto-clear doesn't work, user can click this
//            if (viewModel.unreadNotificationCount > 0) {
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                        .clickable {
//                            Log.d("NotifScreen", "🔴 BIG BUTTON CLICKED!")
//                            viewModel.markAllNotificationsAsRead()
//                        },
//                    shape = RoundedCornerShape(12.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFFF57C00)
//                    ),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.CheckCircle,
//                            contentDescription = null,
//                            tint = Color.White,
//                            modifier = Modifier.size(24.dp)
//                        )
//                        Spacer(modifier = Modifier.width(12.dp))
//                        Text(
//                            text = "Mark All ${viewModel.unreadNotificationCount} as Read",
//                            color = Color.White,
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
//                }
//            }
//
//            // Notification List
//            if (viewModel.notifications.isEmpty()) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Notifications,
//                            contentDescription = null,
//                            tint = Color.LightGray,
//                            modifier = Modifier.size(80.dp)
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        Text(
//                            text = "No Notifications",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Gray
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "You'll see notifications here",
//                            fontSize = 14.sp,
//                            color = Color.LightGray
//                        )
//                    }
//                }
//            } else {
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 16.dp)
//                ) {
//                    items(viewModel.notifications) { notification ->
//                        NotificationCard(
//                            notification = notification,
//                            onMarkAsRead = {
//                                if (!notification.read) {
//                                    Log.d("NotifScreen", "🔘 Marking single: ${notification.notificationId}")
//                                    viewModel.markNotificationAsRead(notification.notificationId)
//                                }
//                            }
//                        )
//                        Spacer(modifier = Modifier.height(12.dp))
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun NotificationCard(
//    notification: Notification,
//    onMarkAsRead: () -> Unit
//) {
//    val backgroundColor = if (notification.read) {
//        Color.White
//    } else {
//        Color(0xFFFFF3E0)
//    }
//
//    val iconColor = when (notification.type) {
//        "order_placed" -> Color(0xFF2196F3)
//        "order_confirmed" -> Color(0xFFFF9800)
//        "order_completed" -> Color(0xFF4CAF50)
//        else -> Color.Gray
//    }
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                if (!notification.read) {
//                    onMarkAsRead()
//                }
//            },
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = if (notification.read) 2.dp else 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = backgroundColor
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.Top
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(48.dp)
//                    .background(
//                        color = iconColor.copy(alpha = 0.1f),
//                        shape = CircleShape
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = when (notification.type) {
//                        "order_placed" -> Icons.Default.ShoppingCart
//                        "order_confirmed" -> Icons.Default.Check
//                        "order_completed" -> Icons.Default.CheckCircle
//                        else -> Icons.Default.Notifications
//                    },
//                    contentDescription = null,
//                    tint = iconColor,
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = notification.title,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black,
//                        modifier = Modifier.weight(1f)
//                    )
//
//                    if (!notification.read) {
//                        Box(
//                            modifier = Modifier
//                                .size(8.dp)
//                                .background(
//                                    color = Color(0xFFF57C00),
//                                    shape = CircleShape
//                                )
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(4.dp))
//
//                Text(
//                    text = notification.message,
//                    fontSize = 14.sp,
//                    color = Color.Gray,
//                    lineHeight = 20.sp
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = formatTimestamp(notification.timestamp),
//                    fontSize = 12.sp,
//                    color = Color.LightGray
//                )
//            }
//        }
//    }
//}
//
//private fun formatTimestamp(timestamp: Long): String {
//    val now = System.currentTimeMillis()
//    val diff = now - timestamp
//
//    return when {
//        diff < 60000 -> "Just now"
//        diff < 3600000 -> "${diff / 60000} minutes ago"
//        diff < 86400000 -> "${diff / 3600000} hours ago"
//        diff < 604800000 -> "${diff / 86400000} days ago"
//        else -> {
//            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
//            sdf.format(Date(timestamp))
//        }
//    }
//}