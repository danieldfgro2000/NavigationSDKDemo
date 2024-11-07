package com.example.navigationsdkdemo.data.remote.stripe

import com.example.navigationsdkdemo.data.model.CustomerResponse
import com.example.navigationsdkdemo.data.model.PaymentMethodsRequest
import com.example.navigationsdkdemo.data.model.PaymentMethodsResponse
import com.example.navigationsdkdemo.data.model.PaymentSheetRequest
import com.example.navigationsdkdemo.data.model.PaymentSheetResponse
import com.example.navigationsdkdemo.data.model.SetDefaultRequest
import com.example.navigationsdkdemo.data.model.SetDefaultResponse
import com.squareup.okhttp.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {
    @FormUrlEncoded
    @POST("ephemeral_keys")
    suspend fun createEphemeralKey(@FieldMap apiVersionMap: HashMap<String, String>): ResponseBody

    @FormUrlEncoded
    @POST("create_payment_intent")
    suspend fun createPaymentIntent(@FieldMap params: MutableMap<String, String>): ResponseBody

    @FormUrlEncoded
    @POST("create_setup_intent")
    suspend fun createSetupIntent(@FieldMap params: MutableMap<String, String>): ResponseBody

    @POST("/payment-sheet")
    fun paymentSheet(@Body paymentSheetRequest: PaymentSheetRequest): Call<PaymentSheetResponse>

    @POST("/create-anonymous")
    fun createAnonymousCustomer(): Call<CustomerResponse>

    @POST("/list-methods")
    fun getPaymentMethods(@Body paymentMethodsRequest: PaymentMethodsRequest): Call<PaymentMethodsResponse>

    @POST("/set-default")
    fun setDefaultCard(@Body setDefaultRequest: SetDefaultRequest): Call<SetDefaultResponse>
}