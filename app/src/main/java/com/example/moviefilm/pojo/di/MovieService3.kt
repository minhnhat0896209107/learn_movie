package com.example.moviefilm.pojo.di

import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.model.list_movie.Response3
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService3 {
    @GET("movie/popular")
    fun getListMovie3(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<Response3>

    @GET("movie/{Id}")
    fun getDetailMovie(
        @Path("Id")id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Detail>
}