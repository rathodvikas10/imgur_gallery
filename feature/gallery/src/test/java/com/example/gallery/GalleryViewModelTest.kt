package com.example.gallery

import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.example.data.repository.NetworkGalleryRepository
import com.example.domain.GetTopWeekImagesUseCase
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class GalleryViewModelTest {

    @Test
    fun test_ideal_state() = runTest {
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
        val viewModel = GalleryViewModel(testDispatcher,useCase)
        assertEquals(GalleryUiState.Ideal,viewModel.uiState.value)
    }

    @Test
    fun test_success_state() = runTest {
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
        val viewModel = GalleryViewModel(testDispatcher,useCase)
        viewModel.search("test")
        viewModel.uiState.test {
            assertTrue(awaitItem() is GalleryUiState.Ideal)
            assertTrue(awaitItem() is GalleryUiState.Loading)
            val item = awaitItem()
            assertTrue(item is GalleryUiState.Success)
            val size = item.galleryImages.asSnapshot().size
            assertEquals(50,size)
        }
    }
}