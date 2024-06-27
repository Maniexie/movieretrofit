package com.maniexie.movieretrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.maniexie.movieretrofit.TVAdapter
import com.maniexie.movieretrofit.databinding.FragmentTvBinding
import com.maniexie.movieretrofit.models.TV
import com.maniexie.movieretrofit.models.TVResponse
import com.maniexie.movieretrofit.service.TVApiInterface
import com.maniexie.movieretrofit.service.TVApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TVFragment : Fragment() {
    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    private val tv = arrayListOf<TV>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTV.layoutManager = LinearLayoutManager(this.context)
        binding.rvTV.setHasFixedSize(true)
        getTVData { tv: List<TV> ->
            binding.rvTV.adapter = TVAdapter(tv)
        }
        showRecycleView()
    }

    private fun getTVData(callback: (List<TV>) -> Unit) {
        val apiService = TVApiService.getInstance().create(TVApiInterface::class.java)
        apiService.getTVPopular().enqueue(object : Callback<TVResponse> {
            override fun onFailure(call: Call<TVResponse>, t: Throwable) {}
            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                return callback(response.body()!!.television)
            }
        })
    }

    private fun showRecycleView() {
        binding.rvTV.layoutManager = LinearLayoutManager(this.context)
        binding.rvTV.adapter = TVAdapter(tv)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}