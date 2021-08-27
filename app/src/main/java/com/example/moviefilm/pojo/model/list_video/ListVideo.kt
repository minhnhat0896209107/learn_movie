package com.example.moviefilm.pojo.model.list_video


import com.google.gson.annotations.SerializedName

data class ListVideo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Result>
)