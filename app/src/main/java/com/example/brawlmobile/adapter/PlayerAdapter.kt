package com.example.brawlmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.models.brawler.BrawlerModel
import com.example.brawlmobile.models.player.PlayerInfoModel

class PlayerAdapter(
): RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    private var infoPlayer: MutableList<PlayerInfoModel> = mutableListOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtProva: TextView


    init {
        txtProva = view.findViewById(R.id.provaText)
    }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return infoPlayer.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoPlayerModel = infoPlayer[position]
        holder.txtProva.text = infoPlayerModel.tag

    }

    fun setInfo(data: PlayerInfoModel) {
        infoPlayer.clear()
        infoPlayer.add(data)
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onButtonClick(): String
    }

}