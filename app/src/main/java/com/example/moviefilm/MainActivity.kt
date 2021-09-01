package com.example.moviefilm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.moviefilm.R
import com.example.moviefilm.adapter.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navigation : BottomNavigationView
    lateinit var viewPager : ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        eventListener()
    }

    private fun eventListener() {
        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.discover -> viewPager.currentItem = 0
                R.id.favorite -> viewPager.currentItem = 1
            }
            true
        }


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> navigation.menu.findItem(R.id.discover).isChecked = true
                    1 -> navigation.menu.findItem(R.id.favorite).isChecked = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun init(){
        navigation = findViewById(R.id.navigation)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
    }
}