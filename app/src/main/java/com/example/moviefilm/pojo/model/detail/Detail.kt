package com.example.moviefilm.pojo.model.detail

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail")
data class Detail(
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = null,
    @SerializedName("budget")
    val budget: Int? = 0,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
//    @SerializedName("production_countries")
//    val productionCountries: List<ProductionCountry>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Int? = 0,
    @SerializedName("runtime")
    val runtime: Int? = 0,
//    @SerializedName("spoken_languages")
//    val spokenLanguages: List<SpokenLanguage>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = 0
)