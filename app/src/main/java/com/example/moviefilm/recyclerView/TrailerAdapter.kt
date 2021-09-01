package com.example.moviefilm.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.list_video.Result

class TrailerAdapter : RecyclerView.Adapter<TrailerAdapter.TrailerVH>(){
    private var listTrailer = mutableListOf<Result>()

    fun setListTrailer(listTrailer : List<Result>){
        this.listTrailer.addAll(listTrailer)
        notifyDataSetChanged()
    }

    class TrailerVH(view: View) : RecyclerView.ViewHolder(view){
        private val ivTrailer : ImageView = view.findViewById(R.id.ivTrailer)
        private val tvNameTrailer : TextView = view.findViewById(R.id.tvNameTrailer)

        fun bind(data : Result){
            Glide.with(ivTrailer)
                .load("https://www.youtube.com/watch?v=${data.key}")
                .error(R.drawable.loading)
                .into(ivTrailer)

            tvNameTrailer.text = data.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trailier_film, parent, false)
        return TrailerVH(view)
    }

    override fun onBindViewHolder(holder: TrailerVH, position: Int) {
        listTrailer.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return listTrailer.size
    }
}