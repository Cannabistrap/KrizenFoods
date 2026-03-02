

package com.example.krizenfoods.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.krizenfoods.view.AddFoodScreen
import com.example.krizenfoods.view.AdminDashboardScreen
import com.example.krizenfoods.view.AdminOrdersScreen
import com.example.krizenfoods.view.DashboardScreen
import com.example.krizenfoods.view.ForgotPasswordScreen
import com.example.krizenfoods.view.LoginScreen
import com.example.krizenfoods.view.NotificationScreen
import com.example.krizenfoods.view.OrderFormScreen
import com.example.krizenfoods.view.SignupScreen
import com.example.krizenfoods.view.SplashScreen
import com.example.krizenfoods.viewmodel.AdminDashboardViewModel
import com.example.krizenfoods.viewmodel.DashboardViewModel
import androidx.compose.runtime.remember
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        // Splash Screen
        composable("splash") {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // Login Screen
        composable("login") {
            LoginScreen(
                onNavigateToSignup = {
                    navController.navigate("signup")
                },
                onNavigateToHome = {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToForgotPassword = {
                    navController.navigate("forgotpassword")
                }
            )
        }

        // Signup Screen
        composable("signup") {
            SignupScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate("dashboard") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }

        // Forgot Password Screen
        composable("forgotpassword") {
            ForgotPasswordScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("forgotpassword") { inclusive = true }
                    }
                }
            )
        }

        // Dashboard Screen - Detects Admin or User
        composable("dashboard") {
            val dashboardViewModel: DashboardViewModel = viewModel()
            val adminViewModel: AdminDashboardViewModel = viewModel()

            if (dashboardViewModel.isAdmin) {
                // Show Admin Dashboard
                AdminDashboardScreen(
                    dashboardViewModel = dashboardViewModel,
                    adminViewModel = adminViewModel,
                    onNavigateToAddFood = {
                        navController.navigate("add_food")
                    },
                    onNavigateToOrders = {
                        navController.navigate("admin_orders")
                    },
                    onLogout = {
                        adminViewModel.logout {
                            navController.navigate("login") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                )
            } else {
                // Show Regular User Dashboard
                DashboardScreen(
                    viewModel = dashboardViewModel,
                    onNavigateToOrderForm = {
                        navController.navigate("order_form")
                    },
                    onNavigateToNotifications = {
                        navController.navigate("notifications")
                    },
                    onLogout = {
                        dashboardViewModel.logout {
                            navController.navigate("login") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                )
            }
        }

        // Add Food Screen (Admin Only)
        composable("add_food") {
            AddFoodScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Admin Orders Screen (Admin Only)
        composable("admin_orders") {
            val dashboardViewModel: DashboardViewModel = viewModel()
            val adminViewModel: AdminDashboardViewModel = viewModel()

            AdminOrdersScreen(
                dashboardViewModel = dashboardViewModel,
                adminViewModel = adminViewModel,
                onNavigateToFoods = {
                    navController.navigate("dashboard") {
                        popUpTo("admin_orders") { inclusive = true }
                    }
                },
                onNavigateToStats = {
                    navController.navigate("dashboard") {
                        popUpTo("admin_orders") { inclusive = true }
                    }
                },
                onNavigateToProfile = {
                    navController.navigate("dashboard") {
                        popUpTo("admin_orders") { inclusive = true }
                    }
                },
                onNavigateToAddFood = {
                    navController.navigate("add_food")
                }
            )
        }

        // ✅ FIXED: Order Form Screen - Share ViewModel with dashboard
//        composable("order_form") {
//            val dashboardEntry = remember(navController) {
//                navController.getBackStackEntry("dashboard")
//            }
//            val dashboardViewModel: DashboardViewModel = viewModel(dashboardEntry)
//
//            OrderFormScreen(
//                viewModel = dashboardViewModel,
//                onNavigateBack = {
//                    navController.popBackStack()
//                },
//                onOrderSuccess = {
//                    navController.navigate("dashboard") {
//                        popUpTo("dashboard") { inclusive = true }
//                    }
//                }
//            )
//        }
        composable("order_form") { backStackEntry ->
            val dashboardEntry = navController.previousBackStackEntry
                ?: backStackEntry
            val dashboardViewModel: DashboardViewModel = viewModel(dashboardEntry)

            OrderFormScreen(
                viewModel = dashboardViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onOrderSuccess = {
                    navController.navigate("dashboard") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }

        // ✅ FIXED: Notifications Screen - Share ViewModel with dashboard
//        composable("notifications") {
//            val dashboardEntry = remember(navController) {
//                navController.getBackStackEntry("dashboard")
//            }
//            val dashboardViewModel: DashboardViewModel = viewModel(dashboardEntry)
//
//            NotificationScreen(
//                viewModel = dashboardViewModel,
//                onNavigateBack = {
//                    navController.popBackStack()
//                }
//            )
//        }

        composable("notifications") { backStackEntry ->
            val dashboardEntry = navController.previousBackStackEntry
                ?: backStackEntry
            val dashboardViewModel: DashboardViewModel = viewModel(dashboardEntry)

            NotificationScreen(
                viewModel = dashboardViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}