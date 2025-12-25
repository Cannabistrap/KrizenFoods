package com.example.krizenfoods.view
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🎉 Welcome to Krizen Foods!",
                fontSize = 28.sp,
                color = Color(0xFFF57C00),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Dashboard Features:",
                        fontSize = 20.sp,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("• View your profile", color = Color.Gray)
                    Text("• Browse food items", color = Color.Gray)
                    Text("• Track orders", color = Color.Gray)
                    Text("• Manage account", color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Firebase Authentication Successful!",
                color = Color.Green,
                fontSize = 16.sp
            )
        }
    }
}