package com.example.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

enum class IconSwitch { FIRST, SECOND }

@Composable
fun IconSwitch(
    modifier: Modifier = Modifier,
    imageVectorFirst: ImageVector,
    imageVectorSecond: ImageVector,
    onSwitch: (IconSwitch) -> Unit,
) {
    var iconSwitch by remember { mutableStateOf(IconSwitch.FIRST) }
    Icon(
        modifier = modifier.clickable {
            iconSwitch = if (iconSwitch == IconSwitch.FIRST) IconSwitch.SECOND else IconSwitch.FIRST
            onSwitch(iconSwitch)
        },
        imageVector = if (iconSwitch == IconSwitch.FIRST) imageVectorFirst else imageVectorSecond,
        contentDescription = "Icon switch"
    )
}

@Preview
@Composable
private fun IconSwitchPreview() {
    IconSwitch(
        modifier = Modifier,
        imageVectorFirst = Icons.Filled.Add,
        imageVectorSecond = Icons.Filled.Delete,
        onSwitch = {

        }
    )
}