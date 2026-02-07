package com.example.materialyouplayground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.materialyouplayground.model.ThemeMode

@Composable
fun ThemeSwitcherScreen(
    currentMode: ThemeMode,
    onModeSelected: (ThemeMode) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize().fillMaxHeight(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(35.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                    Text(
                        text = "Using Material You",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = "Choose a color theme from below",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }


            Spacer(modifier = Modifier.height(32.dp))

            ThemeButton(
                text = "Light Mode",
                selected = currentMode == ThemeMode.LIGHT
            ) {
                onModeSelected(ThemeMode.LIGHT)
            }

            ThemeButton(
                text = "Dark Mode",
                selected = currentMode == ThemeMode.DARK
            ) {
                onModeSelected(ThemeMode.DARK)
            }

            ThemeButton(
                text = "System Default",
                selected = currentMode == ThemeMode.SYSTEM
            ) {
                onModeSelected(ThemeMode.SYSTEM)
            }
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
            containerColor = if (selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (selected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(text)
    }
}
