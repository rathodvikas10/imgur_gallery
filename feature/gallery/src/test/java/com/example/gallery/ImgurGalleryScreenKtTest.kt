package com.example.gallery

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.data.repository.NetworkGalleryRepository
import com.example.domain.GetTopWeekImagesUseCase
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog
import kotlin.test.Test

@RunWith(RobolectricTestRunner::class)
class ImgurGalleryScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        ShadowLog.stream = System.out
    }

    @Test
    fun test_ideal_screen() {
        composeTestRule.setContent {
            ImgurGalleryScreen(viewModel = generateViewModel())
        }
        composeTestRule.onNodeWithText("Search").assertExists()
        composeTestRule.onNodeWithText("Search for top week images").assertExists()
    }

    @Test
    fun test_success_screen() {
        composeTestRule.setContent {
            ImgurGalleryScreen(viewModel = generateViewModel())
        }
        composeTestRule.onNodeWithText("Search").performTextInput("search")
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithTag("LazyVerticalGrid").isDisplayed()
        }
    }

    private fun generateViewModel(): GalleryViewModel {
        val testDispatcher = Dispatchers.Unconfined
        val fakeDataSource = FakeDataSource()
        val repository = NetworkGalleryRepository(
            testDispatcher,
            fakeDataSource
        )
        val useCase = GetTopWeekImagesUseCase(
            dispatcher = testDispatcher,
            repository = repository
        )
        return GalleryViewModel(testDispatcher,useCase)
    }
}