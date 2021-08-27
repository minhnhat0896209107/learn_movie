package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.list_video.ListVideo
import com.example.moviefilm.pojo.repo.MovieRepository3
import retrofit2.Call
import javax.inject.Inject

class GetListVideoUseCase @Inject constructor(
    private val getListVideoUseCase3: MovieRepository3
) {
    fun excute(id : Int) : Call<ListVideo> {
        return getListVideoUseCase3.getVideoMovie3(id)
    }
}