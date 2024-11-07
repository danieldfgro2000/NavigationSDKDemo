package com.example.stripe
import com.stripe.Stripe
import com.stripe.model.Customer
import com.stripe.model.EphemeralKey
import com.stripe.model.SetupIntent
import com.stripe.param.CustomerListParams
import com.stripe.param.CustomerUpdateParams
import com.stripe.param.EphemeralKeyCreateParams
import com.stripe.param.SetupIntentCreateParams

internal class ApiEndpoints: Api {

    init {
        Stripe.apiKey = "STRIPE_KEY"
    }

    override suspend fun login(user: String, psw: String): String? {
        val params = CustomerListParams.builder()
            .setEmail(user)
            .build()
        val customers = Customer.list(params)
        return if (customers.data.isEmpty()) "" else customers.data[0].toJson()
    }

    override suspend fun updateDefaultPayment(customerId: String, paymentId: String) {
        val resource = Customer.retrieve(customerId)
        val params = CustomerUpdateParams.builder()
            .setInvoiceSettings(
                CustomerUpdateParams.InvoiceSettings.builder()
                    .setDefaultPaymentMethod(paymentId)
                    .build()
            )
            .build()
        resource.update(params)
    }

    override suspend fun createEphemeralKey(customerId: String): String {
        val paramsEphemeralKey = EphemeralKeyCreateParams.builder()
            .setCustomer(customerId)
            .setStripeVersion(Stripe.API_VERSION)
            .build()
        val key: EphemeralKey = EphemeralKey.create(paramsEphemeralKey)
        return key.toJson()
    }

    override suspend fun createSetupIntent(customerId: String): String {
        val setupIntentParams = SetupIntentCreateParams.builder()
            .setCustomer(customerId)
            .build()
        val setupIntent = SetupIntent.create(setupIntentParams)
        return setupIntent.toJson()
    }
}
