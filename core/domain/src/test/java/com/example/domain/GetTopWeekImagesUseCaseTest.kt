package com.example.domain

import androidx.paging.testing.asSnapshot
import com.example.data.repository.NetworkGalleryRepository
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTopWeekImagesUseCaseTest {

    @Test
    fun test_get_top_week_images_use_case() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val fakeDataSource = FakeDataSource()
        val repository = NetworkGalleryRepository(
            testDispatcher,
            fakeDataSource
        )
        val useCase = GetTopWeekImagesUseCase(
            dispatcher = testDispatcher,
            repository = repository
        )
        val result = useCase("").asSnapshot()
        assertEquals(50, result.size)
    }

}