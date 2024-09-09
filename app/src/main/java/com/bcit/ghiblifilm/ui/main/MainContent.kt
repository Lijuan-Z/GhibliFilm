package com.bcit.ghiblifilm.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bcit.ghiblifilm.ui.main.FilmState
import com.bcit.ghiblifilm.data.WishListFilm


@Composable
fun MainContent(filmState: FilmState, wishListFilmState:WishListFilmState){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {
            MyNavBar(navController = navController)
        }
    ){paddingValues: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                Home(filmState,navController){clickedFilm ->
                    val wishListFilm = WishListFilm(clickedFilm.id,clickedFilm.title,clickedFilm.image)
                    if (wishListFilmState.films.contains(wishListFilm)) {
                        wishListFilmState.delete(wishListFilm)
                        clickedFilm.inWishList = false
                    } else{
                        wishListFilmState.add(wishListFilm)
                        clickedFilm.inWishList = true
                    }
                }
            }

            composable("details/{index}", arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })) {
                val index = it.arguments?.getInt("index")
                FilmDetails(filmState,index!!)
            }

            composable(Screen.WishList.route){
                WishLists(filmState,wishListFilmState)
            }
        }
    }
}