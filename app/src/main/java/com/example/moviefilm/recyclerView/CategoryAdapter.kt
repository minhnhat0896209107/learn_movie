package com.example.moviefilm.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefilm.R
import com.example.moviefilm.pojo.model.detail.Genre

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    private var listCategory = mutableListOf<Genre>()

    fun setListCategory(listCategory: MutableList<Genre>){
        this.listCategory = listCategory
        notifyDataSetChanged()
    }

    class CategoryVH(view : View) : RecyclerView.ViewHolder(view){
        private var tvCategory: TextView = view.findViewById(R.id.tvCategory)

        fun bind(data: Genre){
            tvCategory.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        return CategoryVH(view)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        listCategory.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }
}