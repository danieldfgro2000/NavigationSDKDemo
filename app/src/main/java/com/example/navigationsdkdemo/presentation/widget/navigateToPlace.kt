package com.example.navigationsdkdemo.presentation.widget

import android.content.Context
import android.util.Log
import com.example.navigationsdkdemo.util.displayMessage
import com.google.android.libraries.navigation.ArrivalEvent
import com.google.android.libraries.navigation.ListenableResultFuture
import com.google.android.libraries.navigation.Navigator
import com.google.android.libraries.navigation.Navigator.RouteStatus.NETWORK_ERROR
import com.google.android.libraries.navigation.Navigator.RouteStatus.NO_ROUTE_FOUND
import com.google.android.libraries.navigation.Navigator.RouteStatus.OK
import com.google.android.libraries.navigation.Navigator.RouteStatus.ROUTE_CANCELED
import com.google.android.libraries.navigation.RoutingOptions
import com.google.android.libraries.navigation.SimulationOptions
import com.google.android.libraries.navigation.Waypoint

fun navigateToPlace(
    context: Context,
    navigator: Navigator,
    placeId: String,
    travelModel: RoutingOptions,
    onArrival: () -> Unit
) {
    var destination: Waypoint
    try {
        destination = Waypoint.Builder().setPlaceIdString(placeId).build()
    } catch (e: Waypoint.UnsupportedPlaceIdException) {
        Log.d("Invalid place ID", e.message.toString())
        return
    }

    val arrivalListener = object : Navigator.ArrivalListener {
        override fun onArrival(p0: ArrivalEvent?) {
            Log.d("Arrival", "Arrived at destination")
            navigator.stopGuidance()
            onArrival()
        }
    }

    val pendingRoute: ListenableResultFuture<Navigator.RouteStatus> =
        navigator.setDestination(destination, travelModel)

    pendingRoute.setOnResultListener(object :
        ListenableResultFuture.OnResultListener<Navigator.RouteStatus> {
        override fun onResult(result: Navigator.RouteStatus) {
            when (result) {
                OK -> {
                    // hide action bar to maximize the navigationUI
                    navigator.setAudioGuidance(Navigator.AudioGuidance.VOICE_ALERTS_AND_GUIDANCE)

                    if (true) {
                        navigator.simulator.simulateLocationsAlongExistingRoute(
                            SimulationOptions().speedMultiplier(15.0F)
                        )
                    }
                    navigator.addArrivalListener(arrivalListener)
                    navigator.startGuidance()
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