package com.maniexie.movieretrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.maniexie.movieretrofit.MovieAdapter
import com.maniexie.movieretrofit.databinding.FragmentMovieBinding
import com.maniexie.movieretrofit.models.Movie
import com.maniexie.movieretrofit.models.MovieResponse
import com.maniexie.movieretrofit.service.MovieApiInterface
import com.maniexie.movieretrofit.service.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val movies = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovie.layoutManager = LinearLayoutManager(this.context)
        binding.rvMovie.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            binding.rvMovie.adapter = MovieAdapter(movies)
        }
        showRecycleView()
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMoviePopular().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
        })
    }

    private fun showRecycleView() {
        binding.rvMovie.layoutManager = LinearLayoutManager(this.context)
        binding.rvMovie.adapter = MovieAdapter(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
