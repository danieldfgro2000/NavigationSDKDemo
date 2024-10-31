package com.example.navigationsdkdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.navigationsdkdemo.presentation.GoogleNavigationScreen
import com.example.navigationsdkdemo.ui.theme.NavigationSDKDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSDKDemoTheme {
                GoogleNavigationScreen()
            }
        }
    }
}