package com.azat.domain

data class ApiError(
    val statusCode: Int = 0,
    val statusMessage: String? = null
)