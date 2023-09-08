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
        val txtName: TextView = view.findViewById(R.id.name)
        val txtTag: TextView = view.findViewById(R.id.tag)
        val txtTrophies: TextView = view.findViewById(R.id.trophies)
        val txtHightestTrophies: TextView = view.findViewById(R.id.hightestTrophies)
        val txtArena: TextView = view.findViewById(R.id.arena)
        val txtExpLevel: TextView = view.findViewById(R.id.expLevel)
        val txtExpPoints: TextView = view.findViewById(R.id.expPoints)
        val txtWins: TextView = view.findViewById(R.id.wins)
        val txtLosses: TextView = view.findViewById(R.id.loss)
        val txtBattleCount: TextView = view.findViewById(R.id.battleCount)
        val txtThreeCrownWins: TextView = view.findViewById(R.id.threeCrownWins)
        val txtChallengeCardsWon: TextView = view.findViewById(R.id.challengeCardsWon)
        val txtChallengeMaxWins: TextView = view.findViewById(R.id.challengeMaxWins)
        val txtTournamentCardsWon: TextView = view.findViewById(R.id.tournamentCardsWon)
        val txtTournamentBattleCount: TextView = view.findViewById(R.id.tournamentBattleCount)

        /*

        val txtRole: TextView = view.findViewById(R.id.roleTextView)
        val txtDonations: TextView = view.findViewById(R.id.donationsTextView)
        val txtDonationsReceived: TextView = view.findViewById(R.id.donationsReceivedTextView)
        val txtTotalDonations: TextView = view.findViewById(R.id.totalDonationsTextView)
        val txtWarDayWins: TextView = view.findViewById(R.id.warDayWinsTextView)
        val txtClanCardsCollected: TextView = view.findViewById(R.id.clanCardsCollectedTextView)
        val txtClan: TextView = view.findViewById(R.id.clanTextView)

        val txtLeagueStatistics: TextView = view.findViewById(R.id.leagueStatisticsTextView)
        val badgesRecyclerView: RecyclerView = view.findViewById(R.id.badgesRecyclerView)
        val achievementsRecyclerView: RecyclerView = view.findViewById(R.id.achievementsRecyclerView)

        val txtCurrentFavouriteCard: TextView = view.findViewById(R.id.currentFavouriteCardTextView)
        val txtStarPoints: TextView = view.findViewById(R.id.starPointsTextView)

        val txtLegacyTrophyRoadHighScore: TextView? = view.findViewById(R.id.legacyTrophyRoadHighScoreTextView)
        val txtCurrentPathOfLegendSeasonResult: TextView? = view.findViewById(R.id.currentPathOfLegendSeasonResultTextView)
        val txtLastPathOfLegendSeasonResult: TextView? = view.findViewById(R.id.lastPathOfLegendSeasonResultTextView)
        val txtBestPathOfLegendSeasonResult: TextView? = view.findViewById(R.id.bestPathOfLegendSeasonResultTextView)
        */
    }


    fun setInfo(data_info: ClashPlayerInfoModel) {
        infoPlayer.clear()
        infoPlayer.add(data_info)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_clash_info, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return infoPlayer.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoModel = infoPlayer[position]
        holder.txtName.text = infoModel.name
        holder.txtTag.text = infoModel.tag
        holder.txtTrophies.text = infoModel.trophies.toString()
        holder.txtHightestTrophies.text = infoModel.bestTrophies.toString()
        holder.txtArena.text = infoModel.arena.name
        holder.txtExpLevel.text = infoModel.expLevel.toString()
        holder.txtExpPoints.text = infoModel.expPoints.toString()
        holder.txtWins.text = infoModel.wins.toString()
        holder.txtLosses.text = infoModel.losses.toString()
        holder.txtBattleCount.text = infoModel.battleCount.toString()
        holder.txtThreeCrownWins.text = infoModel.threeCrownWins.toString()
        holder.txtChallengeCardsWon.text = infoModel.challengeCardsWon.toString()
        holder.txtChallengeMaxWins.text = infoModel.challengeMaxWins.toString()
        holder.txtTournamentCardsWon.text = infoModel.tournamentCardsWon.toString()
        holder.txtTournamentBattleCount.text = infoModel.tournamentBattleCount.toString()


    }

}