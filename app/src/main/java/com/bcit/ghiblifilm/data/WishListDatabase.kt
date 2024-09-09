package com.bcit.ghiblifilm.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WishListFilm::class],version = 1)
abstract class WishListDatabase:RoomDatabase(){
    abstract fun filmDao():FilmDAO
}