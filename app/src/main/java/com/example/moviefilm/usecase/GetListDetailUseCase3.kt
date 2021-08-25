package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.repo.MovieRepository3
import retrofit2.Call
import javax.inject.Inject

class GetListDetailUseCase3 @Inject constructor(
    private val repository3: MovieRepository3
) {
    fun excute(id : Int) : Call<Detail> {
        return repository3.getDetailMovie3(id)
    }
}