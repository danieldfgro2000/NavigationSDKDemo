package com.example.navigationsdkdemo.data

import android.content.Context
import com.example.navigationsdkdemo.MainActivity
import com.example.navigationsdkdemo.util.displayMessage
import com.google.android.libraries.navigation.NavigationApi
import com.google.android.libraries.navigation.Navigator
import com.google.android.libraries.navigation.SupportNavigationFragment

fun initNavigator(context: Context, onNavReady: (Navigator?) -> Unit) {
    NavigationApi.getNavigator(
        context as MainActivity,
        object : NavigationApi.NavigatorListener {
            override fun onNavigatorReady(p0: Navigator?) {

                displayMessage("Navigator ready", context)
                onNavReady(p0)
                p0?.setTaskRemovedBehavior(Navigator.TaskRemovedBehavior.QUIT_SERVICE)
            }

            override fun onError(p0: Int) {
                when (p0) {
                    NavigationApi.ErrorCode.NOT_AUTHORIZED -> {
                        displayMessage(
                            "Error loading Navigation SDK: Your API key is invalid or not authorized to use the Navigation SDK",
                            context
                        )
                    }

                    NavigationApi.ErrorCode.TERMS_NOT_ACCEPTED -> {
                        displayMessage(
                            "Error loading Navigation SDK: User did not accept the Navigation Terms of Use.",
                            context
                        )
                    }

                    NavigationApi.ErrorCode.NETWORK_ERROR -> {
                        displayMessage("Error loading Navigation SDK: Network error.", context)
                    }

                    NavigationApi.ErrorCode.LOCATION_PERMISSION_MISSING -> {
                        displayMessage(
                            "Error loading Navigation SDK: Location permission missing.",
                            context
                        )
                    }

                    else -> {
                        displayMessage("Error loading Navigation SDK: Unknown error $p0.", context)
                    }
                }
            }
        }
    )
    withMapAsync {

    }
}
private fun withMapAsync(block: InitializeMapScope.() -> Unit) {
    SupportNavigationFragment().getMapAsync { map ->
        object : InitializeMapScope {
            override val map = map
        }
            .block()
    }
}