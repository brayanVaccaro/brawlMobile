package com.example.brawlmobile.adapter.clashRoyale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.model.clashRoyale.ClashPlayerInfoModel

class ClashPlayerAdapterInfo: RecyclerView.Adapter<ClashPlayerAdapterInfo.ViewHolder>() {

    private val infoPlayer: MutableList<ClashPlayerInfoModel> = mutableListOf()




    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val infoNameTextView: TextView = view.findViewById(R.id.infoNameTextView)

        /*val trophiesTextView: TextView = view.findViewById(R.id.trophiesTextView)
        val bestTrophiesTextView: TextView = view.findViewById(R.id.bestTrophiesTextView)
        val winsTextView: TextView = view.findViewById(R.id.winsTextView)
        val lossesTextView: TextView = view.findViewById(R.id.lossesTextView)
        val battleCountTextView: TextView = view.findViewById(R.id.battleCountTextView)
        val threeCrownWinsTextView: TextView = view.findViewById(R.id.threeCrownWinsTextView)

        val challengeCardsWonTextView: TextView = view.findViewById(R.id.challengeCardsWonTextView)
        val challengeMaxWinsTextView: TextView = view.findViewById(R.id.challengeMaxWinsTextView)
        val tournamentCardsWonTextView: TextView = view.findViewById(R.id.tournamentCardsWonTextView)
        val tournamentBattleCountTextView: TextView = view.findViewById(R.id.tournamentBattleCountTextView)
        val roleTextView: TextView = view.findViewById(R.id.roleTextView)
        val donationsTextView: TextView = view.findViewById(R.id.donationsTextView)
        val donationsReceivedTextView: TextView = view.findViewById(R.id.donationsReceivedTextView)
        val totalDonationsTextView: TextView = view.findViewById(R.id.totalDonationsTextView)
        val warDayWinsTextView: TextView = view.findViewById(R.id.warDayWinsTextView)
        val clanCardsCollectedTextView: TextView = view.findViewById(R.id.clanCardsCollectedTextView)
        val clanTextView: TextView = view.findViewById(R.id.clanTextView)
        val arenaTextView: TextView = view.findViewById(R.id.arenaTextView)
        val leagueStatisticsTextView: TextView = view.findViewById(R.id.leagueStatisticsTextView)
        val badgesRecyclerView: RecyclerView = view.findViewById(R.id.badgesRecyclerView)
        val achievementsRecyclerView: RecyclerView = view.findViewById(R.id.achievementsRecyclerView)
        val cardsRecyclerView: RecyclerView = view.findViewById(R.id.cardsRecyclerView)
        val currentDeckRecyclerView: RecyclerView = view.findViewById(R.id.currentDeckRecyclerView)
        val currentFavouriteCardTextView: TextView = view.findViewById(R.id.currentFavouriteCardTextView)
        val starPointsTextView: TextView = view.findViewById(R.id.starPointsTextView)
        val expPointsTextView: TextView = view.findViewById(R.id.expPointsTextView)
        val legacyTrophyRoadHighScoreTextView: TextView? = view.findViewById(R.id.legacyTrophyRoadHighScoreTextView)
        val currentPathOfLegendSeasonResultTextView: TextView? = view.findViewById(R.id.currentPathOfLegendSeasonResultTextView)
        val lastPathOfLegendSeasonResultTextView: TextView? = view.findViewById(R.id.lastPathOfLegendSeasonResultTextView)
        val bestPathOfLegendSeasonResultTextView: TextView? = view.findViewById(R.id.bestPathOfLegendSeasonResultTextView)
        val totalExpPointsTextView: TextView = view.findViewById(R.id.totalExpPointsTextView)*/
    }


    fun setInfo(data_info: ClashPlayerInfoModel) {
        infoPlayer.clear()
        infoPlayer.add(data_info)
        notifyDataSetChanged()
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_info_clash, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return infoPlayer.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoModel = infoPlayer[position]


        holder.infoNameTextView.text = infoModel.name

    }

}