package com.example.moviefilm.pojo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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
    fun getMovieService3(retrofit: Retrofit) : MovieService{
        return retrofit.create(MovieService::class.java)
    }

}