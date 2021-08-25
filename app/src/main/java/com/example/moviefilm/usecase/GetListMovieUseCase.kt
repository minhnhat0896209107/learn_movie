//package com.example.moviefilm.usecase
//
//import com.example.moviefilm.pojo.model.response.Response
//import com.example.moviefilm.pojo.repo.MovieRepository
//import retrofit2.Call
//import javax.inject.Inject
//import javax.inject.Named
//
//class GetListMovieUseCase @Inject constructor(
//    private val movieRepo: MovieRepository
//){
//    fun execute() : Call<Response> {
//        return movieRepo.getListMovie()
//    }
//}