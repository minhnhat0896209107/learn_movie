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
import com.example.moviefilm.pojo.model.list_movie.Movie


@Suppress("DEPRECATION")
class DiscoverAdapter(
    protected val onLoadMore : () -> Unit,
    private val onItemClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<DiscoverAdapter.DiscoverVH>() {
    private var items = mutableListOf<Movie>()
    private var isLoading : Boolean = false

    fun setData(items: List<Movie>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun updateAdapter(movie: List<Movie>, focusUpdate: Boolean = false){
        if (focusUpdate){
            items.clear()
            items.addAll(movie)
            notifyDataSetChanged()
        }else{
            val currentSize = items.size
            items.addAll(movie)
            notifyItemRangeInserted(currentSize, items.size)
        }
        isLoading = false
    }


    class DiscoverVH(
        view: View,
        private val onItemClickListener: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val ivFilm: ImageView = view.findViewById(R.id.ivFilm)
        private val tvNameFilm: TextView = view.findViewById(R.id.tvNameFilm)
        fun bind(data: Movie) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(
            R.layout.item_image_film,
            parent,
            false
        )
        return DiscoverVH(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: DiscoverVH, position: Int) {
        items.let { holder.bind(it[position]) }
        if (!isLoading && items.size >= 10 && position + 2 == items.lastIndex ){
            isLoading = true
            onLoadMore.invoke()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}