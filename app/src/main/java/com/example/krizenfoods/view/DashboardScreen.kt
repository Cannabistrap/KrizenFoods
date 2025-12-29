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


package com.example.krizenfoods.view

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

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel()
) {
    // Check if user is admin and show appropriate dashboard
    if (viewModel.isAdmin) {
        AdminDashboardScreen(viewModel = viewModel)
    } else {
        UserDashboardScreen(viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboardScreen(
    viewModel: DashboardViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Welcome Back!",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        Text(
                            text = viewModel.userName,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
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
        }
    ) { paddingValues ->

        if (viewModel.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFFF57C00))
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
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
                                text = "🎉 Login Successful!",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF57C00)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Firebase Authentication Working",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // User Info Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "User Information",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color(0xFFF57C00)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Name",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = viewModel.userName,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null,
                                tint = Color(0xFFF57C00)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Email",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = viewModel.userEmail,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Quick Actions Title
                Text(
                    text = "Quick Actions",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Quick Action Cards Grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionCard(
                        icon = Icons.Default.ShoppingCart,
                        title = "Browse Foods",
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionCard(
                        icon = Icons.Default.Favorite,
                        title = "Favorites",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionCard(
                        icon = Icons.Default.DateRange,
                        title = "Orders",
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionCard(
                        icon = Icons.Default.Settings,
                        title = "Settings",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun QuickActionCard(
    icon: ImageVector,
    title: String,
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
                tint = Color(0xFFF57C00),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}