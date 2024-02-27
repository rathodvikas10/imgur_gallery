package com.example.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.designsystem.theme.Dimens
import com.example.gallery.components.AppBar
import com.example.gallery.components.BrowseImages
import com.example.gallery.components.ErrorView
import com.example.gallery.components.ImgurGallery
import com.example.gallery.components.ViewType

@Composable
fun ImgurGalleryScreen(
    modifier: Modifier = Modifier,
    viewModel: GalleryViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    var viewType by rememberSaveable { mutableStateOf(ViewType.LIST) }

    val onViewChange: (ViewType) -> Unit = { type ->
        viewType = type
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AppBar(
            modifier = Modifier.padding(Dimens.p2),
            onViewChange = onViewChange
        ) { query ->
            viewModel.search(query)
        }
        when(val state = uiState) {
            is GalleryUiState.ErrorState -> {
                ErrorView(errorMessage = state.message)
            }
            is GalleryUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is GalleryUiState.Success -> {
                val searchResult = state.galleryImages.collectAsLazyPagingItems()
                val isEmpty = searchResult.itemCount == 0
                if (isEmpty) {
                    BrowseImages()
                } else {
                    ImgurGallery(viewType = viewType, searchResult = searchResult)
                }
            }
            is GalleryUiState.Ideal -> {
                BrowseImages()
            }
        }

    }
}

