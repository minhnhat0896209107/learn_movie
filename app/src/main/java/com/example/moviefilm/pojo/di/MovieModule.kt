package com.example.moviefilm.pojo.di

import android.content.Context
import androidx.room.Room
import com.example.moviefilm.pojo.database.DetailDatabase
import com.example.moviefilm.pojo.database.IDetailDao
import com.example.moviefilm.pojo.di.MovieModule.getDetailDB
import com.example.moviefilm.pojo.model.detail.Detail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MovieModule {
    @Singleton
    @Provides
    fun getMovieRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL3)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getMovieService(retrofit: Retrofit) : MovieService{
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideDetailDao(detailDatabase: DetailDatabase) : IDetailDao {
        return detailDatabase.iDetailDao()
    }

    @Provides
    fun getDetailDB(@ApplicationContext context : Context) : DetailDatabase {
        return Room.databaseBuilder(
            context,
            DetailDatabase::class.java,
            "Detail.DB"
        ).allowMainThreadQueries()
            .build()
    }

}