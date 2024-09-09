package com.bcit.ghiblifilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_table")
data class WishListFilm (
    @PrimaryKey val id: String,
    val title:String,
    val image:String
)