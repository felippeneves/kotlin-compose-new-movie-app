package com.felippeneves.newmovieapp.di

import android.content.Context
import androidx.room.Room
import com.felippeneves.newmovieapp.core.data.local.NewMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    @Named("test_db") //Utilizado para deixar claro que esse é o BD de testes
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, NewMovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}