package com.example.moviefilm.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.list_movie.Movie3

class RecyclerViewDiscover3(
    private val onItemClickListener: (Movie3) -> Unit
): RecyclerView.Adapter<RecyclerViewDiscover3.RecyclerVH>() {
    private var items = mutableListOf<Movie3>()
    fun setData(items: List<Movie3>){
        this.items = items as MutableList<Movie3>
        notifyDataSetChanged()
    }

    class RecyclerVH(
        view: View,
        private val onItemClickListener: (Movie3) -> Unit
    ) : RecyclerView.ViewHolder(view){
        private val ivFilm : ImageView = view.findViewById(R.id.ivFilm)
        private val tvNameFilm : TextView = view.findViewById(R.id.tvNameFilm)
        fun bind(data: Movie3){
            tvNameFilm.text = data.title
            Glide.with(ivFilm)
                .load("https://image.tmdb.org/t/p/w500${data.image_poster}")
                .apply(RequestOptions.centerCropTransform())
                .error(R.drawable.loading)
                .into(ivFilm)

            itemView.setOnClickListener {
                onItemClickListener.invoke(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_film, parent, false)
        return RecyclerVH(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        items.let { holder.bind(it[position]) }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}