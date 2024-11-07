package com.example.navigationsdkdemo.data.local

import android.content.Context

class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val USER_ID_KEY = "user_id"
        private const val PAYMENT_ID_KEY = "payment_id"
    }

    fun saveUserId(userId: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_ID_KEY, userId)
        editor.apply()
    }

    fun saveFavouritePaymentMethodId(paymentId: String) {
        val editor = sharedPreferences.edit()
        editor.putString(PAYMENT_ID_KEY, paymentId)
        editor.apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(USER_ID_KEY, null)
    }

    fun getPaymentId() : String? {
        return sharedPreferences.getString(PAYMENT_ID_KEY, null)
    }
}