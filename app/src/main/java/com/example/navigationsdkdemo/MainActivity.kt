package com.example.navigationsdkdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationsdkdemo.presentation.AuthScreen
import com.example.navigationsdkdemo.presentation.GoogleNavigationScreen
import com.example.navigationsdkdemo.ui.theme.NavigationSDKDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSDKDemoTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "google") {
        composable("auth") {
            AuthScreen(navController)
        }
        composable("google") {
            GoogleNavigationScreen(navController)
        }
    }
}