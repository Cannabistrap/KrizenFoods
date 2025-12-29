package com.example.krizenfoods.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminDashboardViewModel : ViewModel() {

    var totalUsers by mutableStateOf("0")
    var totalOrders by mutableStateOf("0")
    var isLoading by mutableStateOf(true)

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

        // Fetch total orders count (if you have orders in database)
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
}