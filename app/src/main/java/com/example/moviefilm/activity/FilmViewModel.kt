package com.example.moviefilm.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.usecase.GetListDetailUseCase3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel @ViewModelInject constructor(
    private val useCase: GetListDetailUseCase3
) : ViewModel() {
    private var _liveDataDetail = MutableLiveData<GetListDetail>()
    var liveDataDetail = _liveDataDetail

    fun getListDetail(id: Int){
        useCase.excute(id)
            .enqueue(object : Callback<Detail>{
                override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                    _liveDataDetail.postValue(GetListDetail.Success(response.body()))
                }

                override fun onFailure(call: Call<Detail>, t: Throwable) {
                    _liveDataDetail.postValue(GetListDetail.Error(t.message.toString()))
                }

            })
    }

    sealed class GetListDetail {
        class Error(val message: String) : GetListDetail()
        class Success(val listDetail: Detail?) : GetListDetail()
    }
}

