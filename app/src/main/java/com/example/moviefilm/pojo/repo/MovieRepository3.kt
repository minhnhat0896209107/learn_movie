package com.example.moviefilm.pojo.repo

import com.example.moviefilm.pojo.di.API_KEY
import com.example.moviefilm.pojo.di.API_LANGUAGE
import com.example.moviefilm.pojo.di.MovieService
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.model.list_movie.MovieResponse
import com.example.moviefilm.pojo.model.list_video.ListVideo
import retrofit2.Call
import javax.inject.Inject

class MovieRepository3 @Inject constructor(
    private val movieService: MovieService,
){
    fun getListMovie3(
        page : Int
    ) : Call<MovieResponse> {
        return movieService.getListMovie3(API_KEY, API_LANGUAGE, page)
    }

    fun getDetailMovie3(id: Int): Call<Detail>{
        return movieService.getDetailMovie(id, API_KEY, API_LANGUAGE)
    }

    fun getVideoMovie3(id : Int) : Call<ListVideo>{
        return movieService.getVideoMovie(id, API_KEY, API_LANGUAGE)
    }

}