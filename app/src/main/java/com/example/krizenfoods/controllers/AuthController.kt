//package com.example.krizenfoods.controllers
//
//// controllers/AuthController.kt
//
//
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.database.FirebaseDatabase
//
//// ✅ Use regular Firebase classes (NOT ktx) for better compatibility
//
//class AuthController {
//    // Firebase instances
//    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val database = FirebaseDatabase.getInstance().reference
//
//    // Sign up function
//    fun signUp(
//        email: String,
//        password: String,
//        fullName: String,
//        onSuccess: () -> Unit,
//        onError: (String) -> Unit
//    ) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Save user data to database
//                    val userId = auth.currentUser?.uid ?: ""
//                    val userData = mapOf(
//                        "fullName" to fullName,
//                        "email" to email
//                    )
//
//                    database.child("users").child(userId).setValue(userData)
//                        .addOnSuccessListener {
//                            onSuccess()
//                        }
//                        .addOnFailureListener { error ->
//                            onError(error.message ?: "Failed to save user data")
//                        }
//                } else {
//                    onError(task.exception?.message ?: "Sign up failed")
//                }
//            }
//    }
//
//    // Login function
//    fun login(
//        email: String,
//        password: String,
//        onSuccess: () -> Unit,
//        onError: (String) -> Unit
//    ) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    onSuccess()
//                } else {
//                    onError(task.exception?.message ?: "Login failed")
//                }
//            }
//    }
//
//    // Check if user is already logged in
//    fun isUserLoggedIn(): Boolean {
//        return auth.currentUser != null
//    }
//
//    // Get current user
//    fun getCurrentUser(): FirebaseUser? {
//        return auth.currentUser
//    }
//}

// controllers/AuthController.kt - ADD ONLY THE NEW FUNCTION
package com.example.krizenfoods.controllers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

// ✅ Use regular Firebase classes (NOT ktx) for better compatibility

class AuthController {
    // Firebase instances
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    // Sign up function
    fun signUp(
        email: String,
        password: String,
        fullName: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Save user data to database
                    val userId = auth.currentUser?.uid ?: ""
                    val userData = mapOf(
                        "fullName" to fullName,
                        "email" to email
                    )

                    database.child("users").child(userId).setValue(userData)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { error ->
                            onError(error.message ?: "Failed to save user data")
                        }
                } else {
                    onError(task.exception?.message ?: "Sign up failed")
                }
            }
    }

    // Login function
    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Login failed")
                }
            }
    }

    // NEW: Forgot Password function
    fun sendPasswordResetEmail(
        email: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Failed to send reset email")
                }
            }
    }

    // Check if user is already logged in
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    // Get current user
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}