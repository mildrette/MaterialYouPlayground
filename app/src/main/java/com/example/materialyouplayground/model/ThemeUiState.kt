package com.example.materialyouplayground.model

data class ThemeUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColor: Boolean = true
)