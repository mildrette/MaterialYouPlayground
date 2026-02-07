package com.example.materialyouplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.materialyouplayground.model.ThemeMode
import com.example.materialyouplayground.ui.ThemeSwitcherScreen
import com.example.materialyouplayground.ui.theme.AppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var themeMode by rememberSaveable {
                mutableStateOf(ThemeMode.SYSTEM)
            }

            AppTheme(themeMode) {
                ThemeSwitcherScreen(
                    currentMode = themeMode,
                    onModeSelected = { themeMode = it }
                )
            }
        }
    }
}

