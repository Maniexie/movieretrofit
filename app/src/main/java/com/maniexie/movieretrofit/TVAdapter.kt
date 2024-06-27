package com.maniexie.movieretrofit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maniexie.movieretrofit.databinding.ListTvBinding
import com.maniexie.movieretrofit.models.TV

class TVAdapter(private val tvList: List<TV>) : RecyclerView.Adapter<TVAdapter.TVViewHolder>() {

    class TVViewHolder(private val binding: ListTvBinding) : RecyclerView.ViewHolder(binding.root) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindTV(tv: TV) {
            binding.tvTitle.text = tv.title
            binding.tvDate.text = tv.date
            Glide.with(binding.root).load(IMAGE_BASE + tv.poster).into(binding.tvPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val binding = ListTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVViewHolder(binding)
    }

    override fun getItemCount(): Int = tvList.size

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        val tv = tvList[position]
        holder.bindTV(tv)

        holder.itemView.setOnClickListener {
            moveToTVDetail(tv, it)
        }
    }

    private fun moveToTVDetail(tv: TV, view: View) {
        val detailIntent = Intent(view.context, TVDetailActivity::class.java)
        detailIntent.putExtra(TVDetailActivity.EXTRA_TvS, tv)
        view.context.startActivity(detailIntent)
    }
}
