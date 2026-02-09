package com.example.materialyouplayground.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun KotlinBasic(){

    val name = "Mildred"
    var count = 0
    count = 1

    val isDark = true
    val isLight = false

    if(isDark){
        Text("this is dark mode")
    } else {
        Text("this is light mode")
    }



}



@Preview
@Composable
fun KotlinBasicPreview(){
KotlinBasic()
}