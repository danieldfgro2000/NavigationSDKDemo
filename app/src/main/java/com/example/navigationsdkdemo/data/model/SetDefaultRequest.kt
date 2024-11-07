package com.example.navigationsdkdemo.data.model

import com.google.gson.annotations.SerializedName

class SetDefaultRequest(
    @SerializedName("customerId") val customerId: String,
    @SerializedName("paymentMethodId") val paymentMethodId: String
)
