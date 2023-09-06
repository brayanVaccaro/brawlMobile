package com.example.brawlmobile.adapter.clashRoyale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.remote.clashRoyale.model.Badge

class ClashPlayerAdapterBadgeUnlocked: RecyclerView.Adapter<ClashPlayerAdapterBadgeUnlocked.ViewHolder>() {

    private val badges: MutableList<Badge> = mutableListOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val badgeNameTextView: TextView = view.findViewById(R.id.badgeNameTextView)
    }
    fun setBadge(data_badge: List<Badge>) {
        badges.clear()
        badges.addAll(data_badge)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_clash_badge, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return badges.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val badge = badges[position]

        holder.badgeNameTextView.text = badge.name
    }
}