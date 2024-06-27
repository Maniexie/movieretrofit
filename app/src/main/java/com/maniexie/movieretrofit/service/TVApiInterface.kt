package com.maniexie.movieretrofit.service

import com.maniexie.movieretrofit.models.TVResponse
import retrofit2.Call
import retrofit2.http.GET

interface TVApiInterface {
    @GET("3/tv/popular?api_key=5fc74c731d0d5a8daac072159fceb4f7")
    fun getTVPopular() : Call<TVResponse>
}