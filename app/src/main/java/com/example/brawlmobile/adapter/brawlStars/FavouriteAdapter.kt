package com.example.brawlmobile.adapter.brawlStars

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.ClickListener
import com.example.brawlmobile.data.entities.BrawlerEntity
import com.example.brawlmobile.data.entities.CardEntity

class FavouriteAdapter(
    private val context: Context,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    private var favouriteItem: List<Any> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgFavourite: ImageView
        val txtNameFavourite: TextView
        val deleteButton: ImageView

        init {
            imgFavourite = view.findViewById(R.id.img)
            txtNameFavourite = view.findViewById(R.id.itemName)
            deleteButton = view.findViewById(R.id.itemDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favourite, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favouriteItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = favouriteItem[position]
        if (item is BrawlerEntity) {
            // È un oggetto BrawlerEntity
            Glide.with(context)
                .load(item.spriteUrl)
                .error(R.drawable.ic_delete)
                .into(holder.imgFavourite)
            holder.txtNameFavourite.text = item.name
            holder.deleteButton.setOnClickListener {
                clickListener.deleteFromFavourites(item.name)
            }
        } else if (item is CardEntity) {
            // È un oggetto CardEntity
            Glide.with(context)
                .load(item.spriteUrl)
                .into(holder.imgFavourite)
            holder.txtNameFavourite.text = item.name
            holder.deleteButton.setOnClickListener {
                clickListener.deleteFromFavourites(item.name)
            }
        }
    }

    fun setFavouriteItem(item: List<Any>) {
        favouriteItem = item
        notifyDataSetChanged()
    }

}