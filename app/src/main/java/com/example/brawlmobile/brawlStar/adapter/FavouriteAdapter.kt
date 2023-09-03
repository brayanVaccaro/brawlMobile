package com.example.brawlmobile.brawlStar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brawlmobile.R
import com.example.brawlmobile.brawlStar.data.entities.FavouriteBrawlerEntity

class FavouriteAdapter(
    private val context: Context,
    private val onClick: OnClick
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    private var favouriteBrawlers: List<FavouriteBrawlerEntity> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNameFavourite: TextView
        val imgFavourite: ImageView
        val deleteButton: ImageView

        init {
            txtNameFavourite = view.findViewById(R.id.itemName)
            imgFavourite = view.findViewById(R.id.img)
            deleteButton = view.findViewById(R.id.itemDelete)
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

    interface OnClick {
        fun deleteFromFavourites(name: String){}
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favouriteBrawler = favouriteBrawlers[position]
        holder.txtNameFavourite.text = favouriteBrawler.name
        Glide.with(context).load(favouriteBrawler.spriteUrl).into(holder.imgFavourite)
        holder.deleteButton.setOnClickListener{
            onClick.deleteFromFavourites(favouriteBrawler.name)
        }

    }

    fun setData(data: List<FavouriteBrawlerEntity>) {
        favouriteBrawlers = data
        notifyDataSetChanged()
    }
}