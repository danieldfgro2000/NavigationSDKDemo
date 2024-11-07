package com.example.navigationsdkdemo.data.model

import com.google.gson.annotations.SerializedName

data class SetupIntentResponse (
    @SerializedName("client_secret") var clientSecret: String? = null
)
