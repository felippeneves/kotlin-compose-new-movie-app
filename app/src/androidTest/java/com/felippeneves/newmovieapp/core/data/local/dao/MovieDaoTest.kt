package com.felippeneves.newmovieapp.core.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.felippeneves.newmovieapp.core.data.local.NewMovieDatabase
import com.felippeneves.newmovieapp.core.data.local.entity.MovieEntity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class MovieDaoTest {

    //Regra de instância do Hilt e será utilizada para configurar o ambiente necessário
    //para os testes que usam o Hilt. Incluindo a inicialização do Hilt antes mesmo da execução
    //dos testes que será executado abaixo
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    //Utilizado para configurar o ambiente necessário para testar partes que foram desenvolvidas
    //assíncronas de forma síncrona, pois o código de teste necessita ser executado rapidamente,
    //não há necessidade de esperar uma tarefa ser concluída, por exemplo.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db") //Utilizado para buscar a instância do BD de testes
    lateinit var database: NewMovieDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun setup() {
        hiltRule.inject()
        movieDao = database.MovieDao()
    }

    @After
    fun tearDown() {
        //Fecha a instância do BD após a execução de todos os métodos do teste na classe atual
        database.close()
    }

    @Test
    fun test_getMovies_should_return_list_of_movies() = runTest {

        //Given - Nothing

        //When
        val movies = movieDao.getMovies().first()

        //Then
        assertThat(movies.size).isEqualTo(0)
    }

    @Test
    fun test_getMovies_should_return_movies_ordered_by_id() = runTest {

        //Given
        val moviesEntities = listOf(
            MovieEntity(movieId = 1, title = "Iron Man 1", imageUrl = "Url1"),
            MovieEntity(movieId = 3, title = "Iron Man 2", imageUrl = "Url2"),
            MovieEntity(movieId = 2, title = "Iron Man 3", imageUrl = "Url3"),
        )
        movieDao.insertAll(moviesEntities)

        //When
        val movies = movieDao.getMovies().first()

        //Then
        assertThat(movies.size).isEqualTo(3)
        assertThat(movies[0].movieId).isEqualTo(1)
        assertThat(movies[1].movieId).isEqualTo(2)
        assertThat(movies[2].movieId).isEqualTo(3)
    }

    @Test
    fun test_getMovie_should_return_correct_movie_by_id() = runTest {
        //Given
        val movieEntity = MovieEntity(movieId = 1, title = "Iron Man 1", imageUrl = "Url1")
        movieDao.insert(movieEntity)
        val movies = movieDao.getMovies().first()
        val movieClick = movies[0]

        //When
        val movie = movieDao.getMovie(movieId = movieClick.movieId)

        //Then
        assertThat(movie?.movieId).isEqualTo(movieClick.movieId)
    }

    @Test
    fun test_insertMovies_should_insert_movies_successfully() = runTest {
        //Given
        val moviesEntities = listOf(
            MovieEntity(movieId = 1, title = "Iron Man 1", imageUrl = "Url1"),
            MovieEntity(movieId = 3, title = "Iron Man 2", imageUrl = "Url2"),
            MovieEntity(movieId = 2, title = "Iron Man 3", imageUrl = "Url3"),
        )

        //When
        movieDao.insertAll(moviesEntities)

        //Then
        val insertedMovies = movieDao.getMovies().first()
        assertThat(moviesEntities.size).isEqualTo(insertedMovies.size)
        assertThat(insertedMovies.containsAll(moviesEntities))
    }

    @Test
    fun test_insertMovie_should_insert_a_movie_successfully() = runTest {
        //Given
        val movieEntity = MovieEntity(movieId = 1, title = "Iron Man 1", imageUrl = "Url1")

        //When
        movieDao.insert(movieEntity)

        //Then
        val insertedMovie = movieDao.getMovies().first()[0]
        assertThat(insertedMovie.movieId).isEqualTo(movieEntity.movieId)
    }

    @Test
    fun test_isFavorite_should_return_1_when_movie_is_marked_as_favorite() = runTest {
        //Given
        val movieId = 5321
        val favoriteMovie = MovieEntity(movieId = movieId, title = "Iron Man 1", imageUrl = "Url1")
        movieDao.insert(favoriteMovie)

        //When
        val moviesCountById = movieDao.countMovie(movieId)

        //Then
        assertThat(moviesCountById).isGreaterThan(0)
    }

    @Test
    fun test_countMovies_should_return_size_of_movies() = runTest {
        //Given
        val moviesEntities = listOf(
            MovieEntity(movieId = 1, title = "Iron Man 1", imageUrl = "Url1"),
            MovieEntity(movieId = 3, title = "Iron Man 2", imageUrl = "Url2"),
            MovieEntity(movieId = 2, title = "Iron Man 3", imageUrl = "Url3"),
        )

        //When
        movieDao.insertAll(moviesEntities)

        //Then
        val moviesCount = movieDao.countAll()
        assertThat(moviesEntities.size).isEqualTo(moviesCount)
    }

    @Test
    fun test_isFavorite_should_return_0_when_movie_is_not_marked_as_favorite() = runTest {
        //Given
        val movieId = 5321

        //When
        val moviesCountById = movieDao.countMovie(movieId)

        //Then
        assertThat(moviesCountById).isEqualTo(0)
    }

    @Test
    fun test_updateMovie_should_update_a_movie_successfully() = runTest {
        //Given
        val movieEntity = MovieEntity(movieId = 1, title = "Iron Man 1", imageUrl = "Url1")
        movieDao.insert(movieEntity)
        val allMovies = movieDao.getMovies().first()
        val updatedMovie = allMovies[0].copy(title = "Captain America")

        //When
        movieDao.insert(updatedMovie)

        //Then
        val movies = movieDao.getMovies().first()
        assertThat(movies[0].title).contains(updatedMovie.title)
    }

    @Test
    fun test_deleteMovie_should_delete_a_movie_successfully() = runTest {
        //Given
        val movieEntity = MovieEntity(movieId = 1, title = "Iron Man 1", imageUrl = "Url1")
        movieDao.insert(movieEntity)

        //When
        movieDao.delete(movieEntity)

        //Then
        val moviesCountById = movieDao.countAll()
        assertThat(moviesCountById).isEqualTo(0)
    }
}