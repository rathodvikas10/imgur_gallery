package com.example.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class GalleryPagingSourceTest {

    @Test
    fun load_returns_page_onSuccessful_load() = runTest {
        val fakeDataSource = FakeDataSource()
        val pageSource = GalleryPagingSource(
            query = "",
            initialPage = 1,
            type = "",
            networkDataSource = fakeDataSource,
            sort = "",
            window = ""
        )
        val testPager = TestPager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false
            ),
            pageSource
        )
        val result = testPager.refresh() as PagingSource.LoadResult.Page

        assertEquals(50, result.data.size)
        assertEquals(2, result.nextKey)
        assertEquals(null, result.prevKey)
    }

    @Test
    fun test_consecutive_loads() = runTest {
        val fakeDataSource = FakeDataSource()
        val pageSource = GalleryPagingSource(
            query = "",
            initialPage = 1,
            type = "",
            networkDataSource = fakeDataSource,
            sort = "",
            window = ""
        )
        val testPager = TestPager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false
            ),
            pageSource
        )
        val page = with(testPager) {
            refresh()
            append()
            append()
        } as PagingSource.LoadResult.Page

        assertEquals(4, page.nextKey)
    }
}