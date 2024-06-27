package com.maniexie.movieretrofit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maniexie.movieretrofit.models.Movie

import com.maniexie.movieretrofit.databinding.ListMovieBinding

class MovieAdapter(
    private val movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(private val binding: ListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvDate.text = movie.date
            Glide.with(binding.root).load(IMAGE_BASE + movie.poster).into(binding.moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movies.get(position))

        holder.itemView.setOnClickListener {
            moveToMoviesDetail(movie, it)
        }
    }

    private fun moveToMoviesDetail(movie: Movie, it: View) {
        val detailMoviesIntent = Intent(it.context, MovieDetailActivity::class.java)
        detailMoviesIntent.putExtra(MovieDetailActivity.EXTRA_MOVIES, movie)
        it.context.startActivity(detailMoviesIntent)

    }
}