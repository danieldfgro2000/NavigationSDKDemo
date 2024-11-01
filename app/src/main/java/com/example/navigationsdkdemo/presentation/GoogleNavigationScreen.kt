package com.example.navigationsdkdemo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle.Event.ON_ANY
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleEventObserver
import com.example.navigationsdkdemo.data.initNavigator
import com.example.navigationsdkdemo.presentation.widget.PermissionsNotGranted
import com.example.navigationsdkdemo.presentation.widget.TopBarCustom
import com.example.navigationsdkdemo.util.PermissionRequester
import com.google.android.libraries.navigation.NavigationView
import com.google.android.libraries.navigation.Navigator

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
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    val isNavigationReady = remember { mutableStateOf(false) }
    val mNavigator = remember { mutableStateOf<Navigator?>(null) }

    initNavigator(context = context,
        onNavReady = { navigator ->
            isNavigationReady.value = navigator != null
            navigator?.let {
                mNavigator.value = it


            }
        })

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
            modifier = Modifier.fillMaxSize(),
            update = { view ->
                val lifecycleObserver = LifecycleEventObserver { _, event ->
                    when (event) {
                        ON_CREATE -> view.onCreate(null)
                        ON_START -> view.onStart()
                        ON_RESUME -> view.onResume()
                        ON_PAUSE -> view.onPause()
                        ON_STOP -> view.onStop()
                        ON_DESTROY -> view.onDestroy()
                        ON_ANY -> {
                        }
                    }
                }
                lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

            }
        )


    }
}




