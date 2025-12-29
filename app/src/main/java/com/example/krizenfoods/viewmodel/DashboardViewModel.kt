//package com.example.krizenfoods.viewmodel
//
//
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.example.krizenfoods.controllers.AuthController
//
//class DashboardViewModel : ViewModel() {
//
//    var userName by mutableStateOf("User")
//    var userEmail by mutableStateOf("")
//    var isLoading by mutableStateOf(true)
//
//    private val authController = AuthController()
//
//    init {
//        loadUserData()
//    }
//
//    private fun loadUserData() {
//        val currentUser = authController.getCurrentUser()
//        if (currentUser != null) {
//            userEmail = currentUser.email ?: ""
//            // Extract name from email (before @)
//            userName = userEmail.substringBefore("@").capitalize()
//        }
//        isLoading = false
//    }
//
//    fun onLogoutClick(onSuccess: () -> Unit) {
//        // You can add logout functionality here later
//        // For now, just navigate back
//        onSuccess()
//    }
//}


package com.example.krizenfoods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.krizenfoods.controllers.AuthController

class DashboardViewModel : ViewModel() {

    var userName by mutableStateOf("User")
    var userEmail by mutableStateOf("")
    var isLoading by mutableStateOf(true)
    var isAdmin by mutableStateOf(false)  // NEW: Check if user is admin

    private val authController = AuthController()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        val currentUser = authController.getCurrentUser()
        if (currentUser != null) {
            userEmail = currentUser.email ?: ""
            // Extract name from email (before @)
            userName = userEmail.substringBefore("@").capitalize()

            // Check if user is admin
            isAdmin = userEmail == "admin@gmail.com"
        }
        isLoading = false
    }

    fun onLogoutClick(onSuccess: () -> Unit) {
        // You can add logout functionality here later
        // For now, just navigate back
        onSuccess()
    }
}