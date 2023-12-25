package com.felippeneves.newmovieapp.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_MOVIES")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "MOVIE_ID")
    val movieId: Int,
    @ColumnInfo(name = "MOVIE_TITLE")
    val title: String,
    @ColumnInfo(name = "MOVIE_IMAGE_URL")
    val imageUrl: String,
)
