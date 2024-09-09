package com.bcit.ghiblifilm.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bcit.ghiblifilm.ui.main.FilmState
import com.bcit.ghiblifilm.data.WishListFilm
@Composable
fun WishLists(filmState: FilmState, wishListFilmState: WishListFilmState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Wishlist",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (wishListFilmState.films.isEmpty()) {
            Text(
                text = "Your wishlist is empty",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } else {
            LazyColumn {
                items(wishListFilmState.films.size) { it ->
                    WishItem(
                        wishFilm = wishListFilmState.films[it],
                        itemDeleteCallBack = { film ->
                            wishListFilmState.delete(film)
                            val filmToUpdate = filmState.filmwork.find { apiFilm-> apiFilm.id == film.id }
                            filmToUpdate?.inWishList = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun WishItem(
    wishFilm: WishListFilm,
    itemDeleteCallBack: (WishListFilm) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(0xFFECE0EC))

    ) {

        AsyncImage(model = wishFilm.image,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        // Display film title
        Text(
            text = wishFilm.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .weight(1f)
        )

        // Display delete icon
        IconButton(
            onClick = { itemDeleteCallBack(wishFilm) },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null
            )
        }
    }
}