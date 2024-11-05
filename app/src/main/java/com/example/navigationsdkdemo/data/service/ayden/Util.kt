package com.example.navigationsdkdemo.data.service.ayden

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

object Util {
    @Composable
    fun ShowInfo(text: String) {
        val snackBarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch() {
                snackBarHostState.showSnackbar(
                    message = text,
                    actionLabel = "Dismiss",
                )
            }
        }
    }

    @Composable
    fun ShowError(text: String) {
        val snackBarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch() {
                snackBarHostState.showSnackbar(
                    message = text,
                    actionLabel = "Dismiss",
                )
            }
        }
    }
}