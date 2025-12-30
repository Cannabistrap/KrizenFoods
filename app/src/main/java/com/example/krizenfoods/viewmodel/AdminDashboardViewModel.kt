//package com.example.krizenfoods.viewmodel
//
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class AdminDashboardViewModel : ViewModel() {
//
//    var totalUsers by mutableStateOf("0")
//    var totalOrders by mutableStateOf("0")
//    var isLoading by mutableStateOf(true)
//
//    private val database = FirebaseDatabase.getInstance().reference
//
//    init {
//        loadAdminData()
//    }
//
//    private fun loadAdminData() {
//        // Fetch total users count
//        database.child("users").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val count = snapshot.childrenCount
//                totalUsers = count.toString()
//                isLoading = false
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                totalUsers = "0"
//                isLoading = false
//            }
//        })
//
//        // Fetch total orders count (if you have orders in database)
//        database.child("orders").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val count = snapshot.childrenCount
//                totalOrders = count.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                totalOrders = "0"
//            }
//        })
//    }
//}


package com.example.krizenfoods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.krizenfoods.model.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminDashboardViewModel : ViewModel() {

    var totalUsers by mutableStateOf("0")
    var totalOrders by mutableStateOf("0")
    var isLoading by mutableStateOf(true)
    var errorMessage by mutableStateOf("")
    var successMessage by mutableStateOf("")

    private val database = FirebaseDatabase.getInstance().reference

    init {
        loadAdminData()
    }

    private fun loadAdminData() {
        // Fetch total users count
        database.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val count = snapshot.childrenCount
                totalUsers = count.toString()
                isLoading = false
            }

            override fun onCancelled(error: DatabaseError) {
                totalUsers = "0"
                isLoading = false
            }
        })

        // Fetch total orders count
        database.child("orders").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val count = snapshot.childrenCount
                totalOrders = count.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                totalOrders = "0"
            }
        })
    }

    // Add Food Function
    fun addFood(
        name: String,
        description: String,
        price: String,
        category: String,
        imageUrl: String,
        isAvailable: Boolean,
        onSuccess: () -> Unit
    ) {
        // Clear previous messages
        errorMessage = ""
        successMessage = ""

        // Validation
        if (name.isBlank()) {
            errorMessage = "Please enter food name"
            return
        }

        if (description.isBlank()) {
            errorMessage = "Please enter description"
            return
        }

        if (price.isBlank()) {
            errorMessage = "Please enter price"
            return
        }

        val priceValue = price.toDoubleOrNull()
        if (priceValue == null || priceValue <= 0) {
            errorMessage = "Please enter a valid price"
            return
        }

        if (imageUrl.isBlank()) {
            errorMessage = "Please enter image URL"
            return
        }

        // Start loading
        isLoading = true

        // Generate unique ID
        val foodId = database.child("foods").push().key ?: return

        // Create Food object
        val food = Food(
            id = foodId,
            name = name,
            description = description,
            price = priceValue,
            category = category,
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        // Save to Firebase
        database.child("foods").child(foodId).setValue(food)
            .addOnSuccessListener {
                isLoading = false
                successMessage = "✅ Food item added successfully!"
                onSuccess()
            }
            .addOnFailureListener { error ->
                isLoading = false
                errorMessage = "Failed: ${error.message}"
            }
    }
}