package com.bcit.ghiblifilm.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bcit.ghiblifilm.ui.main.FilmState


@Composable
fun FilmDetails(filmState: FilmState, index:Int){
    val film = filmState.filmwork[index]

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            // Display film image
            AsyncImage(model = film.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Display film title
            Text(
                text = film.title,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display film details
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                FilmDetailItem("Score", film.score)
                FilmDetailItem("Director", film.director)
                FilmDetailItem("Producer", film.producer)
                FilmDetailItem("Release Year", film.year)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display film description
            Text(
                text = film.description,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
@Composable
private fun FilmDetailItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$title: ",
            modifier = Modifier.weight(1f),
        )
        Text(
            text = value,
        )
    }
}