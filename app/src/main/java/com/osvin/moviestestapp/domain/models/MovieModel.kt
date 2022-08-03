package com.osvin.moviestestapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_cash")
data class MovieModel (
    @PrimaryKey
    val id: Int,
    val name: String,
    val info: String,
    val src: String
)