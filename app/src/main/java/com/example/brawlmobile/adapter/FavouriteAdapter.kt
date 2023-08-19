package com.example.brawlmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.data.entities.FavouriteBrawlerEntity

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    private var favouriteBrawlers: List<FavouriteBrawlerEntity> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtProvaNameFavourite: TextView

        init {
            txtProvaNameFavourite = view.findViewById(R.id.provaNameFavourite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favourite, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favouriteBrawlers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favouriteBrawler = favouriteBrawlers[position]
        holder.txtProvaNameFavourite.text = favouriteBrawler.name
    }

    fun setData(data: List<FavouriteBrawlerEntity>) {
        favouriteBrawlers = data
        notifyDataSetChanged()
    }
}