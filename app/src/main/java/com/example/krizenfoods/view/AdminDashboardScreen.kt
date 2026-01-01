//
//
//package com.example.krizenfoods.view
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
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
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.krizenfoods.model.Food
//import com.example.krizenfoods.viewmodel.AdminDashboardViewModel
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AdminDashboardScreen(
//    onNavigateToAddFood: () -> Unit = {},
//    dashboardViewModel: DashboardViewModel = viewModel(),
//    adminViewModel: AdminDashboardViewModel = viewModel()
//) {
//    var selectedTab by remember { mutableStateOf(0) }
//    var searchQuery by remember { mutableStateOf("") }
//    var showDeleteDialog by remember { mutableStateOf(false) }
//    var foodToDelete by remember { mutableStateOf<Food?>(null) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Column {
//                        Text(
//                            text = "Admin Dashboard",
//                            style = MaterialTheme.typography.titleLarge,
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
//                    IconButton(onClick = onNavigateToAddFood) {
//                        Icon(
//                            imageVector = Icons.Default.Add,
//                            contentDescription = "Add Food",
//                            tint = Color.White
//                        )
//                    }
//                }
//            )
//        },
//        bottomBar = {
//            NavigationBar(
//                containerColor = Color.White,
//                tonalElevation = 8.dp
//            ) {
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.Home, contentDescription = "Manage Foods") },
//                    label = { Text("Foods") },
//                    selected = selectedTab == 0,
//                    onClick = { selectedTab = 0 },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color(0xFF1976D2),
//                        selectedTextColor = Color(0xFF1976D2),
//                        indicatorColor = Color(0xFFE3F2FD)
//                    )
//                )
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Orders") },
//                    label = { Text("Orders") },
//                    selected = selectedTab == 1,
//                    onClick = { selectedTab = 1 },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color(0xFF1976D2),
//                        selectedTextColor = Color(0xFF1976D2),
//                        indicatorColor = Color(0xFFE3F2FD)
//                    )
//                )
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.Info, contentDescription = "Stats") },
//                    label = { Text("Stats") },
//                    selected = selectedTab == 2,
//                    onClick = { selectedTab = 2 },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color(0xFF1976D2),
//                        selectedTextColor = Color(0xFF1976D2),
//                        indicatorColor = Color(0xFFE3F2FD)
//                    )
//                )
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
//                    label = { Text("Profile") },
//                    selected = selectedTab == 3,
//                    onClick = { selectedTab = 3 },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color(0xFF1976D2),
//                        selectedTextColor = Color(0xFF1976D2),
//                        indicatorColor = Color(0xFFE3F2FD)
//                    )
//                )
//            }
//        }
//    ) { paddingValues ->
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            when (selectedTab) {
//                0 -> AdminFoodsTab(
//                    searchQuery = searchQuery,
//                    onSearchQueryChange = { searchQuery = it },
//                    dashboardViewModel = dashboardViewModel,
//                    adminViewModel = adminViewModel,
//                    onDeleteClick = { food ->
//                        foodToDelete = food
//                        showDeleteDialog = true
//                    }
//                )
//                1 -> AdminOrdersTab(adminViewModel = adminViewModel)
//                2 -> AdminStatsTab(
//                    adminViewModel = adminViewModel,
//                    dashboardViewModel = dashboardViewModel
//                )
//                3 -> AdminProfileTab(dashboardViewModel = dashboardViewModel)
//            }
//        }
//    }
//
//    // Delete Confirmation Dialog
//    if (showDeleteDialog && foodToDelete != null) {
//        AlertDialog(
//            onDismissRequest = { showDeleteDialog = false },
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = null,
//                    tint = Color(0xFFD32F2F)
//                )
//            },
//            title = {
//                Text(
//                    text = "Delete Food Item?",
//                    fontWeight = FontWeight.Bold
//                )
//            },
//            text = {
//                Text(
//                    text = "Are you sure you want to delete \"${foodToDelete?.name}\"? This action cannot be undone."
//                )
//            },
//            confirmButton = {
//                Button(
//                    onClick = {
//                        foodToDelete?.let { food ->
//                            adminViewModel.deleteFood(food.id)
//                        }
//                        showDeleteDialog = false
//                        foodToDelete = null
//                    },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFFD32F2F)
//                    )
//                ) {
//                    Text("Delete")
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = {
//                    showDeleteDialog = false
//                    foodToDelete = null
//                }) {
//                    Text("Cancel")
//                }
//            }
//        )
//    }
//}
//
//@Composable
//fun AdminOrdersTab(adminViewModel: AdminDashboardViewModel) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Icon(
//                imageVector = Icons.Default.ShoppingCart,
//                contentDescription = null,
//                tint = Color(0xFF1976D2),
//                modifier = Modifier.size(80.dp)
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "Orders",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Order management coming soon",
//                fontSize = 14.sp,
//                color = Color.Gray
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AdminFoodsTab(
//    searchQuery: String,
//    onSearchQueryChange: (String) -> Unit,
//    dashboardViewModel: DashboardViewModel,
//    adminViewModel: AdminDashboardViewModel,
//    onDeleteClick: (Food) -> Unit
//) {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        item {
//            OutlinedTextField(
//                value = searchQuery,
//                onValueChange = onSearchQueryChange,
//                modifier = Modifier.fillMaxWidth(),
//                placeholder = { Text("Search for food items...") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search",
//                        tint = Color(0xFF1976D2)
//                    )
//                },
//                trailingIcon = {
//                    if (searchQuery.isNotEmpty()) {
//                        IconButton(onClick = { onSearchQueryChange("") }) {
//                            Icon(
//                                imageVector = Icons.Default.Clear,
//                                contentDescription = "Clear",
//                                tint = Color.Gray
//                            )
//                        }
//                    }
//                },
//                shape = RoundedCornerShape(16.dp),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color(0xFF1976D2),
//                    unfocusedBorderColor = Color.LightGray
//                )
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(24.dp))
//        }
//
//        item {
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(16.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFFE3F2FD)
//                )
//            ) {
//                Row(
//                    modifier = Modifier.padding(20.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.AccountCircle,
//                        contentDescription = null,
//                        tint = Color(0xFF1976D2),
//                        modifier = Modifier.size(48.dp)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Column {
//                        Text(
//                            text = "👨‍💼 Admin Panel",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color(0xFF1976D2)
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "Manage your food menu",
//                            fontSize = 14.sp,
//                            color = Color.Gray
//                        )
//                    }
//                }
//            }
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(24.dp))
//        }
//
//        if (dashboardViewModel.isFoodsLoading) {
//            item {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(color = Color(0xFF1976D2))
//                }
//            }
//        } else if (dashboardViewModel.foodsByCategory.isEmpty()) {
//            item {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(300.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Icon(
//                            imageVector = Icons.Default.ShoppingCart,
//                            contentDescription = null,
//                            tint = Color.LightGray,
//                            modifier = Modifier.size(80.dp)
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        Text(
//                            text = "No food items yet",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Gray
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "Tap + to add your first item",
//                            fontSize = 14.sp,
//                            color = Color.LightGray
//                        )
//                    }
//                }
//            }
//        } else {
//            val foodsToDisplay = if (searchQuery.isNotEmpty()) {
//                dashboardViewModel.searchFoods(searchQuery).groupBy { it.category }
//            } else {
//                dashboardViewModel.foodsByCategory
//            }
//
//            foodsToDisplay.forEach { (category, foods) ->
//                item {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 12.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(
//                            text = category,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black
//                        )
//                        Text(
//                            text = "${foods.size} items",
//                            fontSize = 14.sp,
//                            color = Color.Gray
//                        )
//                    }
//                }
//
//                items(foods) { food ->
//                    AdminFoodItemCard(
//                        food = food,
//                        onDeleteClick = { onDeleteClick(food) },
//                        isDeleting = adminViewModel.deletingFoodId == food.id
//                    )
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
//
//                item {
//                    Spacer(modifier = Modifier.height(8.dp))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun AdminFoodItemCard(
//    food: Food,
//    onDeleteClick: () -> Unit,
//    isDeleting: Boolean
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
//            Card(
//                modifier = Modifier.size(60.dp),
//                shape = RoundedCornerShape(8.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFFE3F2FD)
//                )
//            ) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.ShoppingCart,
//                        contentDescription = null,
//                        tint = Color(0xFF1976D2),
//                        modifier = Modifier.size(32.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = food.name,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = food.description,
//                    fontSize = 14.sp,
//                    color = Color.Gray,
//                    maxLines = 2
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "NPR ${food.price.toInt()}",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF1976D2)
//                    )
//                    Spacer(modifier = Modifier.width(12.dp))
//                    if (!food.isAvailable) {
//                        Card(
//                            colors = CardDefaults.cardColors(
//                                containerColor = Color(0xFFFFEBEE)
//                            ),
//                            shape = RoundedCornerShape(4.dp)
//                        ) {
//                            Text(
//                                text = "Unavailable",
//                                fontSize = 10.sp,
//                                color = Color(0xFFD32F2F),
//                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
//                            )
//                        }
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Button(
//                onClick = onDeleteClick,
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFD32F2F)
//                ),
//                shape = RoundedCornerShape(8.dp),
//                enabled = !isDeleting
//            ) {
//                if (isDeleting) {
//                    CircularProgressIndicator(
//                        color = Color.White,
//                        modifier = Modifier.size(20.dp),
//                        strokeWidth = 2.dp
//                    )
//                } else {
//                    Icon(
//                        imageVector = Icons.Default.Delete,
//                        contentDescription = "Delete",
//                        modifier = Modifier.size(20.dp)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun AdminStatsTab(
//    adminViewModel: AdminDashboardViewModel,
//    dashboardViewModel: DashboardViewModel
//) {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        item {
//            Text(
//                text = "Quick Stats",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//
//        item {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                StatCard(
//                    icon = Icons.Default.Person,
//                    title = "Total Users",
//                    value = adminViewModel.totalUsers,
//                    modifier = Modifier.weight(1f)
//                )
//                StatCard(
//                    icon = Icons.Default.ShoppingCart,
//                    title = "Total Orders",
//                    value = adminViewModel.totalOrders,
//                    modifier = Modifier.weight(1f)
//                )
//            }
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//
//        item {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                StatCard(
//                    icon = Icons.Default.List,
//                    title = "Total Foods",
//                    value = dashboardViewModel.allFoods.size.toString(),
//                    modifier = Modifier.weight(1f)
//                )
//                StatCard(
//                    icon = Icons.Default.CheckCircle,
//                    title = "Available",
//                    value = dashboardViewModel.allFoods.count { it.isAvailable }.toString(),
//                    modifier = Modifier.weight(1f)
//                )
//            }
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(24.dp))
//        }
//
//        item {
//            Text(
//                text = "Category Breakdown",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//
//        items(dashboardViewModel.foodsByCategory.entries.toList()) { (category, foods) ->
//            CategoryStatCard(
//                category = category,
//                count = foods.size
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//    }
//}
//
//@Composable
//fun CategoryStatCard(
//    category: String,
//    count: Int
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
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = category,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color.Black
//            )
//            Text(
//                text = "$count items",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF1976D2)
//            )
//        }
//    }
//}
//
//@Composable
//fun AdminProfileTab(dashboardViewModel: DashboardViewModel) {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        item {
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(16.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFFE3F2FD)
//                )
//            ) {
//                Column(
//                    modifier = Modifier.padding(20.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.AccountCircle,
//                        contentDescription = null,
//                        tint = Color(0xFF1976D2),
//                        modifier = Modifier.size(80.dp)
//                    )
//                    Spacer(modifier = Modifier.height(12.dp))
//                    Text(
//                        text = dashboardViewModel.userName,
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                    Text(
//                        text = dashboardViewModel.userEmail,
//                        fontSize = 14.sp,
//                        color = Color.Gray
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Card(
//                        colors = CardDefaults.cardColors(
//                            containerColor = Color(0xFF1976D2)
//                        ),
//                        shape = RoundedCornerShape(8.dp)
//                    ) {
//                        Text(
//                            text = "👑 ADMIN",
//                            color = Color.White,
//                            fontSize = 12.sp,
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
//                        )
//                    }
//                }
//            }
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(24.dp))
//        }
//
//        item {
//            Text(
//                text = "Admin Settings",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//
//        item {
//            AdminProfileOption(
//                icon = Icons.Default.Settings,
//                title = "App Settings",
//                description = "Configure app preferences"
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//
//        item {
//            AdminProfileOption(
//                icon = Icons.Default.Info,
//                title = "System Info",
//                description = "View system information"
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//
//        item {
//            AdminProfileOption(
//                icon = Icons.Default.ExitToApp,
//                title = "Logout",
//                description = "Sign out of admin account"
//            )
//        }
//    }
//}
//
//@Composable
//fun StatCard(
//    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
//fun AdminProfileOption(
//    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.krizenfoods.model.Food
import com.example.krizenfoods.model.Order
import com.example.krizenfoods.viewmodel.AdminDashboardViewModel
import com.example.krizenfoods.viewmodel.DashboardViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    onNavigateToAddFood: () -> Unit = {},
    dashboardViewModel: DashboardViewModel = viewModel(),
    adminViewModel: AdminDashboardViewModel = viewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var foodToDelete by remember { mutableStateOf<Food?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Admin Dashboard",
                            style = MaterialTheme.typography.titleLarge,
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
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
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
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1976D2),
                        selectedTextColor = Color(0xFF1976D2),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Stats") },
                    label = { Text("Stats") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1976D2),
                        selectedTextColor = Color(0xFF1976D2),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
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
        ) {
            when (selectedTab) {
                0 -> AdminFoodsTab(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    dashboardViewModel = dashboardViewModel,
                    adminViewModel = adminViewModel,
                    onDeleteClick = { food ->
                        foodToDelete = food
                        showDeleteDialog = true
                    }
                )
                1 -> AdminOrdersTab(adminViewModel = adminViewModel)
                2 -> AdminStatsTab(
                    adminViewModel = adminViewModel,
                    dashboardViewModel = dashboardViewModel
                )
                3 -> AdminProfileTab(dashboardViewModel = dashboardViewModel)
            }
        }
    }

    // Delete Confirmation Dialog
    if (showDeleteDialog && foodToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color(0xFFD32F2F)
                )
            },
            title = {
                Text(
                    text = "Delete Food Item?",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to delete \"${foodToDelete?.name}\"? This action cannot be undone."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        foodToDelete?.let { food ->
                            adminViewModel.deleteFood(food.id)
                        }
                        showDeleteDialog = false
                        foodToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F)
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    foodToDelete = null
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}

// ✅ NEW COMPLETE ADMIN ORDERS TAB
@Composable
fun AdminOrdersTab(adminViewModel: AdminDashboardViewModel) {
    var selectedFilter by remember { mutableStateOf("pending") }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showRejectDialog by remember { mutableStateOf(false) }
    var showDeliverDialog by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<Order?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
// THIS IS PART 2 - ADD THIS TO THE SAME FILE AFTER PART 1

@Composable
fun AdminOrderCard(
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

// KEEP ALL THE REMAINING TABS AS THEY WERE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminFoodsTab(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    dashboardViewModel: DashboardViewModel,
    adminViewModel: AdminDashboardViewModel,
    onDeleteClick: (Food) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search for food items...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFF1976D2)
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = Color.Gray
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1976D2),
                    unfocusedBorderColor = Color.LightGray
                )
            )
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color(0xFF1976D2), modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("👨‍💼 Admin Panel", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Manage your food menu", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        if (dashboardViewModel.isFoodsLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF1976D2))
                }
            }
        } else if (dashboardViewModel.foodsByCategory.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(80.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("No food items yet", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Tap + to add your first item", fontSize = 14.sp, color = Color.LightGray)
                    }
                }
            }
        } else {
            val foodsToDisplay = if (searchQuery.isNotEmpty()) {
                dashboardViewModel.searchFoods(searchQuery).groupBy { it.category }
            } else {
                dashboardViewModel.foodsByCategory
            }

            foodsToDisplay.forEach { (category, foods) ->
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(category, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Text("${foods.size} items", fontSize = 14.sp, color = Color.Gray)
                    }
                }

                items(foods) { food ->
                    AdminFoodItemCard(
                        food = food,
                        onDeleteClick = { onDeleteClick(food) },
                        isDeleting = adminViewModel.deletingFoodId == food.id
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }
            }
        }
    }
}

@Composable
fun AdminFoodItemCard(
    food: Food,
    onDeleteClick: () -> Unit,
    isDeleting: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color(0xFF1976D2), modifier = Modifier.size(32.dp))
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(food.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(food.description, fontSize = 14.sp, color = Color.Gray, maxLines = 2)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("NPR ${food.price.toInt()}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                    Spacer(modifier = Modifier.width(12.dp))
                    if (!food.isAvailable) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text("Unavailable", fontSize = 10.sp, color = Color(0xFFD32F2F), modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = onDeleteClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                shape = RoundedCornerShape(8.dp),
                enabled = !isDeleting
            ) {
                if (isDeleting) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                } else {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}
// THIS IS PART 3 - ADD THIS TO THE SAME FILE AFTER PART 2

@Composable
fun AdminStatsTab(
    adminViewModel: AdminDashboardViewModel,
    dashboardViewModel: DashboardViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Quick Stats",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
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
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    icon = Icons.Default.List,
                    title = "Total Foods",
                    value = dashboardViewModel.allFoods.size.toString(),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    icon = Icons.Default.CheckCircle,
                    title = "Available",
                    value = dashboardViewModel.allFoods.count { it.isAvailable }.toString(),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            Text(
                text = "Category Breakdown",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        items(dashboardViewModel.foodsByCategory.entries.toList()) { (category, foods) ->
            CategoryStatCard(category = category, count = foods.size)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun CategoryStatCard(category: String, count: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(category, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Text("$count items", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
        }
    }
}

@Composable
fun AdminProfileTab(dashboardViewModel: DashboardViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = Color(0xFF1976D2),
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = dashboardViewModel.userName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = dashboardViewModel.userEmail,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1976D2)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "👑 ADMIN",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            Text(
                text = "Admin Settings",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            AdminProfileOption(
                icon = Icons.Default.Settings,
                title = "App Settings",
                description = "Configure app preferences"
            )
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        item {
            AdminProfileOption(
                icon = Icons.Default.Info,
                title = "System Info",
                description = "View system information"
            )
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        item {
            AdminProfileOption(
                icon = Icons.Default.ExitToApp,
                title = "Logout",
                description = "Sign out of admin account"
            )
        }
    }
}

@Composable
fun StatCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
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
fun AdminProfileOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
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