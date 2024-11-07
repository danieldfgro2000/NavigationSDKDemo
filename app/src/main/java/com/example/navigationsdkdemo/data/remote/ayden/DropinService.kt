package com.example.navigationsdkdemo.data.remote.ayden
import com.adyen.checkout.components.core.ActionComponentData
import com.adyen.checkout.components.core.PaymentComponentState
import com.adyen.checkout.dropin.DropInService

class DropinService : DropInService() {
    private var checkoutApiService = CheckOutApiService.getInstance()

//    override fun makePaymentsCall(paymentComponentData: JSONObject): DropInServiceResult {
//        return handlePaymentRequestResult(checkoutApiService.initPayment(paymentComponentData, ComponentType.DROPIN.id))
//    }
//
//    override fun makeDetailsCall(actionComponentData: JSONObject): DropInServiceResult {
//        return handlePaymentRequestResult(checkoutApiService.submitAdditionalDetails(actionComponentData))
//    }
//
//    private fun handlePaymentRequestResult(response: JSONObject): DropInServiceResult {
//        return try {
//            if (response.isNull("action")) {
//                DropInServiceResult.Finished(response.getString("resultCode"))
//            } else {
//                DropInServiceResult.Action(response.getString("action"))
//            }
//        } catch (e: Exception) {
//            DropInServiceResult.Error(e.toString())
//        }
//    }

    override fun onAdditionalDetails(actionComponentData: ActionComponentData) {
        TODO("Not yet implemented")
    }

    override fun onSubmit(state: PaymentComponentState<*>) {
        TODO("Not yet implemented")
    }
}