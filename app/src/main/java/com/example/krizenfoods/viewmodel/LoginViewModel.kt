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

    fun onLoginClick(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please fill all fields"
            return
        }

        isLoading = true
        errorMessage = ""

        authController.login(
            email = email,
            password = password,
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