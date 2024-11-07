package com.example.navigationsdkdemo.data.model

import com.google.gson.annotations.SerializedName

data class EphemeralKeyResponse (
    @SerializedName("created") var created: Long? = null,
    @SerializedName("expires") var expires: Long? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("livemode") var liveMode: Boolean? = null,
    @SerializedName("object") var objectType: String? = null,
    @SerializedName("secret") var secret: String? = null
)
