package com.bcit.ghiblifilm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDAO {
    @Query("SELECT * FROM film_table")
    fun getAll():List<WishListFilm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(film:WishListFilm)

    @Delete()
    fun delete(film:WishListFilm)
}