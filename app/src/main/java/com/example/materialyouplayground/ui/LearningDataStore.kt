package com.example.materialyouplayground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.materialyouplayground.R
import com.example.materialyouplayground.SharedPrefsHelper
import com.example.materialyouplayground.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnDataStore(
    currentMode: AppTheme,
    currentLanguage: String?,
    onModeSelected: (AppTheme) -> Unit,
    onLanguageSelected: (String?) -> Unit,
    onBack: () -> Unit,
    onOpenSettings: () -> Unit
) {
    val context = LocalContext.current
 var isDarkMode by remember {   mutableStateOf(SharedPrefsHelper.getTheme(context)) }
    var showMenu by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(WindowInsets.safeDrawing.asPaddingValues())
        ) {

            TopAppBar(
                title = { Text(stringResource(R.string.Material_You_Design_Test)) },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.Settings))},
                            onClick = {
                                showMenu = false
                                onOpenSettings()
                            }
                        )
                    }
                }
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.large),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Text( stringResource(R.string.Learning_how_to_store_data_using_SharedPreferences),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text("Current Theme: ${currentMode.name}", style = MaterialTheme.typography.bodyMedium)

                    Text(
                        text = stringResource(R.string.Current_Theme, currentMode.name),
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }


        }
    }





