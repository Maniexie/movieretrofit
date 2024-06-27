package com.maniexie.movieretrofit.service

import com.maniexie.movieretrofit.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=5fc74c731d0d5a8daac072159fceb4f7")
    fun getMoviePopular(): Call<MovieResponse>
}