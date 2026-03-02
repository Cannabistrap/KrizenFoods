
package com.example.krizenfoods.view

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.krizenfoods.model.Food
import com.example.krizenfoods.model.Order
import com.example.krizenfoods.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel(),
    onNavigateToOrderForm: () -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    if (viewModel.isAdmin) {
        AdminDashboardScreen(
            dashboardViewModel = viewModel,
            onLogout = onLogout
        )
    } else {
        UserDashboardScreen(
            viewModel = viewModel,
            onNavigateToOrderForm = onNavigateToOrderForm,
            onNavigateToNotifications = onNavigateToNotifications,
            onLogout = onLogout
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboardScreen(
    viewModel: DashboardViewModel,
    onNavigateToOrderForm: () -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Krizen Foods",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF57C00)
                ),
                actions = {
                    if (viewModel.cartItemCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ) {
                                    Text(
                                        text = viewModel.cartItemCount.toString(),
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        ) {
                            IconButton(onClick = { selectedTab = 2 }) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "Cart",
                                    tint = Color.White
                                )
                            }
                        }
                    } else {
                        IconButton(onClick = { selectedTab = 2 }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart",
                                tint = Color.White
                            )
                        }
                    }

                    Box {
                        IconButton(onClick = onNavigateToNotifications) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.White
                            )
                        }
                        if (viewModel.unreadNotificationCount > 0) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .offset(x = 28.dp, y = 8.dp)
                                    .background(
                                        color = Color.Red,
                                        shape = CircleShape
                                    )
                            )
                        }
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
                    icon = { Icon(Icons.Default.Home, contentDescription = "Menu") },
                    label = { Text("Menu") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFF57C00),
                        selectedTextColor = Color(0xFFF57C00),
                        indicatorColor = Color(0xFFFFF3E0)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFF57C00),
                        selectedTextColor = Color(0xFFF57C00),
                        indicatorColor = Color(0xFFFFF3E0)
                    )
                )
                NavigationBarItem(
                    icon = {
                        if (viewModel.cartItemCount > 0) {
                            BadgedBox(
                                badge = {
                                    Badge(
                                        containerColor = Color.Red,
                                        contentColor = Color.White
                                    ) {
                                        Text(
                                            text = viewModel.cartItemCount.toString(),
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            ) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                            }
                        } else {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                        }
                    },
                    label = { Text("Cart") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFF57C00),
                        selectedTextColor = Color(0xFFF57C00),
                        indicatorColor = Color(0xFFFFF3E0)
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
                0 -> MenuTab(searchQuery = searchQuery, onSearchQueryChange = { searchQuery = it }, viewModel = viewModel)
                1 -> ProfileTab(viewModel = viewModel, onLogout = onLogout)
                2 -> CartTab(
                    viewModel = viewModel,
                    onNavigateToOrderForm = onNavigateToOrderForm
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTab(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    viewModel: DashboardViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search for food items...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color(0xFFF57C00))
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF57C00),
                    unfocusedBorderColor = Color.LightGray
                )
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // ✅ CURRENT ORDERS SECTION
        val activeOrders = viewModel.userOrders.filter { it.status == "pending" || it.status == "confirmed" }
        if (activeOrders.isNotEmpty()) {
            item {
                Text(
                    text = "Current Orders",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            items(activeOrders) { order ->
                ActiveOrderCard(order = order, onCancel = { viewModel.cancelOrder(order.orderId) })
                Spacer(modifier = Modifier.height(12.dp))
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }

        // Welcome card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFFF57C00), modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Hello, ${viewModel.userName}!", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF57C00))
                        Text(text = "What would you like to eat today?", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        // Food Menu
        if (viewModel.isFoodsLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFFF57C00))
                }
            }
        } else {
            val foodsToDisplay = if (searchQuery.isNotEmpty()) {
                viewModel.searchFoods(searchQuery).groupBy { it.category }
            } else {
                viewModel.foodsByCategory
            }

            foodsToDisplay.forEach { (category, foods) ->
                item {
                    Text(text = category, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(vertical = 12.dp))
                }
                items(foods) { food ->
                    FoodItemCard(food = food, viewModel = viewModel)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun ActiveOrderCard(order: Order, onCancel: () -> Unit) {
    var showDetails by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDetails = !showDetails },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Order #${order.orderId.takeLast(5)}", fontWeight = FontWeight.Bold)
                    Text(text = "Status: ${order.status.uppercase()}", color = if (order.status == "pending") Color(0xFFF57C00) else Color.Blue, fontSize = 12.sp)
                }
                Text(text = "NPR ${order.totalAmount.toInt()}", fontWeight = FontWeight.Bold, color = Color(0xFFF57C00))
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = if (showDetails) {
                    order.items.joinToString("\n") { "${it.quantity}x ${it.foodName}" }
                } else {
                    order.items.joinToString { "${it.quantity}x ${it.foodName}" }
                },
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = if (showDetails) 10 else 1
            )
            
            if (showDetails) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Location: ${order.location}", fontSize = 12.sp, color = Color.Gray)
                if (order.remarks.isNotEmpty()) {
                    Text(text = "Note: ${order.remarks}", fontSize = 12.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            
            // ✅ UPDATED: Allow cancellation for both pending AND confirmed orders
            Button(
                onClick = { onCancel() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Cancel Order", color = Color.White)
            }
        }
    }
}

@Composable
fun FoodItemCard(food: Food, viewModel: DashboardViewModel) {
    val isInCart = viewModel.isInCart(food.id)
    val quantity = viewModel.getCartQuantity(food.id)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Card(modifier = Modifier.size(60.dp), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, tint = Color(0xFFF57C00), modifier = Modifier.size(32.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = food.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = food.description, fontSize = 14.sp, color = Color.Gray, maxLines = 2)
                Text(text = "NPR ${food.price.toInt()}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF57C00))
            }
            if (isInCart) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(onClick = { viewModel.updateQuantity(food.id, quantity - 1) }, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Decrease", tint = Color(0xFFF57C00))
                    }
                    Text(text = quantity.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    IconButton(onClick = { viewModel.updateQuantity(food.id, quantity + 1) }, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Increase", tint = Color(0xFFF57C00))
                    }
                }
            } else {
                Button(onClick = { viewModel.addToCart(food) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00)), shape = RoundedCornerShape(8.dp)) {
                    Text("Add")
                }
            }
        }
    }
}

@Composable
fun CartTab(viewModel: DashboardViewModel, onNavigateToOrderForm: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (viewModel.cartItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(100.dp))
                    Text(text = "Your cart is empty", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                }
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f).padding(16.dp)) {
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "My Cart", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        TextButton(onClick = { viewModel.clearCart() }) { Text(text = "Clear All", color = Color.Red) }
                    }
                }
                items(viewModel.cartItems) { cartItem ->
                    CartItemCard(
                        cartItem = cartItem,
                        onIncreaseQuantity = { viewModel.updateQuantity(cartItem.food.id, cartItem.quantity + 1) },
                        onDecreaseQuantity = { viewModel.updateQuantity(cartItem.food.id, cartItem.quantity - 1) },
                        onRemove = { viewModel.removeFromCart(cartItem.food.id) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(text = "Total Amount", fontSize = 14.sp, color = Color.Gray)
                            Text(text = "NPR ${viewModel.cartTotal.toInt()}", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF57C00))
                        }
                        Text(text = "${viewModel.cartItemCount} items", fontSize = 14.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onNavigateToOrderForm, modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00)), shape = RoundedCornerShape(12.dp)) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Order Now", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(cartItem: com.example.krizenfoods.model.CartItem, onIncreaseQuantity: () -> Unit, onDecreaseQuantity: () -> Unit, onRemove: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Card(modifier = Modifier.size(70.dp), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, tint = Color(0xFFF57C00), modifier = Modifier.size(36.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = cartItem.food.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = "NPR ${cartItem.food.price.toInt()} each", fontSize = 14.sp, color = Color.Gray)
                Text(text = "NPR ${cartItem.totalPrice.toInt()}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF57C00) )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(onClick = onDecreaseQuantity, modifier = Modifier.size(32.dp)) { Icon(imageVector = Icons.Default.Clear, contentDescription = "Decrease", tint = Color(0xFFF57C00)) }
                    Text(text = cartItem.quantity.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    IconButton(onClick = onIncreaseQuantity, modifier = Modifier.size(32.dp)) { Icon(imageVector = Icons.Default.Add, contentDescription = "Increase", tint = Color(0xFFF57C00)) }
                }
                TextButton(onClick = onRemove, contentPadding = PaddingValues(0.dp)) { Text(text = "Remove", fontSize = 12.sp, color = Color.Red) }
            }
        }
    }
}

@Composable
fun ProfileTab(viewModel: DashboardViewModel, onLogout: () -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))) {
                Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, tint = Color(0xFFF57C00), modifier = Modifier.size(80.dp))
                    Text(text = viewModel.userName, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = viewModel.userEmail, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item { Text(text = "Account Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { ProfileOption(icon = Icons.Default.Person, title = "Edit Profile", description = "Update your personal information") }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { ProfileOption(icon = Icons.Default.LocationOn, title = "Addresses", description = "Manage your delivery addresses") }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { ProfileOption(icon = Icons.Default.Settings, title = "Settings", description = "App preferences and notifications") }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { ProfileOption(icon = Icons.Default.ExitToApp, title = "Logout", description = "Sign out of your account", onClick = onLogout) }
    }
}

@Composable
fun ProfileOption(icon: ImageVector, title: String, description: String, onClick: () -> Unit = {}) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = title, tint = Color(0xFFF57C00), modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = description, fontSize = 14.sp, color = Color.Gray)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Go", tint = Color.Gray)
        }
    }
}