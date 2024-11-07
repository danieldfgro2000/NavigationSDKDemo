package com.example.stripe

interface Api {
    suspend fun login(user:String, psw: String): String?
    suspend fun updateDefaultPayment(customerId: String, paymentId: String)
    suspend fun createEphemeralKey(customerId: String): String
    suspend fun createSetupIntent(customerId: String): String
}