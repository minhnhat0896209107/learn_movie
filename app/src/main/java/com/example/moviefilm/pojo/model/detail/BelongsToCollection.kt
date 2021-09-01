package com.example.moviefilm.pojo.model.detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "belongToCollection")
data class BelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int?= 0,
    @SerializedName("name")
    val name: String?= null,
    @SerializedName("poster_path")
    val posterPath: String? =null
)