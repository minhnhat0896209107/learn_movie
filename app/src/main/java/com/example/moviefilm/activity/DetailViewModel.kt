package com.example.moviefilm.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.pojo.model.list_video.ListVideo
import com.example.moviefilm.usecase.CheckLikeDetailUseCase
import com.example.moviefilm.usecase.GetListDetailUseCase
import com.example.moviefilm.usecase.GetListVideoUseCase
import com.example.moviefilm.usecase.InsertDetailUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel @ViewModelInject constructor(
    private val detailUseCase: GetListDetailUseCase,
    private val videoUseCase: GetListVideoUseCase,
    private val insertDetailUseCase: InsertDetailUseCase,
    private val checkLikeDetailUseCase: CheckLikeDetailUseCase
) : ViewModel() {
    private var _liveDataDetail = MutableLiveData<GetListDetail>()
    var liveDataDetail = _liveDataDetail

    private var _liveDataVideo = MutableLiveData<GetListVideo>()
    var liveDataVideo = _liveDataVideo

    private var _insertDetail = MutableLiveData<InsertDetail>()
    var insertDetail = _insertDetail

    private var _checkLikeDetail = MutableLiveData<CheckLikeState>()
    var checkLike = _checkLikeDetail

    fun checkLikeDetail(id : Int){
        val idLike = checkLikeDetailUseCase.excute(id)
        if (idLike == true){
            _checkLikeDetail.postValue(CheckLikeState.Success(true))
        }
        else{
            _checkLikeDetail.postValue(CheckLikeState.Error(false))
        }
    }

    fun getListDetail(id: Int) {
        detailUseCase.excute(id)
            .enqueue(object : Callback<Detail> {
                override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                    _liveDataDetail.postValue(GetListDetail.Success(response.body()))
                }

                override fun onFailure(call: Call<Detail>, t: Throwable) {
                    _liveDataDetail.postValue(GetListDetail.Error(t.message.toString()))
                }

            })
    }

    fun insertDetail(detail : Detail){
         val insert = insertDetailUseCase.excute(detail)

        if (insert != null){
            _insertDetail.postValue(InsertDetail.Success(detail))
        }
        else{
            _insertDetail.postValue(InsertDetail.Error())
        }

    }

    fun getListVideo(id: Int) {
        videoUseCase.excute(id)
            .enqueue(object : Callback<ListVideo> {
                override fun onResponse(call: Call<ListVideo>, response: Response<ListVideo>) {
                    _liveDataVideo.postValue(response.body()?.let { GetListVideo.Success(it) })
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
        class Success(val listVideo: ListVideo) : GetListVideo()
    }
    sealed class InsertDetail {
        class Error: InsertDetail()
        class Success(val detail: Detail?) : InsertDetail()
    }
    sealed class CheckLikeState{
        class Error(isCheck: Boolean?) : CheckLikeState()
        class Success(isCheck : Boolean?) : CheckLikeState()
    }

}


