package com.example.krizenfoods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.krizenfoods.controllers.AuthController

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    private val authController = AuthController()

    companion object {
        var isHardcodedAdmin = false
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please fill all fields"
            return
        }

        isLoading = true
        errorMessage = ""

        // ✅ HARDCODED ADMIN BYPASS
        if (email == "admin@gmail.com" && password == "@admin123") {
            isHardcodedAdmin = true
            isLoading = false
            onSuccess()
            return
        }

        authController.login(
            email = email,
            password = password,
            onSuccess = {
                isHardcodedAdmin = false
                isLoading = false
                onSuccess()
            },
            onError = { error ->
                isHardcodedAdmin = false
                isLoading = false
                errorMessage = error
            }
        )
    }
}