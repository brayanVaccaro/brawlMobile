package com.example.brawlmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.models.player.PlayerBrawlersUnlocked
import com.example.brawlmobile.remote.model.BrawlersUnlocked

class PlayerAdapterBrawlersUnlocked() : RecyclerView.Adapter<PlayerAdapterBrawlersUnlocked.ViewHolder>() {

    private var playerBrawlersUnlocked: MutableList<BrawlersUnlocked> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtProvaName: TextView
//        val txtProvaId: TextView


        init {
            txtProvaName = view.findViewById(R.id.provaName)
//            txtProvaId = view.findViewById(R.id.provaId)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_brawler, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return playerBrawlersUnlocked.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoBrawlersUnlocked = playerBrawlersUnlocked[position]
        holder.txtProvaName.text = infoBrawlersUnlocked.name
//        holder.txtProvaId.text = infoBrawlersUnlocked.unlocked[position].id.toString()

    }

    fun setBrawlersUnlocked(data: List<BrawlersUnlocked>) {
//        playerBrawlersUnlocked.clear()
        playerBrawlersUnlocked.addAll(data)
        notifyDataSetChanged()
    }


}