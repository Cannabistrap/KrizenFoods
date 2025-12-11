
package com.example.krizenfoods.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.krizenfoods.view.LoginScreen
import com.example.krizenfoods.view.SignupScreen
import com.example.krizenfoods.view.SplashScreen

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
                    // For now, just show a toast or debug message
                    // We'll implement home screen later
                    println("Login successful - would navigate to home")
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
                    // For now, just show a toast or debug message
                    // We'll implement home screen later
                    println("Signup successful - would navigate to home")
                }
            )
        }
    }
}