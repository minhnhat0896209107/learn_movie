package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.repo.DetailRepository
import javax.inject.Inject

class GetListFavouriteUseCase @Inject constructor(
    private val detailRepository: DetailRepository
){
    fun excute() : List<Detail> {
        return detailRepository.getListDetail()
    }
}