package com.example.navigationsdkdemo.data.pay

import android.util.Log
import android.os.Bundle
import com.example.navigationsdkdemo.BuildConfig
import com.stripe.Stripe
import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object StripePay {

    init {
        Stripe.apiKey = BuildConfig.STRIPE_API_KEY
    }

    suspend fun pay(amount: Long) : PaymentResult {
        return withContext(Dispatchers.IO) {
            try {
                val paymentIntent = createPaymentIntent(amount)
                val result = paymentIntent.confirm()
                Log.v("payment", result.status)
                when (result.status) {
                    "succeeded" -> PaymentResult.Completed(result.id)
                    else -> PaymentResult.Failed("Payment failed! Reason: ${result.lastPaymentError.message}")
                }
            } catch (e: Exception) {
                Log.e("payment", "$e, message: ${e.message}")
                PaymentResult.Failed("Payment failed! Reason: ${e.message}")
            }
        }
    }

    private fun createPaymentIntent(amount: Long): PaymentIntent {
        val automaticPaymentsMethods = PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
            .setEnabled(true).setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER).build()

        val params = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency("eur")
            .setPaymentMethod("pm_card_visa")
            .setAutomaticPaymentMethods(automaticPaymentsMethods)
            .build()
        return PaymentIntent.create(params)
    }
}

sealed class PaymentResult {
    data class Completed(val paymentId: String) : PaymentResult()
    data class Failed(val error: String) : PaymentResult()
}