package com.bcit.ghiblifilm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.bcit.ghiblifilm.ui.main.FilmState
import com.bcit.ghiblifilm.ui.main.MainContent
import com.bcit.ghiblifilm.ui.main.WishListFilmState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filmRepository = (application as MyApp).filmRepository
        val wishListRepository = (application as MyApp).wishListRepository
        setContent {
            val filmState = FilmState(filmRepository)
            val wishListFilmState = remember{
                WishListFilmState(wishListRepository)
            }
            LaunchedEffect(filmState){
                filmState.getFilm()
                filmState.filmwork.forEach { film ->
                    film.inWishList = wishListFilmState.films.any { it.id == film.id }
                }
            }

            MainContent(filmState,wishListFilmState)
        }
    }
}
