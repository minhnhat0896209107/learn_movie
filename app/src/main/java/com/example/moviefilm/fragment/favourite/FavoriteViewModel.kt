package com.example.moviefilm.fragment.favourite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.usecase.CheckUnlikeUseCase
import com.example.moviefilm.usecase.GetListFavouriteUseCase

class FavoriteViewModel @ViewModelInject constructor(
    private val getListFavoriteUseCase: GetListFavouriteUseCase,
    private val checkUnlikeUseCase: CheckUnlikeUseCase
) : ViewModel(){
    private val _liveDataFavourite = MutableLiveData<FavoriteState>()
    val liveDataFavourite : LiveData<FavoriteState> = _liveDataFavourite

    private val _unLikeDetail = MutableLiveData<UnLikeState>()
    val unLikeDetail : LiveData<UnLikeState> = _unLikeDetail

    fun getUnLikeDetail(id: Int){
        val unLike = checkUnlikeUseCase.excute(id)
        if (unLike != null){
            _unLikeDetail.postValue(UnLikeState.Success)
        }
        else{
            _unLikeDetail.postValue(UnLikeState.Error)
        }

    }

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
    sealed class UnLikeState{
        object Error: UnLikeState()
        object Success : UnLikeState()
    }
}





