package com.example.navigationsdkdemo.data.model

import com.google.gson.annotations.SerializedName

data class PaymentMethodsRequest(
    @SerializedName("customerId") val customerId: String
)
