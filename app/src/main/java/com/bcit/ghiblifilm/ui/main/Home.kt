package com.bcit.ghiblifilm.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bcit.ghiblifilm.api.Film
import com.bcit.ghiblifilm.api.FilmRepository
import com.bcit.ghiblifilm.ui.main.FilmState

@Composable
fun Home(filmState: FilmState, navController: NavController, wishSelectCallBack:(Film)->Unit){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        val picNumber = filmState.filmwork.size
        items(picNumber/2){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FilmItem(index = it,film = filmState.filmwork[it],url = filmState.getImageUrl(it),navController,wishSelectCallBack)
                FilmItem(index = picNumber/2+it,film = filmState.filmwork[picNumber/2+it],url = filmState.getImageUrl(picNumber/2+it),navController,wishSelectCallBack)
            }

        }
    }
}

@Composable
fun FilmItem(index:Int, film: Film, url: String, navController: NavController, wishSelectCallBack:(Film)->Unit) {
    var isFilled by remember {
        mutableStateOf(film.inWishList)
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(180.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate("details/${index}")
            },
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            // Display film image
            AsyncImage(model = url,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth())

            Text(
                text = film.title,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = "RT Score: ${film.score}",
                    modifier = Modifier.padding(end = 40.dp)                )

                // Display wishlist icon
                IconButton(
                    onClick = {
                        isFilled = !isFilled
                        wishSelectCallBack(film)
                    },
                ) {
                    val icon = if (isFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                    Icon(icon, contentDescription = "Add to Wishlist")
                }
            }
        }
    }
}


class FilmState (private val filmRepository: FilmRepository){
    var filmwork = mutableStateListOf<Film>()

    suspend fun getFilm(){
        filmwork.also{
            it.clear()
            it.addAll(filmRepository.getFilm())
        }
    }

    fun getImageUrl(index: Int): String {
        return filmwork[index].image
    }
}