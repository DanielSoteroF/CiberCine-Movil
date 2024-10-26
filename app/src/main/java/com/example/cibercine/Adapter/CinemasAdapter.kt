package com.example.cibercine.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.cibercine.Activity.FilmDetailActivity
import com.example.cibercine.Models.Cinemas
import com.example.cibercine.Models.Film
import com.example.cibercine.databinding.ViewholderCinemasBinding
import com.example.cibercine.databinding.ViewholderFilmBinding

class CinemasAdapter(private val items:ArrayList<Cinemas>):RecyclerView.Adapter<CinemasAdapter.Viewholder>(){
    private var context: Context?=null

    inner class Viewholder(private val binding: ViewholderCinemasBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(cinemas: Cinemas){
            binding.placeTxt.text=cinemas.Place
            binding.streetTxt.text=cinemas.Street
            binding.theatersTxt.text=cinemas.Theaters
            val requestOptions= RequestOptions()
                .transform(CenterCrop(), RoundedCorners(30))

            Glide.with(context!!)
                .load(cinemas.Image)
                .apply (requestOptions)
                .into(binding.pic)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemasAdapter.Viewholder {
        context=parent.context
        val binding= ViewholderCinemasBinding.inflate(LayoutInflater.from(context),parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CinemasAdapter.Viewholder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =items.size

}