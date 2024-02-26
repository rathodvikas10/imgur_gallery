package com.example.domain

import com.example.common.network.AppDispatchers
import com.example.common.network.Dispatcher
import com.example.data.repository.GalleryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Get top album of the week
 */
class GetTopWeekImagesUseCase @Inject constructor(
    @Dispatcher(AppDispatchers.IO)
    private val dispatcher: CoroutineDispatcher,
    private val repository: GalleryRepository
) {
    private companion object{
        const val SORT_BY = "top"
        const val WINDOW = "week"
        const val INITIAL_PAGE = 1
        const val SEARCH_TYPE = "image"
    }
    operator fun invoke(query: String) = repository.searchImage(
        sort = SORT_BY,
        window = WINDOW,
        page = INITIAL_PAGE,
        type = SEARCH_TYPE,
        query = query
    ).flowOn(dispatcher)
}