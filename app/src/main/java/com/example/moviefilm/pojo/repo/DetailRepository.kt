package com.example.moviefilm.pojo.repo

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.moviefilm.pojo.database.IDetailDao
import com.example.moviefilm.pojo.model.detail.Detail
import retrofit2.Call
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val iDetailDao: IDetailDao,
) {
    fun insertDetail(detail : Detail): Long  {
        return iDetailDao.insertDetail(detail)
    }

    fun getListDetail() : List<Detail> {
        return iDetailDao.getAllDetail()
    }

}