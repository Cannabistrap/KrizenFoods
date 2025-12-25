
package com.example.krizenfoods.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.krizenfoods.view.DashboardScreen
import com.example.krizenfoods.view.LoginScreen
import com.example.krizenfoods.view.SignupScreen
import com.example.krizenfoods.view.SplashScreen
import com.example.krizenfoods.view.ForgotPasswordScreen  // NEW IMPORT

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("login") {
            LoginScreen(
                onNavigateToSignup = {
                    navController.navigate("signup")
                },
                onNavigateToHome = {
                    // Navigates to dashboard
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                // NEW: Forgot Password navigation
                onNavigateToForgotPassword = {
                    navController.navigate("forgotpassword")
                }
            )
        }

        composable("signup") {
            SignupScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    // Navigates to dashboard
                    navController.navigate("dashboard") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }

        // NEW: Forgot Password Screen
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

        composable("dashboard") {
            DashboardScreen()
        }
    }
}