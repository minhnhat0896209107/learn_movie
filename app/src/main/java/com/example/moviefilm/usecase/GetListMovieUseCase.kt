package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.list_movie.MovieResponse
import com.example.moviefilm.pojo.repo.MovieRepository
import retrofit2.Call
import javax.inject.Inject

class GetListMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
){
    fun excute(vararg params : Any) : Call<MovieResponse>{
        val page: Int = params[0] as Int
        return movieRepository.getListMovie(
            page = page
        )
    }
}