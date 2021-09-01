package com.example.moviefilm.fragment.favourite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.usecase.GetListFavouriteUseCase

class FavoriteViewModel @ViewModelInject constructor(
    private val getListFavoriteUseCase: GetListFavouriteUseCase
) : ViewModel(){
    private val _liveDataFavourite = MutableLiveData<FavoriteState>()
    val liveDataFavourite : LiveData<FavoriteState> = _liveDataFavourite
    fun getListFavourite() {
        val list = getListFavoriteUseCase.excute()
        if (list.isNotEmpty()){
            _liveDataFavourite.postValue(FavoriteState.Success(list))
        }else{
            _liveDataFavourite.postValue(FavoriteState.Error)
        }
    }

    sealed class FavoriteState{
        object Error: FavoriteState()
        class Success(var detail: List<Detail>) : FavoriteState()
    }

}





