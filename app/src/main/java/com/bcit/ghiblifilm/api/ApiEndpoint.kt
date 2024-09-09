package com.bcit.ghiblifilm.api

enum class ApiEndpoint(val url:String) {
    BASE_URL("https://ghibliapi.vercel.app"),
    FILMS("${BASE_URL.url}/films"),
//    FIELD("${FILMS.url}/id")
}
