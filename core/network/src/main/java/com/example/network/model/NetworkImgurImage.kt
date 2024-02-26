package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkImgurImage(
    val id: String,
    val link: String? = null,
    val datetime: Long
)
