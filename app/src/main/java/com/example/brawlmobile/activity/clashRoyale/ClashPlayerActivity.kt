package com.example.brawlmobile.activity.clashRoyale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.activity.brawlStars.FavouriteActivity
import com.example.brawlmobile.adapter.clashRoyale.ClashPlayerAdapterBadgeUnlocked

import com.example.brawlmobile.adapter.clashRoyale.ClashPlayerAdapterInfo
import com.example.brawlmobile.adapter.clashRoyale.ClashPlayerAdapterCardUnlocked
import com.example.brawlmobile.fragment.InputFragment
import com.example.brawlmobile.viewmodel.brawlStars.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.brawlStars.factory.PlayerActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ClashPlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayerActivityViewModel

    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var recyclerViewCards: RecyclerView
    private lateinit var recyclerViewBadges: RecyclerView

    private lateinit var adapter: ClashPlayerAdapterInfo
    private lateinit var adapterCardUnlocked: ClashPlayerAdapterCardUnlocked
    private lateinit var adapterBadgeUnlocked: ClashPlayerAdapterBadgeUnlocked

    private var TAG = "clashRoyale.PlayerActivity"
    private lateinit var expandPlayerInfo: LinearLayout
    private lateinit var expandUnlockedCards: LinearLayout
    private lateinit var expandUnlockedBadges: LinearLayout

    private val inputFragment = InputFragment()
//    private lateinit var playerTag: String

    //    tag personale: #80RL2LU2R
    // secondo tag personale: #JL8RPC0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_clash)

        viewModel = ViewModelProvider(
            this,
            PlayerActivityViewModelFactory(this)
        )[PlayerActivityViewModel::class.java]

        startInputFragment()

        val arrowIconPlayerInfo: ImageView = findViewById(R.id.arrowIconPlayerInfo)
        expandPlayerInfo = findViewById(R.id.expandPlayerInfo)

        val arrowIconUnlockedCards: ImageView = findViewById(R.id.arrowIconUnlockedCards)
        expandUnlockedCards = findViewById(R.id.expandUnlockedCards)

        val arrowIconUnlockedBadges: ImageView = findViewById(R.id.arrowIconUnlockedBadges)
        expandUnlockedBadges= findViewById(R.id.expandUnlockedBadges)



        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Intent(this, ClashHomeActivity::class.java).also {
                        startActivity(it)

                    }
                    true
                }
                R.id.menu_player -> {

                    true
                }
                R.id.menu_favourite -> {
                    Intent(this, FavouriteActivity::class.java)
                        .also {
                            startActivity(it)

                        }
                    true
                }
                else -> {
                    false
                }
            }
        }
        bottomNavigationView.selectedItemId = R.id.menu_player

        adapter = ClashPlayerAdapterInfo()
        adapterCardUnlocked = ClashPlayerAdapterCardUnlocked()
        adapterBadgeUnlocked = ClashPlayerAdapterBadgeUnlocked()

        recyclerViewInfo = findViewById(R.id.playerRecyclerViewInfo)
        recyclerViewInfo.layoutManager = LinearLayoutManager(this)
        recyclerViewInfo.adapter = adapter
        recyclerViewInfo.visibility = View.GONE


        recyclerViewCards = findViewById(R.id.playerRecyclerViewCards)
        recyclerViewCards.layoutManager = GridLayoutManager(this, 4)
        recyclerViewCards.adapter = adapterCardUnlocked
        recyclerViewCards.visibility = View.GONE

        recyclerViewBadges = findViewById(R.id.playerRecyclerViewBadges)
        recyclerViewBadges.layoutManager = GridLayoutManager(this, 3)
        recyclerViewBadges.adapter = adapterBadgeUnlocked
        recyclerViewBadges.visibility = View.GONE

        viewModel.clahsPlayerInfo.observe(this, Observer { info ->
            adapter.setInfo(info)
        })
        viewModel.playerCardsUnlocked.observe(this, Observer {card ->
            adapterCardUnlocked.setCard(card)
        })
        viewModel.playerBadgesUnlocked.observe(this, Observer {badge ->
            adapterBadgeUnlocked.setBadge(badge)
        })
        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            if (errorMessage.isNotEmpty()) {

                Log.e(TAG,"c'è errore, $errorMessage")
                inputFragment.showTagError()
            }
            else {
                Log.d(TAG,"sono nell'else, non c'è errore")
                val fragment = supportFragmentManager.beginTransaction()
                fragment.remove(inputFragment)
                fragment.commit()
                expandPlayerInfo.visibility = View.VISIBLE
                expandUnlockedCards.visibility = View.VISIBLE
                expandUnlockedBadges.visibility = View.VISIBLE
            }
        })

        //Gestiamo l'apertura della recycler delle info del player
        expandPlayerInfo.visibility = View.GONE
        expandPlayerInfo.setOnClickListener {
            if (recyclerViewInfo.visibility == View.GONE) {
                recyclerViewInfo.visibility = View.VISIBLE
                arrowIconPlayerInfo.setImageResource(R.drawable.ic_arrow_down)
            } else {
                recyclerViewInfo.visibility = View.GONE
                arrowIconPlayerInfo.setImageResource(R.drawable.ic_arrow_forward)
            }
        }

        //Gestiamo l'apertura della recycler delle carte sbloccate dal player
        expandUnlockedCards.visibility = View.GONE
        expandUnlockedCards.setOnClickListener {
            if (recyclerViewCards.visibility == View.GONE) {
                recyclerViewCards.visibility = View.VISIBLE
                arrowIconUnlockedCards.setImageResource(R.drawable.ic_arrow_down)

            } else {
                recyclerViewCards.visibility = View.GONE
                arrowIconUnlockedCards.setImageResource(R.drawable.ic_arrow_forward)
            }
        }

        //Gestiamo l'apertura della recycler dei badge sbloccati
        expandUnlockedBadges.visibility = View.GONE
        expandUnlockedBadges.setOnClickListener {
            if (recyclerViewBadges.visibility == View.GONE) {
                recyclerViewBadges.visibility = View.VISIBLE
                arrowIconUnlockedBadges.setImageResource(R.drawable.ic_arrow_down)
            } else {
                recyclerViewBadges.visibility = View.GONE
                arrowIconUnlockedBadges.setImageResource(R.drawable.ic_arrow_forward)
            }
        }


        //
        inputFragment.onTagSubmitted = { playerTag ->
            viewModel.getClashPlayerInfo(playerTag)

        }
    }

    private fun startInputFragment() {

        Log.d(TAG,"inizio il fragment di input")

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.clashInputFragmentContainer, inputFragment)
        transaction.commit()


    }


}