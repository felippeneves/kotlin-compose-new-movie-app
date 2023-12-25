package com.felippeneves.newmovieapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felippeneves.newmovieapp.core.data.local.NewMovieDatabase.Companion.DATABASE_VERSION
import com.felippeneves.newmovieapp.core.data.local.dao.MovieDao
import com.felippeneves.newmovieapp.core.data.local.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class NewMovieDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "new_movie_database.db3"
        const val DATABASE_VERSION = 1
    }

    abstract fun MovieDao(): MovieDao
}