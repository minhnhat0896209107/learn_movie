package com.example.moviefilm.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.Favorite

class RecyclerViewFavorite : RecyclerView.Adapter<RecyclerViewFavorite.RecyclerVH>() {
    private var listFavourite = mutableListOf<Favorite>()

    fun getListFavourite(listFavourite : MutableList<Favorite>){
        this.listFavourite = listFavourite
        notifyDataSetChanged()
    }

    class RecyclerVH(view : View) : RecyclerView.ViewHolder(view){
        private val tvFavorite : TextView = view.findViewById(R.id.tv_favourite)
        private val ivFavorite : ImageView = view.findViewById(R.id.iv_fg_favourite)

        fun bind(data: Favorite){
            tvFavorite.text = data.nameFilm

            Glide.with(ivFavorite)
                .load("https://image.tmdb.org/t/p/w500${data.imageFilm}")
                .error(R.drawable.loading)
                .into(ivFavorite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        return RecyclerVH(view)
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        listFavourite.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return listFavourite.size
    }
}