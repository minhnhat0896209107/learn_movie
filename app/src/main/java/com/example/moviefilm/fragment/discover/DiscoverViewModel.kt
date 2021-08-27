package com.example.moviefilm.fragment.discover

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.moviefilm.pojo.model.list_movie.Movie
import com.example.moviefilm.pojo.model.list_movie.MovieResponse
import com.example.moviefilm.usecase.GetListMovieUseCase

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverViewModel @ViewModelInject constructor(
    private val getListMovieUseCase3: GetListMovieUseCase
) : ViewModel() {

    private var _liveDataLoadMovie: MutableLiveData<ListMovieState> =
        MutableLiveData<ListMovieState>()
    val liveDataLoadMovie: LiveData<ListMovieState> = _liveDataLoadMovie

    var page: Int = 1
    fun getListMovie( focusUpdate: Boolean = false){
        _liveDataLoadMovie.postValue(ListMovieState.Loading)
        if (focusUpdate) page = 1
        getListMovieUseCase3.excute(page)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    _liveDataLoadMovie.postValue(ListMovieState.Error(t.message.toString()))
                    Log.d("ABC", t.message.toString())
                }
                override fun onResponse(call: Call<MovieResponse>, movieResponse: Response<MovieResponse>) {
                    if(movieResponse.body()?.movie != null) page++
                    _liveDataLoadMovie.postValue(movieResponse.body()?.movie?.let {
                        ListMovieState.Success(it, focusUpdate)
                    })
                    Log.d("ABC", "Repo = ${movieResponse.body()}")
                }
            })
    }

    sealed class ListMovieState{
        object Loading : ListMovieState()
        class Error(val message: String) : ListMovieState()
        class Success(val movieResponse: List<Movie>, val focusUpdate: Boolean = false) : ListMovieState()
    }
}

