//
//package com.example.krizenfoods.viewmodel
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
//    var isAdmin by mutableStateOf(false)  // NEW: Check if user is admin
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
//
//            // Check if user is admin
//            isAdmin = userEmail == "admin@gmail.com"
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
import com.example.krizenfoods.model.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DashboardViewModel : ViewModel() {

    var userName by mutableStateOf("User")
    var userEmail by mutableStateOf("")
    var isLoading by mutableStateOf(true)
    var isAdmin by mutableStateOf(false)

    // Food related states
    var allFoods by mutableStateOf<List<Food>>(emptyList())
    var foodsByCategory by mutableStateOf<Map<String, List<Food>>>(emptyMap())
    var isFoodsLoading by mutableStateOf(true)

    private val authController = AuthController()
    private val database = FirebaseDatabase.getInstance().reference

    init {
        loadUserData()
        loadFoods()
    }

    private fun loadUserData() {
        val currentUser = authController.getCurrentUser()
        if (currentUser != null) {
            userEmail = currentUser.email ?: ""
            userName = userEmail.substringBefore("@").replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
            isAdmin = userEmail == "admin@gmail.com"
        }
        isLoading = false
    }

    private fun loadFoods() {
        isFoodsLoading = true

        database.child("foods").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val foods = mutableListOf<Food>()

                for (foodSnapshot in snapshot.children) {
                    val food = foodSnapshot.getValue(Food::class.java)
                    if (food != null && food.isAvailable) {
                        foods.add(food)
                    }
                }

                allFoods = foods

                // Group foods by category
                foodsByCategory = foods.groupBy { it.category }

                isFoodsLoading = false
            }

            override fun onCancelled(error: DatabaseError) {
                isFoodsLoading = false
            }
        })
    }

    fun searchFoods(query: String): List<Food> {
        if (query.isBlank()) return allFoods

        return allFoods.filter { food ->
            food.name.contains(query, ignoreCase = true) ||
                    food.description.contains(query, ignoreCase = true) ||
                    food.category.contains(query, ignoreCase = true)
        }
    }

//    fun onLogoutClick(onSuccess: () -> Unit) {
//        authController.signOut()
//        onSuccess()
//    }
}