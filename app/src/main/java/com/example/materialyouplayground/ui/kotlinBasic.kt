package com.example.materialyouplayground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KotlinBasic() {

    var isDark by remember { mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxSize()
        .padding(25.dp)
        .background(if(isDark) Color.Black else Color.White)
    ){

        Text("Pick and theme Below",
            fontSize = 24.sp,
            color = if (isDark) Color.White else Color.Black)

        Button(onClick = {isDark = !isDark}) {
            Text("Toggle Theme Here")
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Text(if (isDark) "This one is Dark Mode" else "This One is Light Mode",
            fontSize = 24.sp, color = if (isDark) Color.White else Color.Black
        )
    }
}


@Preview
@Composable
fun KotlinBasicPreview(){
    KotlinBasic()
}











//    val name = "Mildred"
//    var count = 0
//    count = 1
//
//    val isDark = true
//    val isLight = false
//
//    if(isDark){
//        Text("this is dark mode")
//    } else {
//        Text("this is light mode")
//    }



