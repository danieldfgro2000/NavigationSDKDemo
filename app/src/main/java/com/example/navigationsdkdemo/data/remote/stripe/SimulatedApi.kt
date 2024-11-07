package com.example.navigationsdkdemo.data.remote.stripe

import android.util.Log
import com.example.navigationsdkdemo.data.model.CustomerResponse
import com.example.navigationsdkdemo.data.model.EphemeralKeyResponse
import com.example.navigationsdkdemo.data.model.SetupIntentResponse
import com.example.stripe.Api
import com.example.stripe.ApiFactory
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimulatedApi {
    private val api: Api = ApiFactory().create()

    suspend fun login(user:String, psw: String) : Result<CustomerResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val customer =  api.login(user, psw)
                Result.success(Gson().fromJson(customer, CustomerResponse::class.java))
            } catch (e: Throwable) {
                Log.e("API", "${e.message}")
                Result.failure(e)
            }
        }
    }

    suspend fun savePreferredPayment(customerId: String, paymentId: String) {
        return withContext(Dispatchers.IO) {
            try {
                api.updateDefaultPayment(customerId, paymentId)
            } catch (e: Throwable) {
                Log.e("API", "${e.message}")
            }
        }
    }

    suspend fun createEphemeralKey(customerId: String) : Result<EphemeralKeyResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val key = api.createEphemeralKey(customerId)
                Result.success(Gson().fromJson(key, EphemeralKeyResponse::class.java))
            } catch (e: Throwable) {
                Log.e("API", "${e.message}")
                Result.failure(e)
            }
        }
    }

    suspend fun createSetupIntent(customerId: String) : Result<SetupIntentResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val key = api.createSetupIntent(customerId)
                Result.success(Gson().fromJson(key, SetupIntentResponse::class.java))
            } catch (e: Throwable) {
                Log.e("API", "${e.message}")
                Result.failure(e)
            }
        }
    }
}