package com.example.brawlmobile.adapter.brawlStars

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.model.brawlStar.player.PlayerInfoModel

class PlayerAdapterInfo(
): RecyclerView.Adapter<PlayerAdapterInfo.ViewHolder>() {

    private var infoPlayer: MutableList<PlayerInfoModel> = mutableListOf()
    private val TAG = "PlayerAdapterInfo"

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtPlayerTag: TextView
        val txtPlayerName: TextView
        val txtTrophies: TextView
        val txtHightestTrophies: TextView
        val txtExpLevel: TextView
        val txtExpPoints: TextView
        val txtIsQualifiedFromChampChallenge: TextView
        val txtThreeVsThreeVictories: TextView
        val txtSoloVictories: TextView
        val txtDuoVictories: TextView
        val txtBestRoboRumbleTime: TextView
        val txtBestTimeAsBigBrawler: TextView


    init {
        txtPlayerTag = view.findViewById(R.id.tag)
        txtPlayerName = view.findViewById(R.id.name)
        txtTrophies = view.findViewById(R.id.trophies)
        txtHightestTrophies = view.findViewById(R.id.hightestTrophies)
        txtExpLevel = view.findViewById(R.id.expLevel)
        txtExpPoints = view.findViewById(R.id.expPoints)
        txtIsQualifiedFromChampChallenge = view.findViewById(R.id.isQualifiedFromChampChallenge)
        txtThreeVsThreeVictories = view.findViewById(R.id.ThreeVsThreeVictories)
        txtSoloVictories = view.findViewById(R.id.soloVictories)
        txtDuoVictories = view.findViewById(R.id.duoVictories)
        txtBestRoboRumbleTime = view.findViewById(R.id.bestRoboRumbleTime)
        txtBestTimeAsBigBrawler = view.findViewById(R.id.bestTimeAsBigBrawler)
    }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_info_v2, parent, false)
        Log.d(TAG,"onCreate")
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return infoPlayer.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoPlayerModel = infoPlayer[position]
        holder.txtPlayerTag.text = infoPlayerModel.tag
        holder.txtPlayerName.text = infoPlayerModel.name
        holder.txtTrophies.text = infoPlayerModel.trophies.toString()
        holder.txtHightestTrophies.text = infoPlayerModel.highestTrophies.toString()
        holder.txtExpLevel.text = infoPlayerModel.expLevel.toString()
        holder.txtExpPoints.text = infoPlayerModel.expPoints.toString()
        holder.txtIsQualifiedFromChampChallenge.text = infoPlayerModel.isQualifiedFromChampionshipChallenge.toString()
        holder.txtThreeVsThreeVictories.text = infoPlayerModel.threeVsThreeVictories.toString()
        holder.txtSoloVictories.text = infoPlayerModel.soloVictories.toString()
        holder.txtDuoVictories.text = infoPlayerModel.duoVictories.toString()
        holder.txtBestRoboRumbleTime.text = infoPlayerModel.bestRoboRumbleTime.toString()
        holder.txtBestTimeAsBigBrawler.text = infoPlayerModel.bestTimeAsBigBrawler.toString()

    }

    fun setInfo(data: PlayerInfoModel) {
        infoPlayer.clear()
        infoPlayer.add(data)
        notifyDataSetChanged()
    }


}