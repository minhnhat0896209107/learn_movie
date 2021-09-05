package com.example.moviefilm.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefilm.R
import com.example.moviefilm.fragment.favourite.FavoriteFragment
import com.example.moviefilm.pojo.model.detail.Detail
import com.example.moviefilm.recyclerView.CategoryAdapter
import com.example.moviefilm.recyclerView.CharacterAdapter
import com.example.moviefilm.recyclerView.TrailerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_film.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var id: Int = 0

    private var isCheck = false

    private lateinit var tvTitle: TextView
    private lateinit var tvDate: TextView
    private lateinit var ivBackdrop: ImageView
    private lateinit var ivPoster: ImageView
    private lateinit var tvDescription: TextView
    private lateinit var tvRate: TextView
    private lateinit var tvLanguage: TextView
    private lateinit var ivBack: ImageView
    private lateinit var ivFavourite: ImageView
    private lateinit var tvVoteCount: TextView

    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var trailerAdapter: TrailerAdapter
    private lateinit var linearHori: LinearLayoutManager
    private lateinit var linearHorizon: LinearLayoutManager
    private lateinit var linearVerti: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var detail: Detail

    private lateinit var favoriteFragment: FavoriteFragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)

        id = intent.getIntExtra("keyId", 0)

        init()
        getRecyclerView()
        eventRegisterObs()
        eventOnClick()
        viewModel.getDetail(id)
        viewModel.getListVideo(id)
        viewModel.checkLikeDetail(id)

    }

    private fun sendId(){
        val bundle = Bundle()
        bundle.putInt("keyIDLike", id)
        favoriteFragment.arguments = bundle

        transaction.replace(R.id.content, favoriteFragment).commit()
        transaction.hide(favoriteFragment)
    }

    private fun eventRegisterObs() {
        viewModel.liveDataDetail.observe(this, { t ->
            when (t) {
                is DetailViewModel.GetListDetail.Error -> Toast.makeText(
                    this,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()

                is DetailViewModel.GetListDetail.Success -> {
                    t.listDetail

                    tvTitle.text = t.listDetail?.title
                    Glide.with(ivBackdrop)
                        .load("https://image.tmdb.org/t/p/w500${t.listDetail?.backdropPath}")
                        .error(R.drawable.loading)
                        .into(ivBackdrop)
                    Glide.with(ivPoster)
                        .load("https://image.tmdb.org/t/p/w500${t.listDetail?.posterPath}")
                        .error(R.drawable.loading)
                        .into(ivPoster)

                    tvRate.text = t.listDetail?.voteAverage.toString()
                    tvLanguage.text = t.listDetail?.originalLanguage
                    tvDate.text = t.listDetail?.releaseDate
                    tvDescription.text = t.listDetail?.overview
                    tvVoteCount.text = "${t.listDetail?.voteCount} votes"

                    t.listDetail?.genres?.let { categoryAdapter.setListCategory(it) }
                    t.listDetail?.productionCompanies?.let { characterAdapter.setListCharacter(it) }

                    detail = t.listDetail!!
                }

            }
        })

        viewModel.liveDataVideo.observe(this, { t ->
            when (t) {
                is DetailViewModel.GetListVideo.Error -> t.message
                is DetailViewModel.GetListVideo.Success -> {
                    t.listVideo.results
                    trailerAdapter.setListTrailer(t.listVideo.results)
                }
            }
        })

        viewModel.insertDetail.observe(this, { t ->
            when (t) {
                is DetailViewModel.InsertDetail.Error -> Toast.makeText(
                    this,
                    "Insert UnSuccess",
                    Toast.LENGTH_SHORT
                ).show()

                is DetailViewModel.InsertDetail.Success -> {
                    t.detail
                    Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.checkLike.observe(this, { t ->
            when (t) {
                is DetailViewModel.CheckLikeState.Success -> changeFavourite(true)
                is DetailViewModel.CheckLikeState.Error -> changeFavourite(false)
            }
        })
    }

    private fun eventOnClick() {
        ivBack.setOnClickListener {
            finish()
        }
          ivFavourite.setOnClickListener {
              if(!isCheck){
                  changeFavourite(true)
                  viewModel.insertDetail(detail)
              }else {
                  changeFavourite(false)
                  sendId()
              }

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

        favoriteFragment = FavoriteFragment()
        fragmentManager = supportFragmentManager
        transaction = fragmentManager.beginTransaction()
    }

    private fun getRecyclerView() {
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

    private fun changeFavourite(isUpdate: Boolean) {
        if(isUpdate){
            ivFavourite.setImageResource(
               R.drawable.favorite_fill_white
            )
            isCheck = true
        }else{
            ivFavourite.setImageResource(
                R.drawable.favourite_white
            )
            isCheck = false
        }
    }
}

