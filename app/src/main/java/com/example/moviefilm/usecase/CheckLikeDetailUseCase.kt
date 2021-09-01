package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.repo.DetailRepository
import javax.inject.Inject

class CheckLikeDetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    fun excute(id : Int): Boolean{
        val listDetail = detailRepository.getListDetail()
        for(item : Detail in listDetail){
            if (id == item.id){
                return true
                continue
            }
        }
        return false
    }
}