package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.list_video.ListVideo
import com.example.moviefilm.pojo.repo.MovieRepository
import retrofit2.Call
import javax.inject.Inject

class GetListVideoUseCase @Inject constructor(
    private val getListVideoUseCase: MovieRepository
) {
    fun excute(id : Int) : Call<ListVideo> {
        return getListVideoUseCase.getVideoMovie(id)
    }
}