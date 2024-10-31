package com.example.navigationsdkdemo.util

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionRequester(
    requestNavigationPermissions: Boolean = false,
    onNavigationPermissionsGranted: () -> Unit = {},
    permissionLauncher: ActivityResultLauncher<Array<String>>? = null
) {
    val navigationPermissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val permissionsToRequest = remember {
        if (requestNavigationPermissions) {
            navigationPermissions
        } else {
            emptyArray()
        }
    }

    val context = LocalContext.current
    val permissionLauncher = permissionLauncher ?: rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { resultPermissions ->
        val navigationPermissionsGranted = navigationPermissions.all {
            resultPermissions[it] == true
        }
        when {
            navigationPermissionsGranted -> onNavigationPermissionsGranted()
            else -> {
                Toast.makeText(
                    context,
                    "Permissions not granted",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    LaunchedEffect(Unit) {
        if (permissionsToRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionsToRequest)
        }
    }
}