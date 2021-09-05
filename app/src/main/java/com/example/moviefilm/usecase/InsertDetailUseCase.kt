package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.repo.DetailRepository
import javax.inject.Inject

class InsertDetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    fun excute( detail : Detail): Boolean {
        val detail = detailRepository.insertDetail(detail)
        return detail != 0L
    }
}