package com.example.model

/**
 * Imgur album model.
 * @property id The ID for the album.
 * @property title The title of the album in the gallery.
 * @property count The total number of images in the album.
 * @property datetime The time inserted into the gallery, epoch time.
 * @property images The images in the album.
 */
data class ImgurAlbum(
    val id: String,
    val title: String,
    val count: Int,
    val datetime: Long,
    val images: List<ImgurImage>
)