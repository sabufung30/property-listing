package com.sabufung.data

import java.io.IOException

interface DomainError

data class ServiceError(
    val statusCode: Int,
    val statusMessage: String,
    val details: List<Detail>,
    /**
     * Optional timestamp for when the service error occurred.
     */
    val timestamp: Long = System.currentTimeMillis(),
) : DomainError {

    data class Detail(
        val code: String,
        val message: String,
    )
}
data class IOError(val ioException: IOException) : DomainError
