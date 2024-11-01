package com.example.navigationsdkdemo.presentation.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.navigationsdkdemo.util.displayMessage
import com.google.android.libraries.navigation.ListenableResultFuture
import com.google.android.libraries.navigation.Navigator
import com.google.android.libraries.navigation.Navigator.RouteStatus.NETWORK_ERROR
import com.google.android.libraries.navigation.Navigator.RouteStatus.NO_ROUTE_FOUND
import com.google.android.libraries.navigation.Navigator.RouteStatus.OK
import com.google.android.libraries.navigation.Navigator.RouteStatus.ROUTE_CANCELED
import com.google.android.libraries.navigation.RoutingOptions
import com.google.android.libraries.navigation.SimulationOptions
import com.google.android.libraries.navigation.Waypoint
import com.google.firebase.encoders.json.BuildConfig

@Composable
fun NavigateToPlace(
    context: Context,
    mNavigator: Navigator,
    placeId: String,
    travelModel: RoutingOptions
) {
    var destination: Waypoint
    try {
        destination = Waypoint.Builder().setPlaceIdString(placeId).build()
    } catch (e: IllegalArgumentException) {
        displayMessage("Invalid place ID", LocalContext.current)
        return
    }

    val pendingRoute: ListenableResultFuture<Navigator.RouteStatus> =
        mNavigator.setDestination(destination, travelModel)

    pendingRoute.setOnResultListener(object :
        ListenableResultFuture.OnResultListener<Navigator.RouteStatus> {
        override fun onResult(result: Navigator.RouteStatus) {
            when (result) {
                OK -> {
                    // hide action bar to maximize the navigationUI
                    mNavigator.setAudioGuidance(Navigator.AudioGuidance.VOICE_ALERTS_AND_GUIDANCE)

                    if (BuildConfig.DEBUG) {
                        mNavigator.simulator.simulateLocationsAlongExistingRoute(
                            SimulationOptions().speedMultiplier(5.0F)
                        )
                    }
                    mNavigator.startGuidance()
                }

                NO_ROUTE_FOUND -> {
                    displayMessage("Error Starting navigation: No route found", context)
                }

                NETWORK_ERROR -> {
                    displayMessage("Error Starting navigation: Network error", context)
                }

                ROUTE_CANCELED -> {
                    displayMessage("Error Starting navigation: Route canceled", context)
                }

                else -> {
                    displayMessage("Error Starting navigation ${result.name}", context)
                }
            }
        }
    })
}