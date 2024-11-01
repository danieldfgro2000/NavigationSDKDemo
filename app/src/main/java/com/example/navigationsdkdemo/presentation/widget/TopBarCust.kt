package com.example.navigationsdkdemo.presentation.widget

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom() {
    TopAppBar(
        modifier = Modifier.wrapContentHeight(),
        title = {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = "Google Maps NavigationSDK"
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) // Top bar
}