package com.example.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.model.toAlbum
import com.example.model.ImgurAlbum
import com.example.network.NetworkDataSource

/**
 * Network paging source for search results
 */
class GalleryPagingSource(
    private val query: String,
    private val page: Int,
    private val type: String,
    private val window: String,
    private val sort: String,
    private val networkDataSource: NetworkDataSource
) : PagingSource<Int, ImgurAlbum>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImgurAlbum> {
        return try {
            val nextPage = params.key ?: page
            val response = networkDataSource.searchImages(
                sort,
                window,
                nextPage,
                type,
                query
            )
            LoadResult.Page(
                data = response.map { it.toAlbum() },
                prevKey = if (nextPage == page) null else nextPage,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImgurAlbum>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
