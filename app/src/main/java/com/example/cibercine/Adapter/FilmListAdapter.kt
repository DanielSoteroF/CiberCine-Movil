package com.example.cibercine.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.cibercine.Models.Film
import com.example.cibercine.databinding.ViewholderFilmBinding
import com.example.cibercine.databinding.ViewholderSliderBinding

class FilmListAdapter(private val items:ArrayList<Film>):RecyclerView.Adapter<FilmListAdapter.Viewholder>(){
    private var context: Context?=null

    inner class Viewholder(private val binding: ViewholderFilmBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film){
            binding.nameTxt.text=film.Title
            val requestOptions=RequestOptions()
                .transform(CenterCrop(), RoundedCorners(30))

            Glide.with(context!!)
                .load(film.Poster)
                .apply (requestOptions)
                .into(binding.pic)

            binding.root.setOnClickListener {

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmListAdapter.Viewholder {
        context=parent.context
        val binding= ViewholderFilmBinding.inflate(LayoutInflater.from(context),parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: FilmListAdapter.Viewholder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =items.size


}