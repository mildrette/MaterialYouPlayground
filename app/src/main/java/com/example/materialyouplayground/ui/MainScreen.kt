package com.example.materialyouplayground.ui

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.materialyouplayground.ui.theme.AppTheme
import com.example.materialyouplayground.ui.theme.MyAppTheme

@Composable
fun MainScreen() {
    var currentMode by rememberSaveable { mutableStateOf(AppTheme.SYSTEM) }
    var showSettings by rememberSaveable { mutableStateOf(false) }

    MyAppTheme(theme = currentMode) {
        if (showSettings) {
            Settings(
                currentMode = currentMode,
                onModeSelected = { currentMode = it },
                onBack = { showSettings = false }
            )
        } else {
            ThemeSwitcherScreen(
                currentMode = currentMode,
                onModeSelected = { currentMode = it },
                onOpenSettings = { showSettings = true }
            )
        }
    }
}
