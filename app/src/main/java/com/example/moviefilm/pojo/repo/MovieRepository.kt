//package com.example.moviefilm.pojo.repo
//
//import com.example.moviefilm.pojo.di.API_KEY
//import com.example.moviefilm.pojo.di.API_LANGUAGE
//import com.example.moviefilm.pojo.di.MovieService
//import com.example.moviefilm.pojo.model.response.Response
//import retrofit2.Call
//import javax.inject.Inject
//
//class MovieRepository @Inject constructor(
//    private val movieService:MovieService
//){
//
//    fun getListMovie() : Call<Response> {
//        return movieService.getListMovie(API_KEY, API_LANGUAGE, 1)
//    }
//}