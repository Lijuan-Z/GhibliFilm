package com.bcit.ghiblifilm.api

import com.bcit.ghiblifilm.api.ApiEndpoint
import com.bcit.ghiblifilm.api.Film
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FilmRepository(private val client: HttpClient){
    suspend fun getFilm(): List<Film> {
        try {
            val response = client.get(ApiEndpoint.FILMS.url)
            val json = response.body<JsonArray>().toString()
            return deserializeJason(json)
        } catch (e: Exception) {
            println("Exception occurred: ${e.message}")
            throw e
        }
    }

    private fun deserializeJason(json:String):List<Film>{
        return Gson().fromJson(json, object : TypeToken<List<Film>>() {}.type)
    }
}