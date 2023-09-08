package com.example.brawlmobile.adapter.brawlStars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.remote.brawlStars.model.BrawlersUnlocked

class PlayerAdapterBrawlersUnlocked() : RecyclerView.Adapter<PlayerAdapterBrawlersUnlocked.ViewHolder>() {

    private var playerBrawlersUnlocked: MutableList<BrawlersUnlocked> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView= view.findViewById(R.id.brawlerUnlockedName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_brawl_brawler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerBrawlersUnlocked.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoBrawlersUnlocked = playerBrawlersUnlocked[position]
        holder.txtName.text = infoBrawlersUnlocked.name
    }

    fun setBrawlersUnlocked(data: List<BrawlersUnlocked>) {
        playerBrawlersUnlocked.clear()
        playerBrawlersUnlocked.addAll(data)
        notifyDataSetChanged()
    }
}