package com.example.moviefilm.fragment.discover

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.moviefilm.pojo.model.list_movie.Response3
import com.example.moviefilm.usecase.GetListMovieUseCase3

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverViewModel @ViewModelInject constructor(
    private val getListMovieUseCase3: GetListMovieUseCase3
) : ViewModel() {
    private var _liveDataMovie: MutableLiveData<GetListMovieState> =
        MutableLiveData<GetListMovieState>()
    val liveDataMovie: LiveData<GetListMovieState> = _liveDataMovie

    fun getListMovie() {
        getListMovieUseCase3.excute()
            .enqueue(object : Callback<Response3> {
                override fun onFailure(call: Call<Response3>, t: Throwable) {
                    _liveDataMovie.postValue(GetListMovieState.Error(t.message.toString()))
                }

                override fun onResponse(call: Call<Response3>, response: Response<Response3>) {
                    _liveDataMovie.postValue(GetListMovieState.Success(response.body()))
                    Log.d("ABC", "Repo = ${response.body()}")
                }

            })
    }

    sealed class GetListMovieState {
        class Error(val message: String) : GetListMovieState()
        class Success(val listMovie: Response3?) :
            GetListMovieState()
    }
}
