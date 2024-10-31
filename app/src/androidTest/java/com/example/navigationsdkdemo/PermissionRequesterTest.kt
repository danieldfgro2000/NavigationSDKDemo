package com.example.navigationsdkdemo

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.navigationsdkdemo.util.PermissionRequester
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class PermissionRequesterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun whenPermissionsGranted_onPermissionsGranted_called() {
        val onPermissionsGrantedMock = mock<() -> Unit>()
        val mockLauncher = mock<ActivityResultLauncher<Array<String>>>()

        composeTestRule.setContent {
            PermissionRequester(
                requestNavigationPermissions = true,
                onNavigationPermissionsGranted = onPermissionsGrantedMock,
                permissionLauncher = mockLauncher
            )
        }
        verify(mockLauncher).launch(any())
        verify(onPermissionsGrantedMock).invoke()
    }

    @Test
    fun whenPermissionsNotGranted_showToast() {
        val toastMock = mock(Toast::class.java)
        val handler = Handler(Looper.getMainLooper())

        composeTestRule.setContent {
            PermissionRequester(
                requestNavigationPermissions = true
            )
        }

        handler.post {
            Toast.makeText(context, "Permissions not granted", Toast.LENGTH_LONG).show()
        }

        verify(toastMock).show()
    }
}