package com.sabufung.data.api.retrofit

data class NetworkResponse<T>(
    val results: T,
    val data: T,
)