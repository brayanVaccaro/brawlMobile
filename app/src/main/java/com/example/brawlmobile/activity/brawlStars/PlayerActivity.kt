package com.example.brawlmobile.activity.brawlStars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.brawlStars.PlayerAdapterBrawlersUnlocked
import com.example.brawlmobile.adapter.brawlStars.PlayerAdapterInfo
import com.example.brawlmobile.fragment.InputFragment
import com.example.brawlmobile.viewmodel.brawlStars.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.brawlStars.factory.PlayerActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayerActivityViewModel

    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var recyclerViewBrawlers: RecyclerView

    private lateinit var adapterInfo: PlayerAdapterInfo
    private lateinit var adapterBrawlersUnlocked: PlayerAdapterBrawlersUnlocked

    private var TAG = "PlayerActivity"
    private lateinit var expandPlayerInfo: LinearLayout
    private lateinit var expandUnlocked: LinearLayout
    private val inputFragment = InputFragment()
//    private lateinit var playerTag: String

    //    tag personale: #PRGG0V09G
    // secondo tag personale: #29G8QYCYG
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        viewModel = ViewModelProvider(
            this,
            PlayerActivityViewModelFactory(this)
        )[PlayerActivityViewModel::class.java]

        startInputFragment()

        val arrowIconPlayerInfo: ImageView = findViewById(R.id.arrowIconPlayerInfo)
        expandPlayerInfo = findViewById(R.id.expandPlayerInfo)

        val arrowIconUnlocked: ImageView = findViewById(R.id.arrowIconUnlocked)
        expandUnlocked = findViewById(R.id.expandUnlocked)

        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
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

        adapterInfo = PlayerAdapterInfo()
        adapterBrawlersUnlocked = PlayerAdapterBrawlersUnlocked()

        recyclerViewInfo = findViewById(R.id.playerRecyclerViewInfo)
        recyclerViewInfo.layoutManager = LinearLayoutManager(this)
        recyclerViewInfo.adapter = adapterInfo
        recyclerViewInfo.visibility = View.GONE


        recyclerViewBrawlers = findViewById(R.id.playerRecyclerViewBrawlers)
        recyclerViewBrawlers.layoutManager = GridLayoutManager(this, 3)
        recyclerViewBrawlers.adapter = adapterBrawlersUnlocked
        recyclerViewBrawlers.visibility = View.GONE

        viewModel.playerInfo.observe(this, Observer { info ->
            adapterInfo.setInfo(info)
        })
        viewModel.playerBrawlersUnlocked.observe(this, Observer { data ->
            adapterBrawlersUnlocked.setBrawlersUnlocked(data)
        })
        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            if (errorMessage.isNotEmpty()) {
//                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
//                viewModel.clearErrorMessage()
                Log.d(TAG,"sono nell'else, c'è errore")
                inputFragment.showTagError()
//                val inputFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? InputFragment
//                inputFragment?.hideTagError()
            }
            else {
                Log.d(TAG,"sono nell'else, non c'è errore")
                val fragment = supportFragmentManager.beginTransaction()
                fragment.remove(inputFragment)
                fragment.commit()
                expandPlayerInfo.visibility = View.VISIBLE
                expandUnlocked.visibility = View.VISIBLE
            }
        })


        //Gestiamo l'apertura della recycler dei brawler sbloccati dal player
        expandUnlocked.visibility = View.GONE
        expandUnlocked.setOnClickListener {
            if (recyclerViewBrawlers.visibility == View.GONE) {
                recyclerViewBrawlers.visibility = View.VISIBLE
                arrowIconUnlocked.setImageResource(R.drawable.ic_arrow_down)

            } else {
                recyclerViewBrawlers.visibility = View.GONE
                arrowIconUnlocked.setImageResource(R.drawable.ic_arrow_forward)
            }
        }

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


        //
        inputFragment.onTagSubmitted = { playerTag ->
            viewModel.getPlayerInfo(playerTag)

        }
    }

    private fun startInputFragment() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.brawlFragmentContainer, inputFragment)
        transaction.commit()


    }


}