package com.example.moviefilm.pojo.di

import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.model.list_movie.MovieResponse
import com.example.moviefilm.pojo.model.list_video.ListVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getListMovie3(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/{Id}")
    fun getDetailMovie(
        @Path("Id")id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Detail>

    @GET("movie/{Id}/videos")
    fun getVideoMovie(
        @Path("Id") id : Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ListVideo>
}