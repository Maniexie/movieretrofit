package com.maniexie.movieretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.maniexie.movieretrofit.databinding.ActivityTvDetailBinding
import com.maniexie.movieretrofit.models.TV

class TVDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TvS = "extra_tv"
    }

    private lateinit var binding: ActivityTvDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.imgItemPhoto.clipToOutline = true

        val detailTV = intent.getParcelableExtra<TV>(EXTRA_TvS)

        if (detailTV != null) {
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
            Glide.with(this).load(IMAGE_BASE + detailTV.poster).into(binding.imgItemPhoto)
            binding.tvItemName.text = detailTV.title
            binding.tvItemDescription.text = detailTV.overview
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
