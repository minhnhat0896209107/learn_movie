package com.example.moviefilm.recyclerView

import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class OnLoadMoreScrollListener : RecyclerView.OnScrollListener(){
    private var isScrolling = false
    private var isReverse = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if(newState ==  AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
            isScrolling = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(isScrolling){
            if (isReverse){
                if (dy < 0){
                    recyclerView.layoutManager?.apply {
                        val pastVisibleItems = when (this){
                            is LinearLayoutManager -> findFirstVisibleItemPosition()
                            is GridLayoutManager -> findFirstVisibleItemPosition()
                            is StaggeredGridLayoutManager -> findFirstVisibleItemPositions(IntArray(spanCount))[0]
                            else -> 0
                        }
                        if (childCount + pastVisibleItems >= itemCount){
                            isScrolling = true
                            onLoadMore(recyclerView)
                        }
                    }
                }
            }
        }
    }

    fun setReverseScrolled(isReverse: Boolean = false){
        this.isReverse = isReverse
    }
    abstract fun onLoadMore(recyclerView: RecyclerView?)
}