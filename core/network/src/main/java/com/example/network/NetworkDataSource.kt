package com.example.network

import com.example.network.model.NetworkImgurAlbum

/**
 * Fetches data from network for Imgur Gallery App
 */
interface NetworkDataSource {

    /**
     * Searches for images
     * @param sort sort order one of [time | viral | top - defaults to time]
     * @param window Change the date range of the request if the sort is 'top', day | week | month | year | all, defaults to all.
     * @param page The data paging number.
     * @param type Show results for any image file type, jpg | png | gif | anigif (animated gif) | album
     * @param query The search query.
     */
    suspend fun searchImages(
        sort: String,
        window: String,
        page: Int,
        type: String,
        query: String
    ): List<NetworkImgurAlbum>
}