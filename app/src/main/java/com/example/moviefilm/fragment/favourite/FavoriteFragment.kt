package com.example.moviefilm.fragment.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviefilm.R
import com.example.moviefilm.recyclerView.RecyclerViewFavorite


class FavoriteFragment : Fragment() {
    private var nameFilm: String? = null
    private var imageFilm: String? = null
    private lateinit var recyclerViewFavorite : RecyclerViewFavorite
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init(){
        recyclerViewFavorite = RecyclerViewFavorite()

    }

}