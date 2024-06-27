package com.maniexie.movieretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.maniexie.movieretrofit.fragment.MovieFragment
import com.maniexie.movieretrofit.fragment.ProfileFragment
import com.maniexie.movieretrofit.fragment.TVFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val movieFragment = MovieFragment()
        val tvFragment = TVFragment()
        val profileFragment = ProfileFragment()


        showFragment(movieFragment)


        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_movie ->showFragment(movieFragment)
                R.id.menu_tv ->showFragment(tvFragment)
                R.id.menu_profile ->showFragment(profileFragment)
            }
            true
        }

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fram_nav, fragment)
            commit()
        }

    }
}

