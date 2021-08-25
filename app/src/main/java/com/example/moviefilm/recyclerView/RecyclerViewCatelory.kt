package com.example.moviefilm.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.detail.Genre

class RecyclerViewCatelory : RecyclerView.Adapter<RecyclerViewCatelory.RecyclerVH>() {
    private var listCatelory = mutableListOf<Genre>()

    fun setListCatelory(listCatelory: MutableList<Genre>){
        this.listCatelory = listCatelory
        notifyDataSetChanged()
    }

    class RecyclerVH(view : View) : RecyclerView.ViewHolder(view){
        private var tvCategory: TextView = view.findViewById(R.id.tvCatelory)

        fun bind(data: Genre){
            tvCategory.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        return RecyclerVH(view)
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        listCatelory.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return listCatelory.size
    }
}