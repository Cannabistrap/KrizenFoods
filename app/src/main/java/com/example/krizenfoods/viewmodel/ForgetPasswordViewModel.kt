package com.example.krizenfoods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.krizenfoods.controllers.AuthController

class ForgotPasswordViewModel : ViewModel() {

    var email by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    var successMessage by mutableStateOf("")

    private val authController = AuthController()

    fun onSendResetLinkClick() {
        if (email.isBlank()) {
            errorMessage = "Please enter your email address"
            return
        }

        isLoading = true
        errorMessage = ""
        successMessage = ""

        authController.sendPasswordResetEmail(
            email = email,
            onSuccess = {
                isLoading = false
                successMessage = "Password reset link sent to your email. Please check your inbox."
            },
            onError = { error ->
                isLoading = false
                errorMessage = error
            }
        )
    }
}