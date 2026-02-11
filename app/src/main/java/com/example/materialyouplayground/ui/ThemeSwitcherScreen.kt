package com.example.materialyouplayground.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.materialyouplayground.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSwitcherScreen(
    currentMode: AppTheme,
    onModeSelected: (AppTheme) -> Unit,
    onOpenSettings: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopAppBar(
                title = { Text("Material You Demo") },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = {
                                showMenu = false
                                onOpenSettings()
                            }
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Choose a theme:", style = MaterialTheme.typography.titleMedium)

            ThemeButton(
                text = "Light Mode",
                selected = currentMode == AppTheme.LIGHT
            ) { onModeSelected(AppTheme.LIGHT) }

            ThemeButton(
                text = "Dark Mode",
                selected = currentMode == AppTheme.DARK
            ) { onModeSelected(AppTheme.DARK) }

            ThemeButton(
                text = "System Default",
                selected = currentMode == AppTheme.SYSTEM
            ) { onModeSelected(AppTheme.SYSTEM) }
        }
    }
}

@Composable
fun ThemeButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(text)
    }
}
