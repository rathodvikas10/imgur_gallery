package com.example.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.designsystem.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import coil.compose.AsyncImage as CoilImage

@Composable
fun AsyncImage(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop
) {
    if(shouldLoadImage(imageUrl)) {
        CoilImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .dispatcher(Dispatchers.IO)
                .diskCacheKey(imageUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.core_designsystem_placeholder),
            error = painterResource(R.drawable.core_designsystem_image_error),
            contentDescription = stringResource(R.string.core_designsystem_album_cover_image),
            contentScale = contentScale,
            modifier = modifier.fillMaxWidth()
        )
    } else {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .shimmerAnimation()
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun shouldLoadImage(key: String): Boolean {
    var load by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val hasCache = context.imageLoader.diskCache?.openSnapshot(key) != null
    LaunchedEffect(Unit) {
        scope.launch {
            if(hasCache) {
                load = true
            } else {
                delay(1000)
                load = true
            }
        }
    }
    return load
}

@Preview
@Composable
private fun AsyncImagePreview() {
    AsyncImage(
        modifier = Modifier,
        imageUrl = ""
    )
}