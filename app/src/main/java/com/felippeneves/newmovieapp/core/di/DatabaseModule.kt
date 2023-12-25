package com.felippeneves.newmovieapp.core.di

import android.content.Context
import androidx.room.Room
import com.felippeneves.newmovieapp.core.data.local.NewMovieDatabase
import com.felippeneves.newmovieapp.core.data.local.NewMovieDatabase.Companion.DATABASE_NAME
import com.felippeneves.newmovieapp.core.data.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NewMovieDatabase =
        Room.databaseBuilder(
            context,
            NewMovieDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMovieDao(database: NewMovieDatabase): MovieDao =
        database.MovieDao()
}