
package com.example.krizenfoods.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun KrizenFoodsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Direct color definitions
    val orangePrimary = Color(0xFFF57C00)
    val orangeLight = Color(0xFFFFAD42)
    val orangeDark = Color(0xFFBB4D00)

    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = orangePrimary,
            secondary = orangeLight,
            tertiary = orangeDark
        )
    } else {
        lightColorScheme(
            primary = orangePrimary,
            secondary = orangeLight,
            tertiary = orangeDark
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}