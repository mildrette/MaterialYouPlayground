package com.example.materialyouplayground.ui

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.materialyouplayground.R
import com.example.materialyouplayground.SharedPrefsHelper
import com.example.materialyouplayground.changeAppLanguage
import com.example.materialyouplayground.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    currentMode: AppTheme,
    currentLanguage: String,
    onModeSelected: (AppTheme) -> Unit,
    onLanguageSelected: (String) -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    var selectedLanguage by remember { mutableStateOf(SharedPrefsHelper.getLanguage(context)) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_title)) },
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
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.choose_theme),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            SettingOption(stringResource(R.string.theme_system), currentMode == AppTheme.SYSTEM) {
                onModeSelected(AppTheme.SYSTEM)
            }
            SettingOption(stringResource(R.string.theme_light), currentMode == AppTheme.LIGHT) {
                onModeSelected(AppTheme.LIGHT)
            }
            SettingOption(stringResource(R.string.theme_dark), currentMode == AppTheme.DARK) {
                onModeSelected(AppTheme.DARK)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.language_title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            SettingOption(stringResource(R.string.language_english), selectedLanguage == "en") {
                selectedLanguage = "en"
                SharedPrefsHelper.saveLanguage(context, "en")
                changeAppLanguage("en")
            }

            SettingOption(stringResource(R.string.language_french), selectedLanguage == "fr") {
                selectedLanguage = "fr"
                SharedPrefsHelper.saveLanguage(context, "fr")
                changeAppLanguage("fr")
            }

            SettingOption(stringResource(R.string.language_spanish), selectedLanguage == "es") {
                selectedLanguage = "es"
                SharedPrefsHelper.saveLanguage(context, "es")
                changeAppLanguage("es")
            }
        }
    }
}



@Composable
fun SettingOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(text)
    }
}
