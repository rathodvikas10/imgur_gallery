package com.example.gallery.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.components.IconSwitch
import com.example.designsystem.components.SearchBar
import com.example.designsystem.theme.Dimens

enum class ViewType { LIST, GRID }
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    initialText: String = "",
    onViewChange: (ViewType) -> Unit,
    onSearch: (String) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth().weight(1f),
            text = initialText,
            searchLabelText = "Search",
            onSearch = onSearch
        )
        Spacer(modifier = Modifier.width(Dimens.p1))
        IconSwitch(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.outline)
                .size(56.dp)
                .padding(Dimens.p1),
            imageVectorFirst = Icons.Default.GridView,
            imageVectorSecond = Icons.AutoMirrored.Filled.ViewList
        ) { icon ->
            onViewChange(if (icon == IconSwitch.FIRST) ViewType.LIST else ViewType.GRID)
        }
    }
}

@Preview
@Composable
private fun AppBarPreview() {
    AppBar(onViewChange = {}, onSearch = {})
}