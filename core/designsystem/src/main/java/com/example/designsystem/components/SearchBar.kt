package com.example.designsystem.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String = "",
    searchLabelText: String,
    onSearch: (String) -> Unit
) {
    var searchValue by rememberSaveable { mutableStateOf(text) }
    OutlinedTextField(
        modifier = modifier,
        value = searchValue,
        onValueChange = {
            searchValue = it
            onSearch(searchValue)
        },
        label = {
            Text(text = searchLabelText)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        singleLine = true
    )
}

@Preview
@Composable
private fun SearchPreview() {
    SearchBar(
        modifier = Modifier,
        searchLabelText = "Search",
        onSearch = {}
    )
}