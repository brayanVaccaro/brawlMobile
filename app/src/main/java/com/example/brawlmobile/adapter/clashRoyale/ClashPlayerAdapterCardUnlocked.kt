package com.example.brawlmobile.adapter.clashRoyale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.remote.clashRoyale.model.Card

class ClashPlayerAdapterCardUnlocked: RecyclerView.Adapter<ClashPlayerAdapterCardUnlocked.ViewHolder>() {

    private val cards: MutableList<Card> = mutableListOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardNameTextView: TextView = view.findViewById(R.id.cardNameTextView)

    }

    fun setCard(data_card: List<Card>) {
        cards.clear()
        cards.addAll(data_card)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_clash_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.cardNameTextView.text = card.name
    }

}