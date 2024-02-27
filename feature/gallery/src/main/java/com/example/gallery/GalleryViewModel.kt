package com.example.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.common.network.AppDispatchers
import com.example.common.network.Dispatcher
import com.example.common.result.*
import com.example.domain.GetTopWeekImagesUseCase
import com.example.model.ImgurAlbum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    @Dispatcher(AppDispatchers.IO)
    private val dispatcher: CoroutineDispatcher,
    private val useCase: GetTopWeekImagesUseCase
): ViewModel() {

    private val query = Channel<String>()

    @OptIn(FlowPreview::class)
    val uiState: StateFlow<GalleryUiState> = query.consumeAsFlow()
        .debounce(300)
        .flatMapLatest { query ->
            processUiState(query)
        }
        .flowOn(dispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = GalleryUiState.Ideal
        )

    fun search(query: String = "") {
        viewModelScope.launch {
            if(query.isNotEmpty()) {
                this@GalleryViewModel.query.send(query)
            }
        }
    }

    private fun processUiState(query: String) = useCase(query).asResult().map { result ->
        when (result) {
            is Result.Success -> {
                GalleryUiState.Success(flowOf(result.data))
            }
            is Result.Error -> {
                GalleryUiState.ErrorState(
                    result.exception.message ?: "Unknown Error"
                )
            }
            is Result.Loading -> {
                GalleryUiState.Loading
            }
        }
    }
}

sealed interface GalleryUiState {
    data class Success(
        val galleryImages: Flow<PagingData<ImgurAlbum>>
    ) : GalleryUiState
    data class ErrorState(val message: String) : GalleryUiState
    data object Loading : GalleryUiState
    data object Ideal : GalleryUiState
}