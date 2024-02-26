package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkImgurAlbum(
    val id: String,
    val title: String,
    val datetime: Long,
    val images: List<NetworkImgurImage>? = null
)
