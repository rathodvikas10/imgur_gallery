package com.example.network.model

import kotlinx.serialization.Serializable

/**
 * Network response wrapper
 * @property status status of the response
 * @property data data of the response
 */
@Serializable
data class NetworkResponse<T>(
    val data: T,
    val success: Boolean,
    val status: Int
)
