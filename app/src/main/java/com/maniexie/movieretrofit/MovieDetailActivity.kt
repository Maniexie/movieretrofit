package com.maniexie.movieretrofit

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.maniexie.movieretrofit.databinding.ActivityMovieDetailBinding
import com.maniexie.movieretrofit.models.Movie

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.imgItemPhoto.clipToOutline = true

        val detailMovies = intent.getParcelableExtra<Movie>(EXTRA_MOVIES)

        if (detailMovies != null) {
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
            Glide.with(this).load(IMAGE_BASE + detailMovies.poster).into(binding.imgItemPhoto)
            binding.tvItemName.text = detailMovies.title
            binding.tvItemDescription.text = detailMovies.overview
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
