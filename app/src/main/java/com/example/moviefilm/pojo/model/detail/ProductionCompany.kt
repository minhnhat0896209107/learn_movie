package com.example.moviefilm.pojo.model.detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productCompany")
data class ProductionCompany(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)