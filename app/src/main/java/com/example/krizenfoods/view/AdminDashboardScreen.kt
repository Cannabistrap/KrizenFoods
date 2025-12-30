//package com.example.krizenfoods.view
//
//
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AdminDashboardScreen(
//    viewModel: DashboardViewModel = viewModel()
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Column {
//                        Text(
//                            text = "Admin Dashboard",
//                            style = MaterialTheme.typography.titleMedium,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Text(
//                            text = "Krizen Foods Management",
//                            style = MaterialTheme.typography.bodySmall,
//                            color = Color.White.copy(alpha = 0.8f)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF1976D2) // Blue for admin
//                ),
//                actions = {
//                    IconButton(onClick = { /* Add settings */ }) {
//                        Icon(
//                            imageVector = Icons.Default.Settings,
//                            contentDescription = "Settings",
//                            tint = Color.White
//                        )
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//
//        if (viewModel.isLoading) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator(color = Color(0xFF1976D2))
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp)
//            ) {
//                // Admin Welcome Card
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFFE3F2FD) // Light blue
//                    )
//                ) {
//                    Row(
//                        modifier = Modifier.padding(20.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.AccountCircle,
//                            contentDescription = null,
//                            tint = Color(0xFF1976D2),
//                            modifier = Modifier.size(48.dp)
//                        )
//                        Spacer(modifier = Modifier.width(16.dp))
//                        Column {
//                            Text(
//                                text = "👨‍💼 Admin Access",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color(0xFF1976D2)
//                            )
//                            Spacer(modifier = Modifier.height(4.dp))
//                            Text(
//                                text = "Welcome, ${viewModel.userName}",
//                                fontSize = 14.sp,
//                                color = Color.Gray
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Stats Cards
//                Text(
//                    text = "Quick Stats",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    StatCard(
//                        icon = Icons.Default.Person,
//                        title = "Total Users",
//                        value = "248",
//                        modifier = Modifier.weight(1f)
//                    )
//                    StatCard(
//                        icon = Icons.Default.ShoppingCart,
//                        title = "Total Orders",
//                        value = "567",
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Admin Actions
//                Text(
//                    text = "Management",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                AdminActionCard(
//                    icon = Icons.Default.Person,
//                    title = "Manage Users",
//                    description = "View and manage user accounts"
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                AdminActionCard(
//                    icon = Icons.Default.List,
//                    title = "Manage Products",
//                    description = "Add, edit or remove food items"
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                AdminActionCard(
//                    icon = Icons.Default.ShoppingCart,
//                    title = "View Orders",
//                    description = "Track and manage all orders"
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                AdminActionCard(
//                    icon = Icons.Default.Info,
//                    title = "Reports",
//                    description = "View sales and analytics"
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun StatCard(
//    icon: ImageVector,
//    title: String,
//    value: String,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier,
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White
//        )
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = title,
//                tint = Color(0xFF1976D2),
//                modifier = Modifier.size(32.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = value,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Text(
//                text = title,
//                fontSize = 12.sp,
//                color = Color.Gray
//            )
//        }
//    }
//}
//
//@Composable
//fun AdminActionCard(
//    icon: ImageVector,
//    title: String,
//    description: String
//) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = title,
//                tint = Color(0xFF1976D2),
//                modifier = Modifier.size(40.dp)
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = title,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = description,
//                    fontSize = 14.sp,
//                    color = Color.Gray
//                )
//            }
//            Icon(
//                imageVector = Icons.Default.KeyboardArrowRight,
//                contentDescription = "Go",
//                tint = Color.Gray
//            )
//        }
//    }
//}


//package com.example.krizenfoods.view
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//import com.example.krizenfoods.viewmodel.AdminDashboardViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AdminDashboardScreen(
//    dashboardViewModel: DashboardViewModel = viewModel(),
//    adminViewModel: AdminDashboardViewModel = viewModel()
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Column {
//                        Text(
//                            text = "Admin Dashboard",
//                            style = MaterialTheme.typography.titleMedium,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Text(
//                            text = "Krizen Foods Management",
//                            style = MaterialTheme.typography.bodySmall,
//                            color = Color.White.copy(alpha = 0.8f)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF1976D2)
//                ),
//                actions = {
//                    IconButton(onClick = { /* Add settings */ }) {
//                        Icon(
//                            imageVector = Icons.Default.Settings,
//                            contentDescription = "Settings",
//                            tint = Color.White
//                        )
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//
//        if (adminViewModel.isLoading) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator(color = Color(0xFF1976D2))
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp)
//            ) {
//                // Admin Welcome Card
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFFE3F2FD)
//                    )
//                ) {
//                    Row(
//                        modifier = Modifier.padding(20.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.AccountCircle,
//                            contentDescription = null,
//                            tint = Color(0xFF1976D2),
//                            modifier = Modifier.size(48.dp)
//                        )
//                        Spacer(modifier = Modifier.width(16.dp))
//                        Column {
//                            Text(
//                                text = "👨‍💼 Admin Access",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color(0xFF1976D2)
//                            )
//                            Spacer(modifier = Modifier.height(4.dp))
//                            Text(
//                                text = "Welcome, ${dashboardViewModel.userName}",
//                                fontSize = 14.sp,
//                                color = Color.Gray
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Stats Cards
//                Text(
//                    text = "Quick Stats",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    StatCard(
//                        icon = Icons.Default.Person,
//                        title = "Total Users",
//                        value = adminViewModel.totalUsers,
//                        modifier = Modifier.weight(1f)
//                    )
//                    StatCard(
//                        icon = Icons.Default.ShoppingCart,
//                        title = "Total Orders",
//                        value = adminViewModel.totalOrders,
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Admin Actions
//                Text(
//                    text = "Management",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                AdminActionCard(
//                    icon = Icons.Default.Person,
//                    title = "Manage Users",
//                    description = "View and manage user accounts"
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                AdminActionCard(
//                    icon = Icons.Default.List,
//                    title = "Manage Products",
//                    description = "Add, edit or remove food items"
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                AdminActionCard(
//                    icon = Icons.Default.ShoppingCart,
//                    title = "View Orders",
//                    description = "Track and manage all orders"
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                AdminActionCard(
//                    icon = Icons.Default.Info,
//                    title = "Reports",
//                    description = "View sales and analytics"
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun StatCard(
//    icon: ImageVector,
//    title: String,
//    value: String,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier,
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White
//        )
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = title,
//                tint = Color(0xFF1976D2),
//                modifier = Modifier.size(32.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = value,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Text(
//                text = title,
//                fontSize = 12.sp,
//                color = Color.Gray
//            )
//        }
//    }
//}
//
//@Composable
//fun AdminActionCard(
//    icon: ImageVector,
//    title: String,
//    description: String
//) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = title,
//                tint = Color(0xFF1976D2),
//                modifier = Modifier.size(40.dp)
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = title,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = description,
//                    fontSize = 14.sp,
//                    color = Color.Gray
//                )
//            }
//            Icon(
//                imageVector = Icons.Default.KeyboardArrowRight,
//                contentDescription = "Go",
//                tint = Color.Gray
//            )
//        }
//    }
//}


package com.example.krizenfoods.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.krizenfoods.viewmodel.DashboardViewModel
import com.example.krizenfoods.viewmodel.AdminDashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    onNavigateToAddFood: () -> Unit = {},
    dashboardViewModel: DashboardViewModel = viewModel(),
    adminViewModel: AdminDashboardViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Admin Dashboard",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Krizen Foods Management",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2)
                ),
                actions = {
                    IconButton(onClick = { /* Add settings */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        if (adminViewModel.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF1976D2))
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Admin Welcome Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE3F2FD)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            tint = Color(0xFF1976D2),
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "👨‍💼 Admin Access",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Welcome, ${dashboardViewModel.userName}",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Stats Cards
                Text(
                    text = "Quick Stats",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        icon = Icons.Default.Person,
                        title = "Total Users",
                        value = adminViewModel.totalUsers,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        icon = Icons.Default.ShoppingCart,
                        title = "Total Orders",
                        value = adminViewModel.totalOrders,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Admin Actions
                Text(
                    text = "Management",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                AdminActionCard(
                    icon = Icons.Default.Person,
                    title = "Manage Users",
                    description = "View and manage user accounts",
                    onClick = { /* TODO: Navigate to Manage Users */ }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ✅ CLICKABLE Manage Products Card
                AdminActionCard(
                    icon = Icons.Default.Add,
                    title = "Add Food Item",
                    description = "Add new food items to the menu",
                    onClick = onNavigateToAddFood
                )

                Spacer(modifier = Modifier.height(12.dp))

                AdminActionCard(
                    icon = Icons.Default.ShoppingCart,
                    title = "View Orders",
                    description = "Track and manage all orders",
                    onClick = { /* TODO: Navigate to Orders */ }
                )

                Spacer(modifier = Modifier.height(12.dp))

                AdminActionCard(
                    icon = Icons.Default.Info,
                    title = "Reports",
                    description = "View sales and analytics",
                    onClick = { /* TODO: Navigate to Reports */ }
                )
            }
        }
    }
}

@Composable
fun StatCard(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = title,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun AdminActionCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Go",
                tint = Color.Gray
            )
        }
    }
}