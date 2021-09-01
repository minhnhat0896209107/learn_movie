package com.example.moviefilm.fragment.favourite

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.recyclerView.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*


@Suppress("DEPRECATION")
@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        eventObs()
        viewModel.getListFavourite()
        swipeRefreshLayout?.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                viewModel.getListFavourite()
                Handler().
                postDelayed({
                        swipeRefreshLayout.isRefreshing = false
                    }, 1000)
            }
        })
    }

    private fun eventObs() {
        viewModel.liveDataFavourite.observe(viewLifecycleOwner, { t ->
            when (t) {
                is FavoriteViewModel.FavoriteState.Error -> Toast.makeText(
                    context,
                    "ERROR",
                    Toast.LENGTH_SHORT
                ).show()
                is FavoriteViewModel.FavoriteState.Success -> {
                    swipeRefreshLayout.isEnabled = true
                    loadData(t.detail)
                }
            }
        })

    }

    private fun init() {
        favoriteAdapter = FavoriteAdapter()
        recyclerViewFavourite?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = favoriteAdapter

        }
    }
    private fun loadData(detail: List<Detail>){
        favoriteAdapter.setListFavourite(detail)
    }
}
