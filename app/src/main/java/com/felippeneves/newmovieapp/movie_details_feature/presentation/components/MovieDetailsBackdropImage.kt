package com.felippeneves.newmovieapp.movie_details_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felippeneves.newmovieapp.core.presentation.components.common.AsyncImageUrl

@Composable
fun MovieDetailsBackdropImage(
    imageUrl: String,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        AsyncImageUrl(
            imageUrl = imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun MovieDetailsBackdropImagePreview() {
    MovieDetailsBackdropImage(
        imageUrl = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}