package com.example.moviefilm.pojo.database

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviefilm.pojo.model.detail.Detail
import retrofit2.Call

@Dao
interface IDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(detail: Detail): Long

    @Query("SELECT *FROM detail")
    fun getAllDetail(): List<Detail>

    @Delete
    fun deleteDetail(detail: Detail)

}