package com.example.moviefilm.pojo.repo

import com.example.moviefilm.pojo.di.API_KEY
import com.example.moviefilm.pojo.di.API_LANGUAGE
import com.example.moviefilm.pojo.di.MovieService3
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.model.list_movie.Response3
import retrofit2.Call
import javax.inject.Inject

class MovieRepository3 @Inject constructor(
    private val movieService3: MovieService3
){
    fun getListMovie3() : Call<Response3> {
        return movieService3.getListMovie3(API_KEY, API_LANGUAGE, 1)
    }

    fun getDetailMovie3(id: Int): Call<Detail>{
        return movieService3.getDetailMovie(id, API_KEY, API_LANGUAGE)
    }
}