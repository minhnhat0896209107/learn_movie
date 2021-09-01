package com.example.moviefilm.pojo.database

import androidx.room.Database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviefilm.pojo.model.detail.BelongsToCollection
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.model.detail.Genre
import com.example.moviefilm.pojo.model.detail.ProductionCompany

@Database(entities = [
    Detail::class,
    BelongsToCollection::class,
    Genre::class,
    ProductionCompany::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class DetailDatabase : RoomDatabase() {
    abstract fun iDetailDao(): IDetailDao
}