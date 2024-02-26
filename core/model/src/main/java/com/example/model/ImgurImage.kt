package com.example.model

/**
 * Imgur image model
 * @property id image id
 * @property title image title
 * @property url image url
 * @property datetime image created or updated datetime
 */
data class ImgurImage(
    val id: String,
    val title: String,
    val url: String,
    val datetime: Long
)
