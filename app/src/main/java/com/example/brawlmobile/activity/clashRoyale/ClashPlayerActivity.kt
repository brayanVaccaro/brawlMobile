package com.example.brawlmobile.activity.clashRoyale

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.StartActivity
import com.example.brawlmobile.adapter.clashRoyale.ClashPlayerAdapterBadgeUnlocked
import com.example.brawlmobile.adapter.clashRoyale.ClashPlayerAdapterCardUnlocked
import com.example.brawlmobile.adapter.clashRoyale.ClashPlayerAdapterInfo
import com.example.brawlmobile.fragment.InputFragment
import com.example.brawlmobile.viewmodel.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MyCustomViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ClashPlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayerActivityViewModel

    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var recyclerViewCards: RecyclerView
    private lateinit var recyclerViewBadges: RecyclerView

    private lateinit var adapterPlayerInfo: ClashPlayerAdapterInfo
    private lateinit var adapterCardUnlocked: ClashPlayerAdapterCardUnlocked
    private lateinit var adapterBadgeUnlocked: ClashPlayerAdapterBadgeUnlocked

    private var TAG = "clashRoyale.PlayerActivity"
    private lateinit var expandPlayerInfo: TextView
    private lateinit var expandUnlockedCards: TextView
    private lateinit var expandUnlockedBadges: TextView

    private lateinit var drawableArrowForward: Drawable
    private lateinit var drawableArrowDown: Drawable
    private val inputFragment = InputFragment()
//    private lateinit var playerTag: String

    //    tag personale: #80RL2LU2R
    // secondo tag personale: #JL8RPC0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clash_player)

        viewModel = ViewModelProvider(
            this,
            MyCustomViewModelFactory(this, this::class.java)
        )[PlayerActivityViewModel::class.java]

        startInputFragment()

        drawableArrowForward = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_forward, null)!!
        drawableArrowDown = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null)!!
//        val arrowIconPlayerInfo: ImageView = findViewById(R.id.arrowIconPlayerInfo)
        expandPlayerInfo = findViewById(R.id.expandPlayerInfo)

//        val arrowIconUnlockedCards: ImageView = findViewById(R.id.arrowIconUnlockedCards)
        expandUnlockedCards = findViewById(R.id.expandUnlockedCards)

//        val arrowIconUnlockedBadges: ImageView = findViewById(R.id.arrowIconUnlockedBadges)
        expandUnlockedBadges = findViewById(R.id.expandUnlockedBadges)


        adapterPlayerInfo = ClashPlayerAdapterInfo()
        adapterCardUnlocked = ClashPlayerAdapterCardUnlocked()
        adapterBadgeUnlocked = ClashPlayerAdapterBadgeUnlocked(this)

        recyclerViewInfo = findViewById(R.id.playerRecyclerViewInfo)
        recyclerViewInfo.layoutManager = LinearLayoutManager(this)
        recyclerViewInfo.adapter = adapterPlayerInfo
        recyclerViewInfo.visibility = View.GONE


        recyclerViewCards = findViewById(R.id.playerRecyclerViewCards)
        recyclerViewCards.layoutManager = GridLayoutManager(this, 4)
        recyclerViewCards.adapter = adapterCardUnlocked
        recyclerViewCards.visibility = View.GONE

        recyclerViewBadges = findViewById(R.id.playerRecyclerViewBadges)
        recyclerViewBadges.layoutManager = GridLayoutManager(this, 3)
        recyclerViewBadges.adapter = adapterBadgeUnlocked
        recyclerViewBadges.visibility = View.GONE

        viewModel.clashPlayerInfo.observe(this, Observer { info ->
            adapterPlayerInfo.setInfo(info)
        })
        viewModel.playerCardsUnlocked.observe(this, Observer { card ->
            adapterCardUnlocked.setCard(card)
        })
        viewModel.playerBadgesUnlocked.observe(this, Observer { badge ->
            adapterBadgeUnlocked.setBadge(badge)
        })
        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                inputFragment.showTagError()
            } else {
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
                expandPlayerInfo.setCompoundDrawablesWithIntrinsicBounds(drawableArrowDown, null, null, null)

            } else {
                recyclerViewInfo.visibility = View.GONE
                expandPlayerInfo.setCompoundDrawablesWithIntrinsicBounds(drawableArrowForward, null, null, null)
            }
        }

        //Gestiamo l'apertura della recycler delle carte sbloccate dal player
        expandUnlockedCards.visibility = View.GONE
        expandUnlockedCards.setOnClickListener {
            if (recyclerViewCards.visibility == View.GONE) {
                recyclerViewCards.visibility = View.VISIBLE
                expandUnlockedCards.setCompoundDrawablesWithIntrinsicBounds(drawableArrowDown, null, null, null)

            } else {
                recyclerViewCards.visibility = View.GONE
                expandUnlockedCards.setCompoundDrawablesWithIntrinsicBounds(drawableArrowForward, null, null, null)
            }
        }

        //Gestiamo l'apertura della recycler dei badge sbloccati
        expandUnlockedBadges.visibility = View.GONE
        expandUnlockedBadges.setOnClickListener {
            if (recyclerViewBadges.visibility == View.GONE) {
                recyclerViewBadges.visibility = View.VISIBLE
                expandUnlockedBadges.setCompoundDrawablesWithIntrinsicBounds(drawableArrowDown, null, null, null)
            } else {
                recyclerViewBadges.visibility = View.GONE
                expandUnlockedBadges.setCompoundDrawablesWithIntrinsicBounds(drawableArrowForward, null, null, null)
            }
        }

        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
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
                    Intent(this, ClashFavouriteActivity::class.java)
                        .also {
                            startActivity(it)

                        }
                    true
                }
                else -> {
                    Intent(this, StartActivity::class.java)
                        .also {
                            startActivity(it)
                        }
                    true
                }
            }
        }
        bottomNavigationView.selectedItemId = R.id.menu_player


        //
        inputFragment.onTagSubmitted = { playerTag ->
            viewModel.getClashPlayerInfo(playerTag)

        }
    }

    private fun startInputFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.clashInputFragmentContainer, inputFragment)
        transaction.commit()


    }


}