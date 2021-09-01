package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.repo.MovieRepository
import retrofit2.Call
import javax.inject.Inject

class GetListDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun excute(id : Int) : Call<Detail> {
        return repository.getDetailMovie(id)
    }
}