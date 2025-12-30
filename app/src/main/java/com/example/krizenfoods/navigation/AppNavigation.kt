//
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
//import com.example.krizenfoods.view.SignupScreen
//import com.example.krizenfoods.view.SplashScreen
//import com.example.krizenfoods.viewmodel.DashboardViewModel
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
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
//            val viewModel: DashboardViewModel = viewModel()
//
//            if (viewModel.isAdmin) {
//                // Show Admin Dashboard with navigation callback
//                AdminDashboardScreen(
//                    dashboardViewModel = viewModel,
//                    onNavigateToAddFood = {
//                        navController.navigate("add_food")
//                    }
//                )
//            } else {
//                // Show Regular User Dashboard
//                DashboardScreen(viewModel = viewModel)
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
//    }
//}

package com.example.krizenfoods.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.krizenfoods.view.AddFoodScreen
import com.example.krizenfoods.view.AdminDashboardScreen
import com.example.krizenfoods.view.DashboardScreen
import com.example.krizenfoods.view.ForgotPasswordScreen
import com.example.krizenfoods.view.LoginScreen
import com.example.krizenfoods.view.SignupScreen
import com.example.krizenfoods.view.SplashScreen
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
        composable("dashboard") {
            val viewModel: DashboardViewModel = viewModel()

            if (viewModel.isAdmin) {
                // Show Admin Dashboard with full management interface
                AdminDashboardScreen(
                    dashboardViewModel = viewModel,
                    onNavigateToAddFood = {
                        navController.navigate("add_food")
                    }
                )
            } else {
                // Show Regular User Dashboard
                DashboardScreen(viewModel = viewModel)
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
    }
}