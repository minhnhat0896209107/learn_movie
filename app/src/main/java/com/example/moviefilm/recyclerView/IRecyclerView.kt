package com.example.moviefilm.recyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefilm.R

class IRecyclerView @JvmOverloads constructor(
    context : Context,
    attrs : AttributeSet?= null,
    defStyle : Int = 0
) : RecyclerView(context, attrs, defStyle){
    private var loadMoreEnable = false
    private var onLoadMoreListener : OnLoadMoreListener?= null
    private val onLoadMoreScrollListener : OnLoadMoreScrollListener = object : OnLoadMoreScrollListener(){
        override fun onLoadMore(recyclerView: RecyclerView?) {
            onLoadMoreListener?.onLoadMore()
        }
    }

    init {
        val loadMoreEnabled : Boolean
        val typeArray =
            context.obtainStyledAttributes(attrs, R.styleable.IRecyclerView, defStyle, 0)
        try {
            loadMoreEnabled =
                typeArray.getBoolean(R.styleable.IRecyclerView_loadMoreEnabled, false)
        }finally {
            typeArray.recycle()
        }
        setLoadMoreEnabled(loadMoreEnabled)
    }

    fun setLoadMoreEnabled(enable: Boolean) {
        loadMoreEnable = enable
        if (loadMoreEnable){
            addOnScrollListener(onLoadMoreScrollListener)
        }else{
            removeOnScrollListener(onLoadMoreScrollListener)
        }
    }

    fun setLoadMoreReverseEnable(reverseEnable: Boolean = false){
        onLoadMoreScrollListener.setReverseScrolled(reverseEnable)
    }
    fun setOnLoadMoreListener(listener: OnLoadMoreListener){
        onLoadMoreListener = listener
    }

    interface OnLoadMoreListener{
        fun onLoadMore()
    }
}