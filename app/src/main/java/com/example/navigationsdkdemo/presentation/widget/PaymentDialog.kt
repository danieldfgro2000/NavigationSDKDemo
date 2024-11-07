package com.example.navigationsdkdemo.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigationsdkdemo.presentation.viewmodel.PaymentViewModel

@Composable
fun PaymentDialog(
    title: String = "Payment Dialog",
    description: String? = null,
    confirmText: String = "Confirm",
    dismissText: String = "Dismiss",
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {

    val viewModel: PaymentViewModel = viewModel()

    val descriptionText = viewModel.descriptionText.collectAsState()
    val showProgress = viewModel.showProgress.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
            .width(screenWidth * 0.9f)
            .padding(16.dp)
            .height(screenHeight * 0.3f),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface,
            text = title,
            style = MaterialTheme.typography.labelLarge
        )

        if (showProgress.value) {
            Text(
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface,
                text = "Processing payment...",
                style = MaterialTheme.typography.labelMedium
            )

        } else {
            Text(
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface,
                text = description ?: descriptionText.value,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                onClick = {
                    viewModel.pay()
                    onConfirm()
                }) {
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = confirmText
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                onClick = { onDismiss() }) {
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = dismissText
                )
            }
        }
    }
}

@Preview()
@Composable
fun PaymentDialogPreview() {
    PaymentDialog()
}