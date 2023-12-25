package com.felippeneves.newmovieapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.felippeneves.newmovieapp.core.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao : BaseDao<MovieEntity> {
    @Query(
        """   
            SELECT *   
            FROM TB_MOVIES
        """
    )
    fun getMovies(): Flow<List<MovieEntity>>

    @Query(
        """   
            SELECT COUNT(1)   
            FROM TB_MOVIES
            WHERE MOVIE_ID = :movieId
        """
    )
    suspend fun countMovie(movieId: Int): Int
}