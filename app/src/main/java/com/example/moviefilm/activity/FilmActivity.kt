package com.example.moviefilm.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.detail.Genre
import com.example.moviefilm.pojo.model.detail.ProductionCompany
import com.example.moviefilm.pojo.model.list_video.Result
import com.example.moviefilm.recyclerView.CategoryAdapter
import com.example.moviefilm.recyclerView.CharacterAdapter
import com.example.moviefilm.recyclerView.TrailerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_film.*


@AndroidEntryPoint
class FilmActivity : AppCompatActivity() {
    private var id: Int = 0

    private lateinit var tvTitle: TextView
    private lateinit var tvDate: TextView
    private lateinit var ivBackdrop: ImageView
    private lateinit var ivPoster : ImageView
    private lateinit var tvDescription: TextView
    private lateinit var tvRate: TextView
    private lateinit var tvLanguage: TextView
    private lateinit var ivBack: ImageView
    private lateinit var ivFavourite: ImageView
    private lateinit var tvVoteCount : TextView

    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var trailerAdapter: TrailerAdapter
    private lateinit var linearHori: LinearLayoutManager
    private lateinit var linearHorizon: LinearLayoutManager
    private lateinit var linearVerti: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private val viewModel : FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        id = intent.getIntExtra("keyId", 0)

        init()
        getRecyclerView()
        eventOnClick()
        eventRegisterObs()

        viewModel.getListDetail(id)
        viewModel.getListVideo(id)
    }

    @SuppressLint("SetTextI18n")
    private fun eventRegisterObs() {
        viewModel.liveDataDetail.observe(this, Observer {
            t->
            when(t){
                is FilmViewModel.GetListDetail.Error -> Toast.makeText(
                    this,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()

                is FilmViewModel.GetListDetail.Success ->{
                    t.listDetail

                    tvTitle.text = t.listDetail?.title
                    Glide.with(ivBackdrop)
                        .load("https://image.tmdb.org/t/p/w500${t.listDetail?.backdropPath}")
                        .error(R.drawable.loading)
                        .into(ivBackdrop)
                    Glide.with(ivPoster)
                        .load("https://image.tmdb.org/t/p/w500${t.listDetail?.backdropPath}")
                        .error(R.drawable.loading)
                        .into(ivPoster)

                    tvRate.text = t.listDetail?.voteAverage.toString()
                    tvLanguage.text = t.listDetail?.originalLanguage
                    tvDate.text = t.listDetail?.releaseDate
                    tvDescription.text = t.listDetail?.overview
                    tvVoteCount.text = "${t.listDetail?.voteCount} votes"

                    categoryAdapter.setListCategory(t.listDetail?.genres as MutableList<Genre>)
                    characterAdapter.setListCharacter(t.listDetail.productionCompanies as MutableList<ProductionCompany>)

                }

            }
        })


        viewModel.liveDataVideo.observe(this, Observer {
            t->
            when(t){
                is FilmViewModel.GetListVideo.Error -> t.message
                is FilmViewModel.GetListVideo.Success -> {
                    t.listVideo?.results

                    trailerAdapter.setListTrailer(t.listVideo?.results as MutableList<Result>)
                }
            }
        })
    }


    private fun eventOnClick() {
        ivBack.setOnClickListener {
            finish()
        }
        ivFavourite.setOnClickListener {
            changeFavourite()
        }
    }


    private fun init() {
        tvTitle = findViewById(R.id.tv_nameFilm)
        ivBackdrop = findViewById(R.id.iv_backdrop)
        ivPoster = findViewById(R.id.iv_poster)
        tvDescription = findViewById(R.id.tv_description)
        tvRate = findViewById(R.id.tv_rate)
        tvLanguage = findViewById(R.id.tv_language)
        ivBack = findViewById(R.id.iv_backHome)
        ivFavourite = findViewById(R.id.iv_favourite)
        tvDate = findViewById(R.id.tv_date)
        tvVoteCount = findViewById(R.id.tv_vote_count)

        characterAdapter = CharacterAdapter()
        trailerAdapter = TrailerAdapter()
        categoryAdapter = CategoryAdapter()

        linearHori = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        linearHorizon = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        linearVerti = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        gridLayoutManager = GridLayoutManager(this, 2)

    }


    fun getRecyclerView() {
        recyclerViewCharacter.layoutManager = linearHori
        recyclerViewCharacter.setHasFixedSize(true)
        recyclerViewCharacter.adapter = characterAdapter


        recyclerViewCategory.layoutManager = gridLayoutManager
        recyclerViewCategory.setHasFixedSize(true)
        recyclerViewCategory.adapter = categoryAdapter

        recyclerViewTrailer.layoutManager = linearHorizon
        recyclerViewTrailer.setHasFixedSize(true)
        recyclerViewTrailer.adapter = trailerAdapter
    }

    private fun changeFavourite() {
        ivFavourite.setImageResource(
            R.drawable.favorite_fill_white
        )
    }

}

