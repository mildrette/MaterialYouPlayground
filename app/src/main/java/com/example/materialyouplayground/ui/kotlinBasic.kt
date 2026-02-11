package com.example.materialyouplayground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.materialyouplayground.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KotlinBasic(
    currentTheme: AppTheme,
    onThemeChange: (AppTheme) -> Unit,
    currentLanguage: String = "English",
    onLanguageChange: (String) -> Unit
) {
    var showSettingsDialog by remember { mutableStateOf(false) }

    // Show Settings Dialog
    if (showSettingsDialog) {
        SettingsDialog(
            currentTheme = currentTheme,
            onThemeSelected = { theme -> onThemeChange(theme) },
            currentLanguage = currentLanguage,
            onLanguageSelected = { lang -> onLanguageChange(lang) },
            onReset = {
                onThemeChange(AppTheme.SYSTEM)
                onLanguageChange("English")
            },
            onDismiss = { showSettingsDialog = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                title = { Text("Material You App") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle menu click */ }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    ThreeDotsMenu(onSettingsClicked = { showSettingsDialog = true })
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome to Material You Playground!",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Current Theme: ${currentTheme.name}",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                "Current Language: $currentLanguage",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun ThreeDotsMenu(
    onSettingsClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Settings") },
            onClick = { onSettingsClicked(); expanded = false }
        )
        DropdownMenuItem(
            text = { Text("About") },
            onClick = { expanded = false }
        )
    }
}

@Composable
fun SettingsDialog(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onReset: () -> Unit,
    onDismiss: () -> Unit
) {
    var showThemeDialog by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }

    // Nested dialogs for theme and language
    if (showThemeDialog) {
        ThemeSelectionDialog(
            currentTheme = currentTheme,
            onThemeSelected = { theme -> onThemeSelected(theme); showThemeDialog = false },
            onDismiss = { showThemeDialog = false }
        )
    }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLanguage = currentLanguage,
            onLanguageSelected = { lang -> onLanguageSelected(lang); showLanguageDialog = false },
            onDismiss = { showLanguageDialog = false }
        )
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Settings") },
        text = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showThemeDialog = true }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Theme", modifier = Modifier.weight(1f))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showLanguageDialog = true }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Language", modifier = Modifier.weight(1f))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onReset() }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Reset to Default", modifier = Modifier.weight(1f))
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Close")
            }
        }
    )
}

@Composable
fun ThemeSelectionDialog(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Choose Theme") },
        text = {
            Column {
                AppTheme.values().forEach { theme ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onThemeSelected(theme); onDismiss() }
                    ) {
                        RadioButton(
                            selected = currentTheme == theme,
                            onClick = { onThemeSelected(theme); onDismiss() }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(theme.name.replaceFirstChar { it.uppercase() })
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}

@Composable
fun LanguageSelectionDialog(
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val languages = listOf("English", "French", "Spanish")
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Choose Language") },
        text = {
            Column {
                languages.forEach { lang ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onLanguageSelected(lang); onDismiss() }
                    ) {
                        RadioButton(
                            selected = currentLanguage == lang,
                            onClick = { onLanguageSelected(lang); onDismiss() }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(lang)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}
