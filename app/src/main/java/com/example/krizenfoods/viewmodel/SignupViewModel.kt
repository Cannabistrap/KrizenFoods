package com.example.krizenfoods.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.krizenfoods.controllers.AuthController

class SignupViewModel : ViewModel() {

    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    private val authController = AuthController()

    fun onSignupClick(onSuccess: () -> Unit) {
        // Validation
        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
            errorMessage = "Please fill all fields"
            return
        }

        if (password != confirmPassword) {
            errorMessage = "Passwords don't match"
            return
        }

        if (password.length < 6) {
            errorMessage = "Password must be at least 6 characters"
            return
        }

        isLoading = true
        errorMessage = ""

        // Call AuthController
        authController.signUp(
            email = email,
            password = password,
            fullName = fullName,
            onSuccess = {
                isLoading = false
                onSuccess()
            },
            onError = { error ->
                isLoading = false
                errorMessage = error
            }
        )
    }
}