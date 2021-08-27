package com.example.moviefilm.pojo.model.list_movie

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("backdrop_path")
    val image_backdrop : String?=null,
    @SerializedName("id")
    val id : Int?=null,
    @SerializedName("original_language")
    val language: String?=null,
    @SerializedName("poster_path")
    val image_poster : String?=null,
    @SerializedName("release_date")
    val date: String?=null,
    @SerializedName("overview")
    val overview: String?= null,
    @SerializedName("title")
    val title: String?= null,
    @SerializedName("video")
    val video: Boolean?= null,
    @SerializedName("vote_average")
    val vote: String?= null,

)
