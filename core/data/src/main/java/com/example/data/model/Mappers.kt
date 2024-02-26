package com.example.data.model

import com.example.model.ImgurAlbum
import com.example.model.ImgurImage
import com.example.network.model.NetworkImgurAlbum
import com.example.network.model.NetworkImgurImage

/**
 * Convert NetworkImgurAlbum to ImgurAlbum
 */
fun NetworkImgurAlbum.toAlbum() = ImgurAlbum(
    id = id,
    title = title,
    datetime = datetime,
    count = images?.size ?: 0,
    images = images?.map { it.toImage() } ?: arrayListOf()
)

/**
 * Convert NetworkImgurImage to ImgurImage
 */
fun NetworkImgurImage.toImage() = ImgurImage(
    id = id,
    datetime = datetime,
    url = link ?: ""
)