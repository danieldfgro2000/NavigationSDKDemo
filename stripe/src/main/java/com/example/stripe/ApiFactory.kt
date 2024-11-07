package com.example.stripe

class ApiFactory {

    fun create() : Api {
        return ApiEndpoints()
    }
}