package com.example.materialyouplayground.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.materialyouplayground.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    currentMode: AppTheme,
    onModeSelected: (AppTheme) -> Unit,
    onBack: () -> Unit
) {
    var selectedLanguage by remember { mutableStateOf("English") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text("Theme", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp))

            SettingOption("System", currentMode == AppTheme.SYSTEM) { onModeSelected(AppTheme.SYSTEM) }
            SettingOption("Light", currentMode == AppTheme.LIGHT) { onModeSelected(AppTheme.LIGHT) }
            SettingOption("Dark", currentMode == AppTheme.DARK) { onModeSelected(AppTheme.DARK) }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Language", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp))
            SettingOption("English", selectedLanguage == "English") { selectedLanguage = "English" }
            SettingOption("French", selectedLanguage == "French") { selectedLanguage = "French" }
            SettingOption("Spanish", selectedLanguage == "Spanish") { selectedLanguage = "Spanish" }
        }
    }
}

