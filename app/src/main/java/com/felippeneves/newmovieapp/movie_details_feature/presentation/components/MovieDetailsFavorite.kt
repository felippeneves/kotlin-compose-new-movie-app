package com.felippeneves.newmovieapp.movie_details_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailsFavorite(
    iconColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = iconColor
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailsFavoritePreview() {
    MovieDetailsFavorite(
        iconColor = Color.Red,
        onClick = {}
    )
}