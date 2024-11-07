package com.example.navigationsdkdemo.data.model

import com.google.gson.annotations.SerializedName

data class PaymentSheetResponse(
    @SerializedName("paymentIntent") val paymentIntent: String = "",
    @SerializedName("customer") val customer: String = "",
    @SerializedName("ephemeralKey") val ephemeralKey: String = "",
    @SerializedName("publishableKey") val publishableKey: String = "",
)
