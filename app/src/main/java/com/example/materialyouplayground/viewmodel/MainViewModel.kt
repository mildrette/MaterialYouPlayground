package com.example.materialyouplayground.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.materialyouplayground.model.ThemeMode
import com.example.materialyouplayground.model.ThemeUiState

class MainViewModel : ViewModel() {

    private val _uiState = mutableStateOf(ThemeUiState())
    val uiState = _uiState

    fun setDarkMode() {
        _uiState.value = _uiState.value.copy(
            themeMode = ThemeMode.DARK
        )
    }

    fun setLightMode() {
        _uiState.value = _uiState.value.copy(
            themeMode = ThemeMode.LIGHT
        )
    }

    fun setSystemMode() {
        _uiState.value = _uiState.value.copy(
            themeMode = ThemeMode.SYSTEM
        )
    }
}
