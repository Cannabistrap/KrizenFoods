//
//package com.example.krizenfoods.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.krizenfoods.view.AddFoodScreen
//import com.example.krizenfoods.view.AdminDashboardScreen
//import com.example.krizenfoods.view.DashboardScreen
//import com.example.krizenfoods.view.ForgotPasswordScreen
//import com.example.krizenfoods.view.LoginScreen
//import com.example.krizenfoods.view.NotificationScreen
//import com.example.krizenfoods.view.OrderFormScreen
//import com.example.krizenfoods.view.SignupScreen
//import com.example.krizenfoods.view.SplashScreen
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    // Create a single ViewModel instance that will be shared
//    val dashboardViewModel: DashboardViewModel = viewModel()
//
//    NavHost(
//        navController = navController,
//        startDestination = "splash"
//    ) {
//        // Splash Screen
//        composable("splash") {
//            SplashScreen(
//                onNavigateToLogin = {
//                    navController.navigate("login") {
//                        popUpTo("splash") { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        // Login Screen
//        composable("login") {
//            LoginScreen(
//                onNavigateToSignup = {
//                    navController.navigate("signup")
//                },
//                onNavigateToHome = {
//                    navController.navigate("dashboard") {
//                        popUpTo("login") { inclusive = true }
//                    }
//                },
//                onNavigateToForgotPassword = {
//                    navController.navigate("forgotpassword")
//                }
//            )
//        }
//
//        // Signup Screen
//        composable("signup") {
//            SignupScreen(
//                onNavigateToLogin = {
//                    navController.navigate("login") {
//                        popUpTo("signup") { inclusive = true }
//                    }
//                },
//                onNavigateToHome = {
//                    navController.navigate("dashboard") {
//                        popUpTo("signup") { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        // Forgot Password Screen
//        composable("forgotpassword") {
//            ForgotPasswordScreen(
//                onNavigateBack = {
//                    navController.popBackStack()
//                },
//                onNavigateToLogin = {
//                    navController.navigate("login") {
//                        popUpTo("forgotpassword") { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        // Dashboard Screen - Detects Admin or User
//        composable("dashboard") {
//            if (dashboardViewModel.isAdmin) {
//                // Show Admin Dashboard
//                AdminDashboardScreen(
//                    dashboardViewModel = dashboardViewModel,
//                    onNavigateToAddFood = {
//                        navController.navigate("add_food")
//                    }
//                )
//            } else {
//                // Show Regular User Dashboard
//                DashboardScreen(
//                    viewModel = dashboardViewModel,
//                    onNavigateToOrderForm = {
//                        navController.navigate("order_form")
//                    },
//                    onNavigateToNotifications = {
//                        navController.navigate("notifications")
//                    }
//                )
//            }
//        }
//
//        // Add Food Screen (Admin Only)
//        composable("add_food") {
//            AddFoodScreen(
//                onNavigateBack = {
//                    navController.popBackStack()
//                }
//            )
//        }
//
//        // Order Form Screen (User Only)
//        composable("order_form") {
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
//
//        // Notifications Screen (User Only)
//        composable("notifications") {
//            NotificationScreen(
//                viewModel = dashboardViewModel,
//                onNavigateBack = {
//                    navController.popBackStack()
//                }
//            )
//        }
//    }
//}
//

//package com.example.krizenfoods.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.krizenfoods.view.AddFoodScreen
//import com.example.krizenfoods.view.AdminDashboardScreen
//import com.example.krizenfoods.view.DashboardScreen
//import com.example.krizenfoods.view.ForgotPasswordScreen
//import com.example.krizenfoods.view.LoginScreen
//import com.example.krizenfoods.view.NotificationScreen
//import com.example.krizenfoods.view.OrderFormScreen
//import com.example.krizenfoods.view.SignupScreen
//import com.example.krizenfoods.view.SplashScreen
//import com.example.krizenfoods.viewmodel.AdminDashboardViewModel
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    // Create ViewModel instances that will be shared
//    val dashboardViewModel: DashboardViewModel = viewModel()
//    val adminViewModel: AdminDashboardViewModel = viewModel()
//
//    NavHost(
//        navController = navController,
//        startDestination = "splash"
//    ) {
//        // Splash Screen
//        composable("splash") {
//            SplashScreen(
//                onNavigateToLogin = {
//                    navController.navigate("login") {
//                        popUpTo("splash") { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        // Login Screen
//        composable("login") {
//            LoginScreen(
//                onNavigateToSignup = {
//                    navController.navigate("signup")
//                },
//                onNavigateToHome = {
//                    navController.navigate("dashboard") {
//                        popUpTo("login") { inclusive = true }
//                    }
//                },
//                onNavigateToForgotPassword = {
//                    navController.navigate("forgotpassword")
//                }
//            )
//        }
//
//        // Signup Screen
//        composable("signup") {
//            SignupScreen(
//                onNavigateToLogin = {
//                    navController.navigate("login") {
//                        popUpTo("signup") { inclusive = true }
//                    }
//                },
//                onNavigateToHome = {
//                    navController.navigate("dashboard") {
//                        popUpTo("signup") { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        // Forgot Password Screen
//        composable("forgotpassword") {
//            ForgotPasswordScreen(
//                onNavigateBack = {
//                    navController.popBackStack()
//                },
//                onNavigateToLogin = {
//                    navController.navigate("login") {
//                        popUpTo("forgotpassword") { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        // Dashboard Screen - Detects Admin or User
//        composable("dashboard") {
//            if (dashboardViewModel.isAdmin) {
//                // Show Admin Dashboard
//                AdminDashboardScreen(
//                    dashboardViewModel = dashboardViewModel,
//                    adminViewModel = adminViewModel,
//                    onNavigateToAddFood = {
//                        navController.navigate("add_food")
//                    },
//                    onLogout = {
//                        adminViewModel.logout {
//                            navController.navigate("login") {
//                                popUpTo(0) { inclusive = true }
//                            }
//                        }
//                    }
//                )
//            } else {
//                // Show Regular User Dashboard
//                DashboardScreen(
//                    viewModel = dashboardViewModel,
//                    onNavigateToOrderForm = {
//                        navController.navigate("order_form")
//                    },
//                    onNavigateToNotifications = {
//                        navController.navigate("notifications")
//                    },
//                    onLogout = {
//                        dashboardViewModel.logout {
//                            navController.navigate("login") {
//                                popUpTo(0) { inclusive = true }
//                            }
//                        }
//                    }
//                )
//            }
//        }
//
//        // Add Food Screen (Admin Only)
//        composable("add_food") {
//            AddFoodScreen(
//                onNavigateBack = {
//                    navController.popBackStack()
//                }
//            )
//        }
//
//        // Order Form Screen (User Only)
//        composable("order_form") {
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
//
//        // Notifications Screen (User Only)
//        composable("notifications") {
//            NotificationScreen(
//                viewModel = dashboardViewModel,
//                onNavigateBack = {
//                    navController.popBackStack()
//                }
//            )
//        }
//    }
//}

package com.example.krizenfoods.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.krizenfoods.view.AddFoodScreen
import com.example.krizenfoods.view.AdminDashboardScreen
import com.example.krizenfoods.view.DashboardScreen
import com.example.krizenfoods.view.ForgotPasswordScreen
import com.example.krizenfoods.view.LoginScreen
import com.example.krizenfoods.view.NotificationScreen
import com.example.krizenfoods.view.OrderFormScreen
import com.example.krizenfoods.view.SignupScreen
import com.example.krizenfoods.view.SplashScreen
import com.example.krizenfoods.viewmodel.AdminDashboardViewModel
import com.example.krizenfoods.viewmodel.DashboardViewModel

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
        // ✅ KEY CHANGE: Create NEW ViewModel instances for each navigation
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

        // Order Form Screen (User Only)
        composable("order_form") {
            val dashboardViewModel: DashboardViewModel = viewModel()
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

        // Notifications Screen (User Only)
        composable("notifications") {
            val dashboardViewModel: DashboardViewModel = viewModel()
            NotificationScreen(
                viewModel = dashboardViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
