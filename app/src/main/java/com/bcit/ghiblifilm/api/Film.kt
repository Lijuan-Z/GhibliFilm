package com.bcit.ghiblifilm.api

import com.google.gson.annotations.SerializedName

data class Film(
    val id: String,
    val title: String,
    @SerializedName("rt_score")
    val score:String,
    val image:String,
    val director:String,
    val producer:String,
    @SerializedName("release_date")
    val year:String,
    val description:String,
    var inWishList: Boolean = false
)