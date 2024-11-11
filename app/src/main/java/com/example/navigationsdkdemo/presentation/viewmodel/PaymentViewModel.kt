package com.example.navigationsdkdemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationsdkdemo.data.pay.PaymentResult
import com.example.navigationsdkdemo.data.pay.StripePay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaymentViewModel: ViewModel() {
    val _descriptionText: MutableStateFlow<String> = MutableStateFlow("Payment description goes here")
    val descriptionText: StateFlow<String> = _descriptionText

    val _showProgress: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgress: StateFlow<Boolean> = _showProgress

    fun pay() {
        val amount: Long = 1000
        viewModelScope.launch() {
            _showProgress.value = true
            val result = StripePay.pay(amount)

            _descriptionText.value = when (result) {
                is PaymentResult.Completed -> "Payment completed with id: ${result.paymentId}"
                is PaymentResult.Failed -> "Payment failed! Reason: ${result.error}"
            }
            _showProgress.value = false
        }
    }

    fun clearPayment() {
        _descriptionText.value = "Payment description goes here"
    }
}