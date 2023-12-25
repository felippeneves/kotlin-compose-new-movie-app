package com.felippeneves.newmovieapp.movie_details_feature.presentation.components

import MovieDetailsRatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.core.domain.model.Movie
import com.felippeneves.newmovieapp.core.domain.model.MovieDetails
import com.felippeneves.newmovieapp.movie_details_feature.data.mapper.toMovie
import com.felippeneves.newmovieapp.ui.theme.black
import com.felippeneves.newmovieapp.ui.theme.white
import com.felippeneves.newmovieapp.ui.theme.yellow
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailsContent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?,
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    isLoading: Boolean,
    isError: String,
    iconColor: Color,
    paddingValues: PaddingValues,
    onHandleFavorite: (Movie) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(black)
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f) //Ocupa 60% da tela
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieDetailsBackdropImage(
                imageUrl = movieDetails?.backdropPathUrl.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            MovieDetailsFavorite(
                iconColor = iconColor,
                onClick = {
                    movieDetails?.toMovie()?.let { movie ->
                        onHandleFavorite(movie)
                    }
                }
            )

            Text(
                text = movieDetails?.title.toString(),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                mainAxisSpacing = 10.dp,
                mainAxisAlignment = MainAxisAlignment.Center,
                crossAxisSpacing = 10.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                movieDetails?.genres?.forEach { genre ->
                    MovieDetailsGenreTag(genre = genre)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            MovieDetailsInfoContent(
                movieDetails = movieDetails,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            MovieDetailsRatingBar(
                rating = calculateAverageVotes(movieDetails?.voteAverage),
                modifier = Modifier.height(15.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            MovieDetailsOverview(
                overview = movieDetails?.overview.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))
        }

        if (isError.isNotEmpty()) {
            Text(
                text = isError,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.TopCenter)
            )
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                color = yellow
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = stringResource(id = R.string.similar_movies),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            MovieDetailsSimilarMovies(
                pagingMoviesSimilar = pagingMoviesSimilar,
            )
        }
    }
}

private fun calculateAverageVotes(voteAverage: Double?) =
    voteAverage?.toFloat()?.div(2f) ?: 0f

@Preview
@Composable
private fun MovieDetailsContentPreview() {
    MovieDetailsContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Guardians of the Galaxy Vol. 3",
            genres = listOf("Action", "Adventure", "Comedy"),
            overview = "At their new headquarters on Knowhere,[a] the Guardians of the Galaxy are attacked by Adam Warlock, a Sovereign warrior created by High Priestess Ayesha, who seeks to destroy them for stealing from her.[b] After critically wounding Rocket, Adam is stabbed by Nebula, and flees. The Guardians' med-packs are useless at healing Rocket's wounds, who has a kill switch embedded in him by Orgocorp, a company helmed by the High Evolutionary, Rocket's creator. The Guardians travel to Orgocorp's headquarters to find the switch's override code and save Rocket's life.\n" +
                    "\n" +
                    "As Rocket lies unconscious, he recalls his past. He was found as a baby raccoon and was experimented on by the High Evolutionary, who sought to enhance and anthropomorphize animal lifeforms to create an ideal society called Counter-Earth. Rocket befriended his fellow Batch 89 test subjects: the otter Lylla, the walrus Teefs, and the rabbit Floor. The High Evolutionary was impressed by Rocket's growing intelligence and used his insight to fix a defect in later Humanimal batches, but planned to harvest Rocket's brain for further research and exterminate the obsolete Batch 89. Rocket freed his friends, but the High Evolutionary killed Lylla. Enraged, Rocket mauled the High Evolutionary, whose henchmen killed Teefs and Floor in the ensuing firefight. Rocket fled in a spaceship.\n" +
                    "\n" +
                    "In the present, the Ravagers, including an alternate version of Gamora,[c] help the Guardians infiltrate Orgocorp. They retrieve Rocket's file but discover that the code was removed, with the likely culprit being Theel, one of the High Evolutionary's advisors. The Guardians, along with Gamora, depart for Counter-Earth to find him. They are followed by Ayesha and Adam after the High Evolutionary, their race's creator, threatened to wipe out the Sovereign if they fail to retrieve Rocket. The Guardians reach Counter-Earth and are guided to the Arête Laboratories complex. Drax and Mantis remain with Gamora and Rocket, while Peter Quill, Groot, and Nebula travel to Arête. Nebula is forced to wait outside by guards as she is armed; Quill and Groot enter Arête, while Drax tricks Mantis into pursuing Quill's group. Gamora saves Rocket from being captured by Adam and the High Evolutionary's guard War Pig.\n" +
                    "\n" +
                    "Questioned by Quill, the High Evolutionary admits this version Counter-Earth's society is imperfect, so he bombards the planet, killing the Humanimals as well as Ayesha. Arête departs as a spaceship, with Nebula, Drax and Mantis boarding to rescue Quill and Groot, who instead escape Arête with Theel, who they kill before retrieving the code from his corpse and being rescued by Gamora in their ship. As Quill's group uses the code, Rocket flatlines and has a near-death experience, in which he reunites with Lylla, Teefs, and Floor. He learns from Lylla that his time has not yet come, as Quill uses the code to disable the kill switch and restarts Rocket's heart.\n" +
                    "\n" +
                    "Drax, Nebula, and Mantis encounter several genetically modified humanoid children on Arête before being captured. The other Guardians stage a rescue, leading to a battle against the High Evolutionary's forces. Kraglin fires on Arête with Knowhere, dooming Arête, then helps to save Knowhere's citizens from a counter-attack by the High Evolutionary's Hellspawn. Intent on retreat, the High Evolutionary's crew mutiny only to be killed by their leader. Drax, Nebula, and Mantis befriend three monstrous Abilisks to escape and reunite with Quill's group. The Guardians delay leaving Arête, choosing to rescue the children created by the High Evolutionary, who escape to Knowhere via a tunnel constructed by Cosmo's telekinesis. Rocket discovers imprisoned animals on the ship before being confronted by the High Evolutionary, whom the other Guardians defeat. Rocket spares the High Evolutionary,[d] and the Guardians help the animals escape to Knowhere. Quill nearly dies trying to cross over, but is saved by Adam, who was saved from Arête by Groot as \"everyone deserves a second chance\".\n" +
                    "\n" +
                    "In the end, Quill decides to leave the Guardians, naming Rocket as captain, and travels to Earth to reunite with Jason, his grandfather. Mantis embarks on a journey of self-discovery with the Abilisks, Gamora rejoins the Ravagers, and Nebula and Drax remain on Knowhere to raise the rescued children.",
            backdropPathUrl = "",
            releaseDate = "05/05/2023",
            voteAverage = 9.4
        ),
        pagingMoviesSimilar = flowOf(
            PagingData.from(
                listOf(
                    Movie(
                        id = 1,
                        title = "Captain America: The First Avenger",
                        voteAverage = 10.0,
                        imageUrl = ""
                    ),
                    Movie(
                        id = 2,
                        title = "Captain America: The Winter Soldier",
                        voteAverage = 10.0,
                        imageUrl = ""
                    ),
                    Movie(
                        id = 3,
                        title = "Spider-Man: Far From Home",
                        voteAverage = 10.0,
                        imageUrl = ""
                    )
                )
            )
        ).collectAsLazyPagingItems(),
        isError = "Error",
        isLoading = false,
        iconColor = Color.Red,
        paddingValues = PaddingValues(8.dp),
        onHandleFavorite = {}
    )
}