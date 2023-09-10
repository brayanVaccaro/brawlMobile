package com.example.brawlmobile.activity.brawlStars

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
import com.example.brawlmobile.adapter.brawlStars.BrawlPlayerAdapterBrawlersUnlocked
import com.example.brawlmobile.adapter.brawlStars.BrawlPlayerAdapterInfo
import com.example.brawlmobile.fragment.InputFragment
import com.example.brawlmobile.viewmodel.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MyCustomViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class BrawlPlayerActivity : AppCompatActivity() {

    private lateinit var viewModel: PlayerActivityViewModel

    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var recyclerViewBrawlers: RecyclerView

    private lateinit var adapterInfo: BrawlPlayerAdapterInfo
    private lateinit var adapterBrawlersUnlocked: BrawlPlayerAdapterBrawlersUnlocked

    private var TAG = "BrawlPlayerActivity"

    private lateinit var expandPlayerInfo: TextView
    private lateinit var expandUnlocked: TextView
    private lateinit var drawableArrowForward: Drawable
    private lateinit var drawableArrowDown: Drawable

    private lateinit var bottomNavigationView: BottomNavigationView

    private val inputFragment = InputFragment()

    //    tag personale: #PRGG0V09G
    // secondo tag personale: #29G8QYCYG
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brawl_player)

        viewModel = ViewModelProvider(
            this,
            MyCustomViewModelFactory(this, this::class.java)
        )[PlayerActivityViewModel::class.java]

        startInputFragment()

        drawableArrowForward =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_forward, null)!!
        drawableArrowDown = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null)!!
        expandPlayerInfo = findViewById(R.id.expandPlayerInfo)

        expandUnlocked = findViewById(R.id.expandUnlockedBrawler)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        adapterInfo = BrawlPlayerAdapterInfo()
        adapterBrawlersUnlocked = BrawlPlayerAdapterBrawlersUnlocked()

        recyclerViewInfo = findViewById(R.id.playerRecyclerViewInfo)
        recyclerViewInfo.layoutManager = LinearLayoutManager(this)
        recyclerViewInfo.adapter = adapterInfo
        recyclerViewInfo.visibility = View.GONE

        recyclerViewBrawlers = findViewById(R.id.playerRecyclerViewBrawlers)
        recyclerViewBrawlers.layoutManager = GridLayoutManager(this, 3)
        recyclerViewBrawlers.adapter = adapterBrawlersUnlocked
        recyclerViewBrawlers.visibility = View.GONE

        viewModel.brawlPlayerInfo.observe(this, Observer { info ->
            adapterInfo.setInfo(info)
        })

        viewModel.playerBrawlersUnlocked.observe(this, Observer { data ->
            adapterBrawlersUnlocked.setBrawlersUnlocked(data)
        })

        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                viewModel.clearErrorMessage()
                inputFragment.showTagError()
            } else {
                val fragment = supportFragmentManager.beginTransaction()
                fragment.remove(inputFragment)
                fragment.commit()
                expandPlayerInfo.visibility = View.VISIBLE
                expandUnlocked.visibility = View.VISIBLE
            }
        })


        // Gestisco l'apertura della recycler dei brawler sbloccati dal player
        expandUnlocked.visibility = View.GONE
        expandUnlocked.setOnClickListener {
            if (recyclerViewBrawlers.visibility == View.GONE) {
                recyclerViewBrawlers.visibility = View.VISIBLE
                expandUnlocked.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawableArrowDown,
                    null,
                    null,
                    null
                )

            } else {
                recyclerViewBrawlers.visibility = View.GONE
                expandUnlocked.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawableArrowForward,
                    null,
                    null,
                    null
                )
            }
        }

        // Gestisco l'apertura della recycler delle info del player
        expandPlayerInfo.visibility = View.GONE
        expandPlayerInfo.setOnClickListener {
            if (recyclerViewInfo.visibility == View.GONE) {
                recyclerViewInfo.visibility = View.VISIBLE
                expandPlayerInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawableArrowDown,
                    null,
                    null,
                    null
                )


            } else {
                recyclerViewInfo.visibility = View.GONE
                expandPlayerInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawableArrowForward,
                    null,
                    null,
                    null
                )
            }
        }

        // Gestisco la bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Intent(this, BrawlHomeActivity::class.java).also {
                        startActivity(it)

                    }
                    true
                }
                R.id.menu_player -> {

                    true
                }
                R.id.menu_favourite -> {
                    Intent(this, BrawlFavouriteActivity::class.java)
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


        // Gestisco il submit del tag
        inputFragment.onTagSubmitted = { playerTag ->
            viewModel.getBrawlPlayerInfo(playerTag)
        }
    }

    private fun startInputFragment() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.brawlFragmentContainer, inputFragment)
        transaction.commit()

    }


}