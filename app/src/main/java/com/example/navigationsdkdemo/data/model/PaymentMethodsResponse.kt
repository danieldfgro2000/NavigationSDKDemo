package com.example.navigationsdkdemo.data.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city") val city: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("line1") val line1: String? = null,
    @SerializedName("line2") val line2: String? = null,
    @SerializedName("postal_code") val postalCode: String? = null,
    @SerializedName("state") val state: String? = null
)

data class BillingDetails(
    @SerializedName("address") val address: Address,
    @SerializedName("email") val email: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("phone") val phone: String? = null
)

data class Checks(
    @SerializedName("address_line1_check") val addressLine1Check: String? = null,
    @SerializedName("address_postal_code_check") val addressPostalCodeCheck: String? = null,
    @SerializedName("cvc_check") val cvcCheck: String? = null
)

data class Networks(
    @SerializedName("available") val available: List<String>,
    @SerializedName("preferred") val preferred: String? = null
)

data class ThreeDSecureUsage(
    @SerializedName("supported") val supported: Boolean
)

data class Card(
    @SerializedName("brand") val brand: String,
    @SerializedName("checks") val checks: Checks,
    @SerializedName("country") val country: String,
    @SerializedName("display_brand") val displayBrand: String,
    @SerializedName("exp_month") val expMonth: Int,
    @SerializedName("exp_year") val expYear: Int,
    @SerializedName("fingerprint") val fingerprint: String,
    @SerializedName("funding") val funding: String,
    @SerializedName("generated_from") val generatedFrom: Any? = null,
    @SerializedName("last4") val last4: String,
    @SerializedName("networks") val networks: Networks,
    @SerializedName("three_d_secure_usage") val threeDSecureUsage: ThreeDSecureUsage,
    @SerializedName("wallet") val wallet: Any? = null
)

data class PaymentMethod(
    @SerializedName("id") val id: String,
    @SerializedName("object") val objectType: String,
    @SerializedName("allow_redisplay") val allowRedisplay: String,
    @SerializedName("billing_details") val billingDetails: BillingDetails,
    @SerializedName("card") val card: Card,
    @SerializedName("created") val created: Long,
    @SerializedName("customer") val customer: String,
    @SerializedName("livemode") val livemode: Boolean,
    @SerializedName("metadata") val metadata: Map<String, Any>,
    @SerializedName("radar_options") val radarOptions: Map<String, Any>,
    @SerializedName("type") val type: String
)

data class PaymentMethodsResponse(
    @SerializedName("object") val objectType: String,
    @SerializedName("data") val data: List<PaymentMethod>,
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("url") val url: String
)
