package com.example.navigationsdkdemo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.navigationsdkdemo.util.PermissionRequester


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleNavigationScreen() {

    val context = LocalContext.current
//    NavigationApi
    val isNavigationPermissionGranted = remember { mutableStateOf(false) }

    PermissionRequester(
        requestNavigationPermissions = isNavigationPermissionGranted.value.not(),
        onNavigationPermissionsGranted = {
            isNavigationPermissionGranted.value = true
        }
    )

    if(isNavigationPermissionGranted.value.not()) {
        return Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Navigation permissions not granted")
        }
    }

    Scaffold(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            TopAppBar(
                modifier = Modifier.wrapContentHeight(),
                title = { Text("Google Maps NavigationSDK") }) // Top bar
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text("Navigation SDK Demo")
            }
        }
    )
}