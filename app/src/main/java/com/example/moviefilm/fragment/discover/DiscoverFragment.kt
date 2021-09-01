package com.example.moviefilm.fragment.discover

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviefilm.R
import com.example.moviefilm.activity.DetailActivity
import com.example.moviefilm.recyclerView.DiscoverAdapter
import com.example.moviefilm.recyclerView.IRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_discover.*

@AndroidEntryPoint
class DiscoverFragment : Fragment() {
    private val viewModel: DiscoverViewModel by viewModels()
    private lateinit var gridLayoutManager: GridLayoutManager

    private val discoverAdapter: DiscoverAdapter by lazy {
        DiscoverAdapter(
            onLoadMore = {
                viewModel.getListMovie()
            },
            onItemClickListener = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("keyId", it.id)
                startActivity(intent)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        eventObs()
        viewModel.getListMovie(true)
        updateUI()
    }

    private fun initRecyclerView() {
        recycler?.apply {
            adapter = discoverAdapter
            gridLayoutManager = GridLayoutManager(context, 3)
            recycler.layoutManager = gridLayoutManager
            recycler.setHasFixedSize(true)
            setLoadMoreEnabled(true)
            setOnLoadMoreListener(object : IRecyclerView.OnLoadMoreListener {
                override fun onLoadMore() {
                    viewModel.getListMovie()
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    private fun eventObs() {
        viewModel.liveDataLoadMovie.observe(viewLifecycleOwner, { t ->
            when (t) {
                is DiscoverViewModel.ListMovieState.Loading ->
                    updateUI(true)

                is DiscoverViewModel.ListMovieState.Error -> {
                    Toast.makeText(
                        context,
                        t.message,
                        Toast.LENGTH_LONG
                    ).show()
                    updateUI(false)
                }

                is DiscoverViewModel.ListMovieState.Success -> {
                    if (t.movieResponse.isNotEmpty()) {
                        discoverAdapter.updateAdapter(t.movieResponse, t.focusUpdate)
                    }
                    updateUI(false)
                }
            }
        })
    }

    private fun updateUI(isLoading: Boolean = false) {
        progressBar.setVisible(isLoading && discoverAdapter.itemCount == 0)
    }

    private fun View.setVisible(visible: Boolean) {
        if (visible) this.show()
        else this.hide()
    }

    private fun View.show() {
        visibility = View.VISIBLE
    }

    private fun View.hide() {
        visibility = View.GONE
    }
}







