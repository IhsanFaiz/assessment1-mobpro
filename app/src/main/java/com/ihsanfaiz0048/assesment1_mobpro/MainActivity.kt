package com.ihsanfaiz0048.assesment1_mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ihsanfaiz0048.assesment1_mobpro.ui.screen.MainScreen
import com.ihsanfaiz0048.assesment1_mobpro.ui.theme.Assesment1mobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemDark = isSystemInDarkTheme()
            var isDarkMode by remember { mutableStateOf(systemDark) }

            Assesment1mobproTheme(
                isDarkMode = isDarkMode
            ) {
                MainScreen(isDarkMode = isDarkMode, onToggleDarkMode = {isDarkMode = !isDarkMode})
            }
        }
    }
}