package com.example.moviefilm.usecase

import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.repo.DetailRepository
import javax.inject.Inject

class CheckUnlikeUseCase @Inject constructor(
    private var detailRepository: DetailRepository,
) {
    fun excute(id: Int) {
        detailRepository.getUnLike(id)
    }
}

