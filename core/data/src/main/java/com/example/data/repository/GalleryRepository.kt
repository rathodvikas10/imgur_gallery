package com.example.data.repository

import androidx.paging.PagingData
import com.example.model.ImgurAlbum
import kotlinx.coroutines.flow.Flow

/**
 * Get data from available sources
 */
interface GalleryRepository {
    /**
     * Get paginated data flow of Imgur albums
     */
    fun searchImage(
        sort: String,
        window: String,
        page: Int,
        type: String,
        query: String
    ): Flow<PagingData<ImgurAlbum>>
}
