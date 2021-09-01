package com.example.moviefilm.pojo.database

import androidx.room.TypeConverter
import com.example.moviefilm.pojo.model.detail.BelongsToCollection
import com.example.moviefilm.pojo.model.detail.Genre
import com.example.moviefilm.pojo.model.detail.ProductionCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun listToStringBelong(value: BelongsToCollection?): String = Gson().toJson(value)

    @TypeConverter
    fun stringToListBelong(value: String) = Gson().fromJson(value, BelongsToCollection::class.java)

    @TypeConverter
    fun listToStringCompany(value: List<ProductionCompany>?): String = Gson().toJson(value)

    @TypeConverter
    fun stringToListCompany(value: String) = Gson().fromJson(value, Array<ProductionCompany>::class.java).toList()

    @TypeConverter
    fun listToStringGenre(value: List<Genre>?): String = Gson().toJson(value)

    @TypeConverter
    fun stringToListGenre(value: String) = Gson().fromJson(value, Array<Genre>::class.java).toList()
}