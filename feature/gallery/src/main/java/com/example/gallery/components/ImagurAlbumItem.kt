package com.example.gallery.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion.then
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.components.AsyncImage
import com.example.designsystem.theme.Dimens
import com.example.model.ImgurAlbum
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImgurAlbumItem(
    modifier: Modifier = Modifier,
    isGrid: Boolean = false,
    album: ImgurAlbum,
) {
    val fixAspectRatio = Modifier.also {
        if (isGrid) then(Modifier.aspectRatio(1f))
    }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(fixAspectRatio)
                    .heightIn(min = 200.dp, max = 200.dp),
                imageUrl = album.images.firstOrNull()?.url ?: ""
            )

            Text(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(Dimens.ps1)
                    .align(Alignment.BottomStart),
                text = album.datetime.toFormattedDate(),
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .padding(Dimens.p1)
                    .padding(end = Dimens.p1)
                    .circularBackground()
                    .align(Alignment.TopEnd),
                text = album.count.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.p1)
                .basicMarquee(),
            text = album.title,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}


private fun Modifier.circularBackground(): Modifier {
    return drawBehind {
        drawCircle(
            color = Color.Black.copy(alpha = 0.7f),
            radius = this.size.maxDimension * 0.6f,
        )
        drawCircle(
            color = Color.White,
            radius = this.size.maxDimension * 0.6f,
            alpha = 1f,
            style = Stroke(
                width = 5f
            )
        )
    }
}

private fun Long.toFormattedDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.getDefault())
    return format.format(date)
}

@Preview(showBackground = true)
@Composable
private fun ImgurAlbumItemPreview() {
    val album = ImgurAlbum(
        id = "1212", title = "Some title",
        datetime = Date().time, images = arrayListOf(), count = 1
    )
    ImgurAlbumItem(
        album = album,
        isGrid = true
    )
}