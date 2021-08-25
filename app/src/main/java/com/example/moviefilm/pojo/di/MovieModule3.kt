package com.example.moviefilm.pojo.di

import androidx.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MovieModule3 {
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
    fun getMovieService3(retrofit: Retrofit) : MovieService3{
        return retrofit.create(MovieService3::class.java)
    }

}