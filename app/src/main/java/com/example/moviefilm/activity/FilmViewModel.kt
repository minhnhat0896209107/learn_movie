package com.example.moviefilm.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.model.list_video.ListVideo
import com.example.moviefilm.usecase.GetListDetailUseCase
import com.example.moviefilm.usecase.GetListVideoUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel @ViewModelInject constructor(
    private val detailUseCase: GetListDetailUseCase,
    private val videoUseCase: GetListVideoUseCase
) : ViewModel() {
    private var _liveDataDetail = MutableLiveData<GetListDetail>()
    var liveDataDetail = _liveDataDetail

    private var _liveDataVideo = MutableLiveData<GetListVideo>()
    var liveDataVideo = _liveDataVideo

    fun getListDetail(id: Int){
        detailUseCase.excute(id)
            .enqueue(object : Callback<Detail>{
                override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                    _liveDataDetail.postValue(GetListDetail.Success(response.body()))
                }

                override fun onFailure(call: Call<Detail>, t: Throwable) {
                    _liveDataDetail.postValue(GetListDetail.Error(t.message.toString()))
                }

            })
    }

    fun getListVideo(id : Int){
        videoUseCase.excute(id)
            .enqueue(object: Callback<ListVideo>{
                override fun onResponse(call: Call<ListVideo>, response: Response<ListVideo>) {
                    _liveDataVideo.postValue(GetListVideo.Success(response.body()))
                }

                override fun onFailure(call: Call<ListVideo>, t: Throwable) {
                    _liveDataVideo.postValue(GetListVideo.Error(t.message.toString()))
                }

            })
    }

    sealed class GetListDetail {
        class Error(val message: String) : GetListDetail()
        class Success(val listDetail: Detail?) : GetListDetail()
    }

    sealed class GetListVideo {
        class Error(val message: String) : GetListVideo()
        class Success(val listVideo: ListVideo?) : GetListVideo()
    }
}


