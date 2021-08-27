package com.example.moviefilm.fragment.discover

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefilm.R
import com.example.moviefilm.activity.FilmActivity
import com.example.moviefilm.recyclerView.DiscoverAdapter
import com.example.moviefilm.recyclerView.IRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_discover.*

@Suppress("UNREACHABLE_CODE", "UNCHECKED_CAST", "DEPRECATION")
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
                val intent = Intent(activity, FilmActivity::class.java)
                intent.putExtra("keyId", it.id)
                startActivity(intent)
            }
        )
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler?.apply {
            adapter = discoverAdapter
            gridLayoutManager = GridLayoutManager(context, 3)
            recycler.layoutManager = gridLayoutManager
            recycler.setHasFixedSize(true)
            setLoadMoreEnabled(true)
//            setLoadMoreReverseEnable(true)
            setOnLoadMoreListener(object : IRecyclerView.OnLoadMoreListener{
                override fun onLoadMore() {
                    viewModel.getListMovie()
                }
            })
        }
        eventObs()

        viewModel.getListMovie(true)
        updateUI()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }


    private fun eventObs() {
        viewModel.liveDataLoadMovie.observe(viewLifecycleOwner, Observer { t ->
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
//                    t.movieResponse?.let { discoverAdapter.updateAdapter(it, t.focusUpdate) }
                    if (t.movieResponse.isNotEmpty()){
                        discoverAdapter.updateAdapter(t.movieResponse, t.focusUpdate)

                        Log.d("TAGA","${discoverAdapter.updateAdapter(t.movieResponse, t.focusUpdate)}" )
                    }

                    updateUI(false)
                }

            }
        }
        )
    }

    private fun updateUI(isLoading: Boolean = false) {
        // recycler.setVisible(isLoading && discoverAdapter.itemCount == 0)
//        progressBar.setVisible(isLoading && discoverAdapter.itemCount == 0)
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







