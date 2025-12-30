//package com.example.krizenfoods.view
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun DashboardScreen() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = Color.White
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "🎉 Welcome to Krizen Foods!",
//                fontSize = 28.sp,
//                color = Color(0xFFF57C00),
//                style = MaterialTheme.typography.headlineLarge
//            )
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
//            ) {
//                Column(
//                    modifier = Modifier.padding(24.dp)
//                ) {
//                    Text(
//                        text = "Dashboard Features:",
//                        fontSize = 20.sp,
//                        color = Color.Black,
//                        style = MaterialTheme.typography.titleLarge
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    Text("• View your profile", color = Color.Gray)
//                    Text("• Browse food items", color = Color.Gray)
//                    Text("• Track orders", color = Color.Gray)
//                    Text("• Manage account", color = Color.Gray)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(48.dp))
//
//            Text(
//                text = "Firebase Authentication Successful!",
//                color = Color.Green,
//                fontSize = 16.sp
//            )
//        }
//    }
//}


//package com.example.krizenfoods.view
//
//import androidx.compose.foundation.background
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
//fun DashboardScreen(
//    viewModel: DashboardViewModel = viewModel()
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Column {
//                        Text(
//                            text = "Welcome Back!",
//                            style = MaterialTheme.typography.titleMedium,
//                            color = Color.White
//                        )
//                        Text(
//                            text = viewModel.userName,
//                            style = MaterialTheme.typography.bodySmall,
//                            color = Color.White.copy(alpha = 0.8f)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFF57C00)
//                ),
//                actions = {
//                    IconButton(onClick = { /* Add notification action */ }) {
//                        Icon(
//                            imageVector = Icons.Default.Notifications,
//                            contentDescription = "Notifications",
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
//                CircularProgressIndicator(color = Color(0xFFF57C00))
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp)
//            ) {
//                // Welcome Card
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFFFFF3E0)
//                    )
//                ) {
//                    Row(
//                        modifier = Modifier.padding(20.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.CheckCircle,
//                            contentDescription = null,
//                            tint = Color(0xFFF57C00),
//                            modifier = Modifier.size(48.dp)
//                        )
//                        Spacer(modifier = Modifier.width(16.dp))
//                        Column {
//                            Text(
//                                text = "🎉 Login Successful!",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color(0xFFF57C00)
//                            )
//                            Spacer(modifier = Modifier.height(4.dp))
//                            Text(
//                                text = "Firebase Authentication Working",
//                                fontSize = 14.sp,
//                                color = Color.Gray
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // User Info Card
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color.White
//                    )
//                ) {
//                    Column(
//                        modifier = Modifier.padding(20.dp)
//                    ) {
//                        Text(
//                            text = "User Information",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Person,
//                                contentDescription = null,
//                                tint = Color(0xFFF57C00)
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                            Column {
//                                Text(
//                                    text = "Name",
//                                    fontSize = 12.sp,
//                                    color = Color.Gray
//                                )
//                                Text(
//                                    text = viewModel.userName,
//                                    fontSize = 16.sp,
//                                    color = Color.Black
//                                )
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Email,
//                                contentDescription = null,
//                                tint = Color(0xFFF57C00)
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                            Column {
//                                Text(
//                                    text = "Email",
//                                    fontSize = 12.sp,
//                                    color = Color.Gray
//                                )
//                                Text(
//                                    text = viewModel.userEmail,
//                                    fontSize = 16.sp,
//                                    color = Color.Black
//                                )
//                            }
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Quick Actions Title
//                Text(
//                    text = "Quick Actions",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Quick Action Cards Grid
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    QuickActionCard(
//                        icon = Icons.Default.ShoppingCart,
//                        title = "Browse Foods",
//                        modifier = Modifier.weight(1f)
//                    )
//                    QuickActionCard(
//                        icon = Icons.Default.Favorite,
//                        title = "Favorites",
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    QuickActionCard(
//                        icon = Icons.Default.DateRange,
//                        title = "Orders",
//                        modifier = Modifier.weight(1f)
//                    )
//                    QuickActionCard(
//                        icon = Icons.Default.Settings,
//                        title = "Settings",
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun QuickActionCard(
//    icon: ImageVector,
//    title: String,
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
//                tint = Color(0xFFF57C00),
//                modifier = Modifier.size(32.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = title,
//                fontSize = 14.sp,
//                color = Color.Black
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
//
//@Composable
//fun DashboardScreen(
//    viewModel: DashboardViewModel = viewModel()
//) {
//    // Check if user is admin and show appropriate dashboard
//    if (viewModel.isAdmin) {
//        AdminDashboardScreen(dashboardViewModel = viewModel)
//    } else {
//        UserDashboardScreen(viewModel = viewModel)
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun UserDashboardScreen(
//    viewModel: DashboardViewModel
//) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Column {
//                        Text(
//                            text = "Welcome Back!",
//                            style = MaterialTheme.typography.titleMedium,
//                            color = Color.White
//                        )
//                        Text(
//                            text = viewModel.userName,
//                            style = MaterialTheme.typography.bodySmall,
//                            color = Color.White.copy(alpha = 0.8f)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFF57C00)
//                ),
//                actions = {
//                    IconButton(onClick = { /* Add notification action */ }) {
//                        Icon(
//                            imageVector = Icons.Default.Notifications,
//                            contentDescription = "Notifications",
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
//                CircularProgressIndicator(color = Color(0xFFF57C00))
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp)
//            ) {
//                // Welcome Card
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFFFFF3E0)
//                    )
//                ) {
//                    Row(
//                        modifier = Modifier.padding(20.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.CheckCircle,
//                            contentDescription = null,
//                            tint = Color(0xFFF57C00),
//                            modifier = Modifier.size(48.dp)
//                        )
//                        Spacer(modifier = Modifier.width(16.dp))
//                        Column {
//                            Text(
//                                text = "🎉 Login Successful!",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color(0xFFF57C00)
//                            )
//                            Spacer(modifier = Modifier.height(4.dp))
//                            Text(
//                                text = "Firebase Authentication Working",
//                                fontSize = 14.sp,
//                                color = Color.Gray
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // User Info Card
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color.White
//                    )
//                ) {
//                    Column(
//                        modifier = Modifier.padding(20.dp)
//                    ) {
//                        Text(
//                            text = "User Information",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Person,
//                                contentDescription = null,
//                                tint = Color(0xFFF57C00)
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                            Column {
//                                Text(
//                                    text = "Name",
//                                    fontSize = 12.sp,
//                                    color = Color.Gray
//                                )
//                                Text(
//                                    text = viewModel.userName,
//                                    fontSize = 16.sp,
//                                    color = Color.Black
//                                )
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Email,
//                                contentDescription = null,
//                                tint = Color(0xFFF57C00)
//                            )
//                            Spacer(modifier = Modifier.width(12.dp))
//                            Column {
//                                Text(
//                                    text = "Email",
//                                    fontSize = 12.sp,
//                                    color = Color.Gray
//                                )
//                                Text(
//                                    text = viewModel.userEmail,
//                                    fontSize = 16.sp,
//                                    color = Color.Black
//                                )
//                            }
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Quick Actions Title
//                Text(
//                    text = "Quick Actions",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Quick Action Cards Grid
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    QuickActionCard(
//                        icon = Icons.Default.ShoppingCart,
//                        title = "Browse Foods",
//                        modifier = Modifier.weight(1f)
//                    )
//                    QuickActionCard(
//                        icon = Icons.Default.Favorite,
//                        title = "Favorites",
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    QuickActionCard(
//                        icon = Icons.Default.DateRange,
//                        title = "Orders",
//                        modifier = Modifier.weight(1f)
//                    )
//                    QuickActionCard(
//                        icon = Icons.Default.Settings,
//                        title = "Settings",
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun QuickActionCard(
//    icon: ImageVector,
//    title: String,
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
//                tint = Color(0xFFF57C00),
//                modifier = Modifier.size(32.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = title,
//                fontSize = 14.sp,
//                color = Color.Black
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
//import androidx.compose.runtime.*
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
//@Composable
//fun DashboardScreen(
//    viewModel: DashboardViewModel = viewModel()
//) {
//    // Check if user is admin and show appropriate dashboard
//    if (viewModel.isAdmin) {
//        AdminDashboardScreen(dashboardViewModel = viewModel)
//    } else {
//        UserDashboardScreen(viewModel = viewModel)
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun UserDashboardScreen(
//    viewModel: DashboardViewModel
//) {
//    var selectedTab by remember { mutableStateOf(0) }
//    var searchQuery by remember { mutableStateOf("") }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Krizen Foods",
//                        style = MaterialTheme.typography.titleLarge,
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFF57C00)
//                ),
//                actions = {
//                    IconButton(onClick = { /* Add notification action */ }) {
//                        Icon(
//                            imageVector = Icons.Default.Notifications,
//                            contentDescription = "Notifications",
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
//                    icon = { Icon(Icons.Default.Home, contentDescription = "All") },
//                    label = { Text("All") },
//                    selected = selectedTab == 0,
//                    onClick = { selectedTab = 0 },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color(0xFFF57C00),
//                        selectedTextColor = Color(0xFFF57C00),
//                        indicatorColor = Color(0xFFFFF3E0)
//                    )
//                )
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
//                    label = { Text("Profile") },
//                    selected = selectedTab == 1,
//                    onClick = { selectedTab = 1 },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color(0xFFF57C00),
//                        selectedTextColor = Color(0xFFF57C00),
//                        indicatorColor = Color(0xFFFFF3E0)
//                    )
//                )
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
//                    label = { Text("Cart") },
//                    selected = selectedTab == 2,
//                    onClick = { selectedTab = 2 },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color(0xFFF57C00),
//                        selectedTextColor = Color(0xFFF57C00),
//                        indicatorColor = Color(0xFFFFF3E0)
//                    )
//                )
//            }
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
//                CircularProgressIndicator(color = Color(0xFFF57C00))
//            }
//        } else {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//            ) {
//                when (selectedTab) {
//                    0 -> AllTab(searchQuery = searchQuery, onSearchQueryChange = { searchQuery = it }, viewModel = viewModel)
//                    1 -> ProfileTab(viewModel = viewModel)
//                    2 -> OrdersTab()
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AllTab(
//    searchQuery: String,
//    onSearchQueryChange: (String) -> Unit,
//    viewModel: DashboardViewModel
//) {
//    androidx.compose.foundation.lazy.LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        item {
//            // Search Bar
//            OutlinedTextField(
//                value = searchQuery,
//                onValueChange = onSearchQueryChange,
//                modifier = Modifier.fillMaxWidth(),
//                placeholder = { Text("Search for food items...") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search",
//                        tint = Color(0xFFF57C00)
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
//                    focusedBorderColor = Color(0xFFF57C00),
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
//            // Welcome Card
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(16.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFFFFF3E0)
//                )
//            ) {
//                Row(
//                    modifier = Modifier.padding(20.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.CheckCircle,
//                        contentDescription = null,
//                        tint = Color(0xFFF57C00),
//                        modifier = Modifier.size(48.dp)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Column {
//                        Text(
//                            text = "Hello, ${viewModel.userName}!",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color(0xFFF57C00)
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "What would you like to eat today?",
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
//        // Add more dummy items to show scrolling
//        items(10) { index ->
//            Spacer(modifier = Modifier.height(12.dp))
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.White
//                )
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.ShoppingCart,
//                        contentDescription = null,
//                        tint = Color(0xFFF57C00),
//                        modifier = Modifier.size(50.dp)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Column(
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        Text(
//                            text = "Food Item ${index + 1}",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "Delicious and fresh",
//                            fontSize = 14.sp,
//                            color = Color.Gray
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "₹${(index + 1) * 50}",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color(0xFFF57C00)
//                        )
//                    }
//                    Button(
//                        onClick = { },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFFF57C00)
//                        ),
//                        shape = RoundedCornerShape(8.dp)
//                    ) {
//                        Text("Add")
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ProfileTab(viewModel: DashboardViewModel) {
//    androidx.compose.foundation.lazy.LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        item {
//            // Profile Header
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(16.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFFFFF3E0)
//                )
//            ) {
//                Column(
//                    modifier = Modifier.padding(20.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.AccountCircle,
//                        contentDescription = null,
//                        tint = Color(0xFFF57C00),
//                        modifier = Modifier.size(80.dp)
//                    )
//                    Spacer(modifier = Modifier.height(12.dp))
//                    Text(
//                        text = viewModel.userName,
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                    Text(
//                        text = viewModel.userEmail,
//                        fontSize = 14.sp,
//                        color = Color.Gray
//                    )
//                }
//            }
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(24.dp))
//        }
//
//        item {
//            // Profile Options
//            Text(
//                text = "Account Settings",
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
//            ProfileOption(
//                icon = Icons.Default.Person,
//                title = "Edit Profile",
//                description = "Update your personal information"
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//
//        item {
//            ProfileOption(
//                icon = Icons.Default.LocationOn,
//                title = "Addresses",
//                description = "Manage your delivery addresses"
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//
//        item {
//            ProfileOption(
//                icon = Icons.Default.Settings,
//                title = "Settings",
//                description = "App preferences and notifications"
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//
//        item {
//            ProfileOption(
//                icon = Icons.Default.ExitToApp,
//                title = "Logout",
//                description = "Sign out of your account"
//            )
//        }
//    }
//}
//
//@Composable
//fun OrdersTab() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "My Orders",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Empty Orders State
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Icon(
//                    imageVector = Icons.Default.ShoppingCart,
//                    contentDescription = null,
//                    tint = Color.LightGray,
//                    modifier = Modifier.size(100.dp)
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "No orders yet",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Gray
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = "Start ordering delicious food!",
//                    fontSize = 14.sp,
//                    color = Color.LightGray
//                )
//                Spacer(modifier = Modifier.height(24.dp))
//                Button(
//                    onClick = { /* Navigate to All tab */ },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFFF57C00)
//                    ),
//                    shape = RoundedCornerShape(12.dp)
//                ) {
//                    Text("Browse Foods")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun CategoryCard(
//    icon: ImageVector,
//    title: String,
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
//                tint = Color(0xFFF57C00),
//                modifier = Modifier.size(32.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = title,
//                fontSize = 14.sp,
//                color = Color.Black,
//                fontWeight = FontWeight.Medium
//            )
//        }
//    }
//}
//
//@Composable
//fun ProfileOption(
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
//                tint = Color(0xFFF57C00),
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.krizenfoods.model.Food
import com.example.krizenfoods.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel()
) {
    if (viewModel.isAdmin) {
        AdminDashboardScreen(dashboardViewModel = viewModel)
    } else {
        UserDashboardScreen(viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboardScreen(
    viewModel: DashboardViewModel
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
                    IconButton(onClick = { /* Add notification action */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
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
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
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
                1 -> ProfileTab(viewModel = viewModel)
                2 -> CartTab()
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
        item {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search for food items...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFFF57C00)
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
                    focusedBorderColor = Color(0xFFF57C00),
                    unfocusedBorderColor = Color.LightGray
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            // Welcome Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF3E0)
                )
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFFF57C00),
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Hello, ${viewModel.userName}!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF57C00)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "What would you like to eat today?",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Show loading or foods
        if (viewModel.isFoodsLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFFF57C00))
                }
            }
        } else if (viewModel.foodsByCategory.isEmpty()) {
            // Empty state
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No food items available yet",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Check back later for delicious options!",
                            fontSize = 14.sp,
                            color = Color.LightGray
                        )
                    }
                }
            }
        } else {
            // Display foods grouped by category
            val foodsToDisplay = if (searchQuery.isNotEmpty()) {
                viewModel.searchFoods(searchQuery).groupBy { it.category }
            } else {
                viewModel.foodsByCategory
            }

            foodsToDisplay.forEach { (category, foods) ->
                item {
                    // Category Header
                    Text(
                        text = category,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }

                items(foods) { food ->
                    FoodItemCard(food = food)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun FoodItemCard(food: Food) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
            // Food Icon/Image placeholder
            Card(
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF3E0)
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = Color(0xFFF57C00),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Food details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = food.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = food.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "NPR ${food.price.toInt()}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF57C00)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Add button
            Button(
                onClick = { /* Add to cart */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF57C00)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Add")
            }
        }
    }
}

@Composable
fun ProfileTab(viewModel: DashboardViewModel) {
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
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF3E0)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = Color(0xFFF57C00),
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = viewModel.userName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = viewModel.userEmail,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Text(
                text = "Account Settings",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ProfileOption(
                icon = Icons.Default.Person,
                title = "Edit Profile",
                description = "Update your personal information"
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            ProfileOption(
                icon = Icons.Default.LocationOn,
                title = "Addresses",
                description = "Manage your delivery addresses"
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            ProfileOption(
                icon = Icons.Default.Settings,
                title = "Settings",
                description = "App preferences and notifications"
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            ProfileOption(
                icon = Icons.Default.ExitToApp,
                title = "Logout",
                description = "Sign out of your account"
            )
        }
    }
}

@Composable
fun CartTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Cart",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Your cart is empty",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Add items from the menu!",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun ProfileOption(
    icon: ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                tint = Color(0xFFF57C00),
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