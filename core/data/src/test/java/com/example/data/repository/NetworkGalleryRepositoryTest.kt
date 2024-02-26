package com.example.data.repository

import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class NetworkGalleryRepositoryTest {

    @Test
    fun test_load_gallery_success() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val fakeDataSource = FakeDataSource()
        val repository = NetworkGalleryRepository(
            testDispatcher,
            fakeDataSource
        )

        val pagingData = repository.searchImage(
            sort = "",
            window = "",
            type = "",
            page = 1,
            query = ""
        ).asSnapshot()

        assertEquals(50,pagingData.size)
    }
}