package com.example.navigationsdkdemo.util

import android.content.Context
import android.widget.Toast

fun displayMessage(message: String, context: Context) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}