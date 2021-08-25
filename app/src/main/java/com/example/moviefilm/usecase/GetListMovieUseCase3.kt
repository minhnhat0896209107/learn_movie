package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.list_movie.Response3
import com.example.moviefilm.pojo.repo.MovieRepository3
import retrofit2.Call
import javax.inject.Inject

class GetListMovieUseCase3 @Inject constructor(
    private val movieRepository3: MovieRepository3
){
    fun excute() : Call<Response3>{
        return movieRepository3.getListMovie3()
    }
}