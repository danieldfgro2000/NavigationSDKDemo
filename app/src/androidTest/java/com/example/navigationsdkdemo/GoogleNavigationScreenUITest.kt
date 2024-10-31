package com.example.navigationsdkdemo

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.navigationsdkdemo.presentation.GoogleNavigationScreen
import org.junit.Rule
import org.junit.Test

class GoogleNavigationScreenUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigationScreen_showPermissionDeniedMessage() {
        composeTestRule.setContent {
            GoogleNavigationScreen()
        }

        composeTestRule.onNodeWithText("Navigation permissions not granted").assertExists()
    }

    @Test
    fun navigationScreen_ShowContentAfterPermissionGranted() {
        composeTestRule.setContent {
            GoogleNavigationScreen()
        }

        composeTestRule.onNodeWithText("Navigation SDK Demo").assertDoesNotExist()
    }
}