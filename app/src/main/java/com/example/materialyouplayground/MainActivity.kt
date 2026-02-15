package com.example.materialyouplayground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import com.example.materialyouplayground.ui.LearnDataStore
import com.example.materialyouplayground.ui.Settings
import com.example.materialyouplayground.ui.theme.MyAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val savedLanguage = SharedPrefsHelper.getLanguage(this) ?: "en"
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(savedLanguage)
        )

        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            var currentScreen by remember { mutableStateOf("theme") }
            var currentTheme by remember { mutableStateOf(SharedPrefsHelper.getTheme(context)) }
            var currentLanguage by remember { mutableStateOf(SharedPrefsHelper.getLanguage(context) ?: "en") }

            MyAppTheme(theme = currentTheme) {
                when (currentScreen) {
                    "theme" -> LearnDataStore(
                        currentMode = currentTheme,
                        currentLanguage = currentLanguage,
                        onModeSelected = { newTheme ->
                            currentTheme = newTheme
                            SharedPrefsHelper.saveTheme(context, newTheme)
                        },
                        onLanguageSelected = { lang ->
                            changeAppLanguage(lang ?: "en")
                        },
                        onBack = {},
                        onOpenSettings = { currentScreen = "settings" }
                    )

                    "settings" -> Settings(
                        currentMode = currentTheme,
                        currentLanguage = currentLanguage,
                        onModeSelected = { newTheme ->
                            currentTheme = newTheme
                            SharedPrefsHelper.saveTheme(context, newTheme)
                        },
                        onLanguageSelected = { lang ->
                            changeAppLanguage(lang)
                        },
                        onBack = { currentScreen = "theme" }
                    )
                }
            }
        }
    }
}

fun changeAppLanguage(langCode: String) {
    val currentLocales = AppCompatDelegate.getApplicationLocales()
    val currentLang = currentLocales.toLanguageTags()

    if (currentLang != langCode) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(langCode)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}
