package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.common.network.AppDispatchers
import com.example.common.network.Dispatcher
import com.example.model.ImgurAlbum
import com.example.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Paging source for Imgur Gallery
 */
class NetworkGalleryRepository @Inject constructor(
    @Dispatcher(AppDispatchers.IO)
    private val dispatcher: CoroutineDispatcher,
    private val networkDataSource: NetworkDataSource
) : GalleryRepository {

    companion object {
        private const val ELEMENT_PER_PAGE = 25
    }
    override fun searchImage(
        sort: String,
        window: String,
        page: Int,
        type: String,
        query: String
    ): Flow<PagingData<ImgurAlbum>> {

        val galleryPagingSource = GalleryPagingSource(
            query = query,
            initialPage = page,
            type = type,
            window = window,
            sort = sort,
            networkDataSource = networkDataSource
        )

        return Pager(
            config = PagingConfig(
                pageSize = ELEMENT_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { galleryPagingSource }
        ).flow.flowOn(dispatcher)
    }

}