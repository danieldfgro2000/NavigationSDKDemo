package com.example.navigationsdkdemo.data.model

import com.google.gson.annotations.SerializedName

data class SetDefaultResponse(
    @SerializedName("id") val id: String,
    @SerializedName("object") val objectType: String,
    @SerializedName("billing_details") val billingDetails: BillingDetails,
    @SerializedName("card") val card: Card,
    @SerializedName("created") val created: Long,
    @SerializedName("customer") val customer: String,
    @SerializedName("livemode") val livemode: Boolean,
    @SerializedName("metadata") val metadata: Map<String, Any>,
    @SerializedName("type") val type: String
)
