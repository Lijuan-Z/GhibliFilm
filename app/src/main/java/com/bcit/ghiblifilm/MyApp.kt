package com.bcit.ghiblifilm

import android.app.Application
import androidx.room.Room
import com.bcit.ghiblifilm.api.FilmRepository
import com.bcit.ghiblifilm.data.WishListDatabase
import com.bcit.ghiblifilm.data.WishListRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson


class MyApp: Application() {

    private val client = HttpClient{
        install(ContentNegotiation){
            gson()
        }
    }

    val filmRepository = FilmRepository(client)

    private val db by lazy{
        //build database here
        Room.databaseBuilder(
            applicationContext,
            WishListDatabase::class.java,"ghibiFilm-database")
            .allowMainThreadQueries()
            .build()
    }

    val wishListRepository by lazy {
        WishListRepository(db.filmDao())
    }
}