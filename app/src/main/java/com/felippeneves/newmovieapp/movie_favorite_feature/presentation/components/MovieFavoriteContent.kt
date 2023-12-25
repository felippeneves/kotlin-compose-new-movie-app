package com.felippeneves.newmovieapp.movie_favorite_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.ui.theme.black
import com.felippeneves.newmovieapp.ui.theme.white

@Composable
fun MovieFavoriteContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    movies: List<Movie>,
    onClick: (id: Int) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(black)
    ) {

        if (movies.isEmpty()) {
            Text(
                text = stringResource(id = R.string.favorite_movies_empty),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = white,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = paddingValues,
                content = {
                    items(
                        items = movies,
                        key = { item: Movie ->
                            item.id
                        }
                    ) { movie ->
                        MovieFavoriteItem(
                            movie = movie,
                            onClick = onClick
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun MovieFavoriteContentPreview() {
    MovieFavoriteContent(
        modifier = Modifier,
        paddingValues = PaddingValues(),
        movies = listOf(
            Movie(
                id = 1,
                title = "Spider-Man: Far From Home",
                imageUrl = ""
            ),
            Movie(
                id = 2,
                title = "Captain America: The Winter Soldier",
                imageUrl = ""
            ),
            Movie(
                id = 3,
                title = "Avengers: Infinity War",
                imageUrl = ""
            )
        ),
        onClick = { }
    )
}