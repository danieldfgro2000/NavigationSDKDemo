package com.example.navigationsdkdemo.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationsdkdemo.data.local.UserPreferences
import com.example.navigationsdkdemo.data.model.CustomerResponse
import com.example.navigationsdkdemo.data.remote.stripe.APIFactory
import com.example.navigationsdkdemo.data.remote.stripe.SimulatedApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(
    app: Application
) : AndroidViewModel(app) {
    private val api = APIFactory().create()
    private val simulatedApi = SimulatedApi()
    val pref: UserPreferences = UserPreferences(app.applicationContext)

    val _userName: MutableStateFlow<String> = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    val _password: MutableStateFlow<String> = MutableStateFlow("")
    val password: StateFlow<String> = _password

    val _displayError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val displayError: StateFlow<Boolean> = _displayError

    val _errorMessage: MutableStateFlow<String> = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setPassword(pass: String) {
        _password.value = pass
    }

    fun setDisplayError(display: Boolean) {
        _displayError.value = display
    }

    fun login() {
        viewModelScope.launch() {
            val customer = simulatedApi.login(userName.value, password.value)
            customer.onSuccess {
                pref.saveUserId(it.id)
                val paymentId = it.invoiceSettings.defaultPaymentMethod as String? ?: ""
                pref.saveFavouritePaymentMethodId(paymentId)
            }.onFailure {
                _displayError.value = true
                _errorMessage.value = it.message ?: ""
            }
        }
    }

    fun createCustomer() {
        api.createAnonymousCustomer().enqueue(object : Callback<CustomerResponse> {
            override fun onResponse(call: Call<CustomerResponse>, response: Response<CustomerResponse>) {
                Log.d("API", "Customer created ${response.body()}")
                if (response.isSuccessful) {
                    val customer = response.body()
                    pref.saveUserId(customer?.id ?: "")
                    val paymentId = customer?.invoiceSettings?.defaultPaymentMethod as String? ?: ""
                    pref.saveFavouritePaymentMethodId(paymentId)
                }
            }

            override fun onFailure(call: Call<CustomerResponse>, t: Throwable) {
                Log.e("API", "${t.message}")
                _displayError.value = true
                _errorMessage.value = t.message ?: ""
            }
        })
    }
}