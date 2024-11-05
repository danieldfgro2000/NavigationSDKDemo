package com.example.navigationsdkdemo.data.service.ayden


import com.adyen.checkout.components.model.paymentmethods.PaymentMethod
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import org.json.JSONObject
import java.io.File
import java.util.concurrent.TimeUnit

class CheckOutApiService {

    companion object {
        @Volatile
        private var instance: CheckOutApiService? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CheckOutApiService().also { instance = it }
            }
    }

    private var lastPaymentData: String? = null

    private val queue: RequestQueue by lazy {
        val cache = DiskBasedCache(File("volley"))
        val network = BasicNetwork(HurlStack())
        RequestQueue(cache, network).apply {
            start()
        }
    }

    private val baseURL: String by lazy {
        "https://checkoutshopper-test.adyen.com/checkoutshopper/demo"
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        queue.add(req)
    }

    fun getConfig(
        resultListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val url = "$baseURL/getConfig"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            resultListener,
            errorListener
        )
        queue.add(request)
    }

    fun getPaymentMethods(
        resultListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val url = "$baseURL/getPaymentMethods"
        val request = JsonObjectRequest(
            Request.Method.POST, url, null,
            resultListener,
            errorListener
        )
        queue.add(request)
    }

    fun filterPaymentMethodsByType(
        ls: MutableList<PaymentMethod>?,
        type: ComponentType
    ): PaymentMethod? {
        return if (ls != null) {
            val f = ls.filter { it.type == type.id }
            if (f.isNotEmpty()) f[0] else null
        } else null
    }

    fun initPayment(
        req: JSONObject,
        resultListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val url = "$baseURL/initiatePayment"
        val request = JsonObjectRequest(
            Request.Method.POST, url, req.getJSONObject("paymentMethod"),
            Response.Listener { response ->
                if (response.has("paymentData")) {
                    lastPaymentData = response.getString("paymentData")
                }
                resultListener.onResponse(response)
            },
            errorListener

        )
        queue.add(request)
    }

    fun initPayment(req: JSONObject, type: String): JSONObject {
        return makeSyncRequest(
            "$baseURL/initiatePayment?type=$type",
            req.getJSONObject("paymentMethod")
        )
    }

    fun submitAdditionalDetails(
        req: JSONObject,
        resultListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val url = "$baseURL/submitAdditionalDetails"
        if (!req.has("paymentData") && lastPaymentData != null) {
            req.put("paymentData", lastPaymentData)
            lastPaymentData = null
        }
        val request = JsonObjectRequest(
            Request.Method.POST, url, req,
            resultListener,
            errorListener
        )
        queue.add(request)
    }

    fun submitAdditionalDetails(req: JSONObject): JSONObject {
        return makeSyncRequest(
            "$baseURL/submitAdditionalDetails",
            req
        )
    }

    private fun makeSyncRequest(url: String, req: JSONObject): JSONObject {
        val future = RequestFuture.newFuture<JSONObject>()
        val request = JsonObjectRequest(Request.Method.POST, url, req, future, future)
        queue.add(request)
        return future.get(5, TimeUnit.SECONDS)
    }
}