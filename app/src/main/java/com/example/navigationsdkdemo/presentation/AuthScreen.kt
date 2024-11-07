package com.example.navigationsdkdemo.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigationsdkdemo.presentation.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen() {

    val viewModel: AuthViewModel = viewModel()
    val context: Context = LocalContext.current

    val userName by viewModel.userName.collectAsState()
    val password by viewModel.password.collectAsState()

    val displayError by viewModel.displayError.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.fillMaxWidth().padding(innerPadding),
                verticalArrangement = Arrangement.Top
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = userName,
                    onValueChange = {
                        viewModel.setUserName(it)
                    },
                    label = { Text("Username") }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    onValueChange = {
                        viewModel.setPassword(it)
                    },
                    label = { Text("Password") }
                )
            }

            if (displayError) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.setDisplayError(false)
            }
        },
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Button(
                    onClick = {
                        viewModel.login()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }
                Button(
                    onClick = {
                        viewModel.createCustomer()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login Anonymous")
                }
            }
        }
    )
}