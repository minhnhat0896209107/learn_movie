package com.example.moviefilm.fragment.discover

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviefilm.R
import com.example.moviefilm.activity.FilmActivity
import com.example.moviefilm.recyclerView.RecyclerViewDiscover3
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_discover.*


@Suppress("UNREACHABLE_CODE", "UNCHECKED_CAST", "DEPRECATION")
@AndroidEntryPoint
class DiscoverFragment : Fragment() {
    private var mView: View? = null
    private val viewModel: DiscoverViewModel by viewModels()
    private lateinit var gridLayoutManager: GridLayoutManager

        private val adapter: RecyclerViewDiscover3 =  RecyclerViewDiscover3(
        onItemClickListener = {
            val intent = Intent(activity, FilmActivity::class.java)
            intent.putExtra("keyId", it.id)
            startActivity(intent)
        }
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        init()
        eventObs()
        viewModel.getListMovie()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_discover, container, false)
        return mView
    }

    private fun init() {
//        adapter = RecyclerViewDiscover()
        gridLayoutManager = GridLayoutManager(context, 3)
        recycler.layoutManager = gridLayoutManager
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter

    }

    private fun eventObs() {
        viewModel.liveDataMovie.observe(viewLifecycleOwner, Observer { t ->
            when (t) {
                is DiscoverViewModel.GetListMovieState.Error -> Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
                is DiscoverViewModel.GetListMovieState.Success ->
                    t.listMovie?.movie3?.let {
                    adapter.setData(
                        it
                    )
                        Log.d("ABC", "= ${t.listMovie}")
                }

            }
        }
        )
    }


}



