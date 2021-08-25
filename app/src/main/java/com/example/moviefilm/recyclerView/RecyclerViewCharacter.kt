package com.example.moviefilm.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.detail.ProductionCompany

class RecyclerViewCharacter : RecyclerView.Adapter<RecyclerViewCharacter.RecyclerVH>() {
    private var listCharacter = mutableListOf<ProductionCompany>()

    fun setListCharacter(listCharacter: MutableList<ProductionCompany>){
        this.listCharacter = listCharacter
        notifyDataSetChanged()
    }
    class RecyclerVH(view: View): RecyclerView.ViewHolder(view){
        private val ivCharacter : ImageView = view.findViewById(R.id.ivCharacter)
        private val tvNameCharacter : TextView = view.findViewById(R.id.tvNameCharacter)
        fun bind(data: ProductionCompany){
            tvNameCharacter.text = data.name
            Glide.with(ivCharacter)
                .load("https://image.tmdb.org/t/p/w500${data.logoPath}")
                .error(R.drawable.loading)
                .into(ivCharacter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return RecyclerVH(view)
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        listCharacter.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return listCharacter.size
    }
}