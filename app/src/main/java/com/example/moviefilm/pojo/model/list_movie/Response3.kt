package com.example.moviefilm.pojo.model.list_movie

import com.google.gson.annotations.SerializedName

data class Response3(
    @SerializedName("page")
    val page: Int?=null,
    @SerializedName("results")
    val movie3: List<Movie3>?=null,
    @SerializedName("total_pages")
    val totalPage: Int?= null,
    @SerializedName("total_results")
    val totalResult : Int?=null
)