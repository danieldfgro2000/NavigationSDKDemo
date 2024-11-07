package com.example.navigationsdkdemo.data.model

import com.google.gson.annotations.SerializedName

data class InvoiceSettings(
    @SerializedName("custom_fields") val customFields: Any? = null,
    @SerializedName("default_payment_method") val defaultPaymentMethod: Any? = null,
    @SerializedName("footer") val footer: Any? = null,
    @SerializedName("rendering_options") val renderingOptions: Any? = null
)

data class CustomerResponse(
    @SerializedName("id") val id: String,
    @SerializedName("object") val objectType: String,
    @SerializedName("address") val address: Any? = null,
    @SerializedName("balance") val balance: Int,
    @SerializedName("created") val created: Long,
    @SerializedName("currency") val currency: Any? = null,
    @SerializedName("default_source") val defaultSource: Any? = null,
    @SerializedName("delinquent") val delinquent: Boolean,
    @SerializedName("description") val description: Any? = null,
    @SerializedName("discount") val discount: Any? = null,
    @SerializedName("email") val email: String,
    @SerializedName("invoice_prefix") val invoicePrefix: String,
    @SerializedName("invoice_settings") val invoiceSettings: InvoiceSettings,
    @SerializedName("livemode") val livemode: Boolean,
    @SerializedName("metadata") val metadata: Map<String, Any>,
    @SerializedName("name") val name: String,
    @SerializedName("next_invoice_sequence") val nextInvoiceSequence: Int,
    @SerializedName("phone") val phone: Any? = null,
    @SerializedName("preferred_locales") val preferredLocales: List<Any> = emptyList(),
    @SerializedName("shipping") val shipping: Any? = null,
    @SerializedName("tax_exempt") val taxExempt: String,
    @SerializedName("test_clock") val testClock: Any? = null
)

