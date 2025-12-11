package com.example.krizenfoods.view



import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.krizenfoods.R

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit
) {
    var animationScale by remember { mutableStateOf(1f) }
    val infiniteTransition = rememberInfiniteTransition()

    // Create pulsating animation
    val pulseAnimation by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 2000
                1.0f at 0 with LinearEasing
                1.2f at 1000 with LinearEasing
                1.0f at 2000 with LinearEasing
            }
        )
    )

    // Launch effect for navigation after 5 seconds
    LaunchedEffect(Unit) {
        delay(5000L)
        onNavigateToLogin()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.krizenlogo),
                contentDescription = "Krizen Foods Logo",
                modifier = Modifier
                    .size(200.dp)
                    .scale(pulseAnimation)
            )
        }
    }
}