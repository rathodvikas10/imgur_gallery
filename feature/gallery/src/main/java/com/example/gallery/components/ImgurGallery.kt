package com.example.gallery.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.designsystem.theme.Dimens
import com.example.model.ImgurAlbum

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImgurGallery(
    modifier: Modifier = Modifier,
    viewType: ViewType,
    searchResult: LazyPagingItems<ImgurAlbum>
) {
    val isGrid = viewType == ViewType.GRID
    val columns = when (viewType) {
        ViewType.GRID -> GridCells.Fixed(2)
        ViewType.LIST -> GridCells.Fixed(1)
    }

    LazyVerticalGrid(
        modifier = modifier.testTag("LazyVerticalGrid"),
        columns = columns,
        contentPadding = PaddingValues(vertical = Dimens.p2),
        verticalArrangement = Arrangement.spacedBy(Dimens.p2),
        horizontalArrangement = Arrangement.spacedBy(Dimens.p2)
    ) {
        items(searchResult.itemCount) { index ->
            val album = searchResult[index]
            if (album != null) {
                ImgurAlbumItem(
                    modifier = Modifier.animateItemPlacement(),
                    album = album,
                    isGrid = isGrid
                )
            }
        }
        when(val state = searchResult.loadState.append) {
            is LoadState.Loading -> {
                item {
                    CircularProgressIndicator()
                }
            }
            is LoadState.Error -> {
                item {
                    ErrorView(errorMessage = state.error.message ?: "")
                }
            }
            else -> {}
        }
    }
}