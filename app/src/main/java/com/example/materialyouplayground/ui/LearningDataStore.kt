package com.example.materialyouplayground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnDataStore() {

    var showMenu by remember { mutableStateOf(false) }
    var showSettingsScreen by remember { mutableStateOf(false) }

    if (showSettingsScreen) {
        Settings(
            onBack = { showSettingsScreen = false }
        )
    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(WindowInsets.safeDrawing.asPaddingValues())
        ) {

            TopAppBar(
                title = { Text("NthMail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu"
                        )
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = {
                                showMenu = false
                                showSettingsScreen = true
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("About") },
                            onClick = {
                                showMenu = false
                            }
                        )
                    }
                }
            )


//=======================
            Spacer(modifier = Modifier.padding(16.dp))

            Card(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large)
                    .size(width = 360.dp, height = 100.dp)
            ) {
                Text(
                    text = "Learning how to store data using SharedPreferences",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    onBack: () -> Unit = {}
) {
    var selectedTheme by remember { mutableStateOf("System") }
    var selectedLanguage by remember { mutableStateOf("English") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {

        TopAppBar(
            title = { Text("Settings") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Theme",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        SettingOption(
            text = "System",
            selected = selectedTheme == "System",
            onClick = { selectedTheme = "System" }
        )

        SettingOption(
            text = "Light",
            selected = selectedTheme == "Light",
            onClick = { selectedTheme = "Light" }
        )

        SettingOption(
            text = "Dark",
            selected = selectedTheme == "Dark",
            onClick = { selectedTheme = "Dark" }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Language",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        SettingOption(
            text = "English",
            selected = selectedLanguage == "English",
            onClick = { selectedLanguage = "English" }
        )

        SettingOption(
            text = "French",
            selected = selectedLanguage == "French",
            onClick = { selectedLanguage = "French" }
        )

        SettingOption(
            text = "Spanish",
            selected = selectedLanguage == "Spanish",
            onClick = { selectedLanguage = "Spanish" }
        )
    }
}



@Composable
fun SettingOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}



@Preview
@Composable
fun LearnDataStorePreview(){
    LearnDataStore()
}
