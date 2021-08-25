package com.example.moviefilm.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.detail.Detail

class RecyclerViewTrailer : RecyclerView.Adapter<RecyclerViewTrailer.RecyclerVH>(){
    private var listTrailer = mutableListOf<Detail>()

    fun setListTrailer(listTrailer : MutableList<Detail>){
        this.listTrailer = listTrailer
        notifyDataSetChanged()
    }

    class RecyclerVH(view: View) : RecyclerView.ViewHolder(view){
        private val ivTrailer : ImageView = view.findViewById(R.id.ivTrailer)
        private val tvNameTrailer : TextView = view.findViewById(R.id.tvNameTrailer)

        fun bind(data : Detail){
            Glide.with(ivTrailer)
                .load(data.homepage)
                .error(R.drawable.loading)
                .into(ivTrailer)

            tvNameTrailer.text = data.originalTitle
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trailier_film, parent, false)
        return RecyclerVH(view)
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        listTrailer.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return listTrailer.size
    }
}