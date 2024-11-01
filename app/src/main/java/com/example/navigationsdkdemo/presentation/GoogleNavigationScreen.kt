package com.example.navigationsdkdemo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.navigationsdkdemo.data.initNavigator
import com.example.navigationsdkdemo.presentation.widget.NavigateToPlace
import com.example.navigationsdkdemo.presentation.widget.PermissionsNotGranted
import com.example.navigationsdkdemo.presentation.widget.TopBarCustom
import com.example.navigationsdkdemo.util.PermissionRequester
import com.google.android.libraries.navigation.NavigationView
import com.google.android.libraries.navigation.Navigator
import com.google.android.libraries.navigation.RoutingOptions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleNavigationScreen() {
    val isNavigationPermissionGranted = remember { mutableStateOf(false) }

    PermissionRequester(
        shouldRequestNavigationPermissions = !isNavigationPermissionGranted.value,
        onNavigationPermissionsGranted = { isNavigationPermissionGranted.value = true }
    )

    Scaffold(
        topBar = { TopBarCustom() },
        content = { paddingValues ->
            if (!isNavigationPermissionGranted.value) {
                PermissionsNotGranted()
            } else {
               NavigationContent(paddingValues)
            }
        }
    )
}

@Composable
fun NavigationContent(paddingValues: PaddingValues) {
    val isNavigationReady = remember { mutableStateOf(false) }
    val mNavigator = remember { mutableStateOf<Navigator?>(null) }

    initNavigator(context = LocalContext.current) { navigator ->
        isNavigationReady.value = navigator != null
        navigator?.let {
            mNavigator.value = it
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { context ->
                NavigationView(context)
            },
            modifier = Modifier.fillMaxSize()
        )
        if (isNavigationReady.value) {
            val mRoutingOptions = RoutingOptions()
            mRoutingOptions.travelMode(RoutingOptions.TravelMode.DRIVING)
            mNavigator.value?.let { navigator ->

                NavigateToPlace(
                    context = LocalContext.current,
                    mNavigator = navigator,
                    placeId = "EgoyMDI0MTAyNy4wIKXMDSoASAFQAw",
                    travelModel = mRoutingOptions
                )
            }
        } else {
            Text("Navigation is not ready")
        }
    }
}




