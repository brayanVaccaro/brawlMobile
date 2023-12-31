package com.example.brawlmobile.adapter.clashRoyale

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.listener.ClickListener
import com.example.brawlmobile.adapter.listener.GlideRequestListener
import com.example.brawlmobile.model.clashRoyale.CardModel


class ClashHomeAdapter(
    private val context: Context,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<ClashHomeAdapter.ViewHolder>() {

    private var cards: MutableList<CardModel> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardName: TextView
        val clickableCardImageMedium: ImageView
        val clickableSpriteHeart: ImageView
        val clashProgressBar: ProgressBar
//        val maxLevel: TextView
        val glideRequestListener: RequestListener<Drawable>

        init {
            cardName = view.findViewById(R.id.cardName)
            clickableCardImageMedium = view.findViewById(R.id.clickableCardImageMedium)
            clickableSpriteHeart = view.findViewById(R.id.iconFavourite)
            clashProgressBar = view.findViewById(R.id.clashHomeProgressBar)

            glideRequestListener = GlideRequestListener(clashProgressBar)
//            maxLevel = view.findViewById(R.id.maxLevel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_clash, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardModel = cards[position]
        holder.cardName.text = cardModel.name
//        holder.maxLevel.text = cardModel.maxLevel.toString()
        holder.clickableCardImageMedium.setOnClickListener {
            clickListener.onClickViewInfo(cardModel)
        }
        holder.clickableSpriteHeart.setOnClickListener {
            clickListener.onClickAddToFavourite(cardModel)
        }
        holder.clashProgressBar.visibility = View.VISIBLE
        Glide.with(context)
            .load(cardModel.urlNormal)
            .listener(holder.glideRequestListener)
            .into(holder.clickableCardImageMedium)

    }

    fun setCard(data: List<CardModel>) {
        cards.clear()
        cards.addAll(data)
        notifyDataSetChanged()
    }

}