package com.example.brawlmobile.adapter.clashRoyale

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.model.clashRoyale.CardModel

//import com.example.brawlmobile.clashRoyale.adapter.listener.GlideRequestListener

class HomeAdapter(context: Context): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var cards: MutableList<CardModel> = mutableListOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val provaTextClash: TextView


        init {
            provaTextClash = view.findViewById(R.id.provaTextClash)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_clash,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardModel = cards[position]
        holder.provaTextClash.text = cardModel.name
    }

    fun setCard(data: List<CardModel>) {
        cards.clear()
        cards.addAll(data)
        notifyDataSetChanged()
    }

}