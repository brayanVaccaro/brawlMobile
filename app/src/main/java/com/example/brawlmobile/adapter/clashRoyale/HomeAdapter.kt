package com.example.brawlmobile.adapter.clashRoyale

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
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
import com.example.brawlmobile.adapter.listener.GlideRequestListener
import com.example.brawlmobile.model.clashRoyale.CardModel

//import com.example.brawlmobile.clashRoyale.adapter.listener.GlideRequestListener

class HomeAdapter(
    private val context: Context
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var cards: MutableList<CardModel> = mutableListOf()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardName: TextView
        val cardImageMedium: ImageView
        val clashProgressBar: ProgressBar
//        val maxLevel: TextView
//        val cardImageEvolutionMedium: ImageView
        val glideRequestListener: RequestListener<Drawable>


        init {
            cardName = view.findViewById(R.id.cardName)
            cardImageMedium = view.findViewById(R.id.cardImageMedium)
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
        Log.d("clashRoyale.HomeAdapter","loading image named ${cardModel.imageUrl}")
        Glide.with(context)
            .load(cardModel.imageUrl)
            .listener(holder.glideRequestListener)
            .into(holder.cardImageMedium)
//        if (!cardModel.iconUrls.evolutionMedium.isNullOrEmpty()) {
//            Glide.with(context)
//                .load(cardModel.iconUrls.medium)
//                .into(holder.cardImageEvolutionMedium)
//        }
    }

    fun setCard(data: List<CardModel>) {
        cards.clear()
        cards.addAll(data)
        notifyDataSetChanged()
    }

}