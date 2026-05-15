package com.nammamistri.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.nammamistri.app.ui.navigation.NavGraph
import com.nammamistri.app.ui.theme.NammaMistriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }
            NammaMistriTheme(darkTheme = isDarkMode) {
                NavGraph(
                    onToggleDarkMode = { isDarkMode = !isDarkMode },
                    isDarkMode = isDarkMode
                )
            }
        }
    }
}