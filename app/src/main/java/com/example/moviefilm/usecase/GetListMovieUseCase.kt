package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.list_movie.MovieResponse
import com.example.moviefilm.pojo.repo.MovieRepository3
import retrofit2.Call
import javax.inject.Inject

class GetListMovieUseCase @Inject constructor(
    private val movieRepository3: MovieRepository3
){
    fun excute(vararg params : Any) : Call<MovieResponse>{
        val page: Int = params[0] as Int
        return movieRepository3.getListMovie3(
            page = page
        )
    }
}