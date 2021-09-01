package com.example.moviefilm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviefilm.fragment.favourite.FavoriteFragment
import com.example.moviefilm.fragment.discover.DiscoverFragment

@Suppress("DEPRECATION")
class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> DiscoverFragment()
            1 -> FavoriteFragment()
            else -> DiscoverFragment()
        }
    }
}