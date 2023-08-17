package com.example.brawlmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.PlayerAdapterBrawlersUnlocked
import com.example.brawlmobile.adapter.PlayerAdapterInfo
import com.example.brawlmobile.viewmodel.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.factory.PlayerActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayerActivityViewModel

    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var recyclerViewBrawlers: RecyclerView

    private lateinit var adapterInfo: PlayerAdapterInfo
    private lateinit var adapterBrawlersUnlocked: PlayerAdapterBrawlersUnlocked

    private var TAG = "PlayerActivity"
    private lateinit var playerTag: String

    //    tag personale: #PRGG0V09G
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        viewModel = ViewModelProvider(
            this,
            PlayerActivityViewModelFactory(this)
        )[PlayerActivityViewModel::class.java]

        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                    true
                }
                R.id.menu_player -> {

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


        recyclerViewBrawlers = findViewById(R.id.playerRecyclerViewBrawlers)
        recyclerViewBrawlers.layoutManager = LinearLayoutManager(this)
        recyclerViewBrawlers.adapter = adapterBrawlersUnlocked
        recyclerViewBrawlers.visibility = View.GONE

        viewModel.playerInfo.observe(this, Observer { info ->
            adapterInfo.setInfo(info)
        })
        viewModel.playerBrawlersUnlocked.observe(this, Observer { data ->
            adapterBrawlersUnlocked.setBrawlersUnlocked(data)
        })


        val editText: EditText = findViewById(R.id.editText)
        val submitButton: Button = findViewById(R.id.submitTag)
        submitButton.setOnClickListener {
            playerTag = editText.text.toString()
            viewModel.getPlayerInfo(playerTag)
        }

        //Gestiamo l'header a scendere
        val headerIcon: ImageView = findViewById(R.id.headerIcon)
        val expandible: LinearLayout = findViewById(R.id.expandibleLayout)
        val headerText: TextView = findViewById(R.id.headerText)
        expandible.setOnClickListener {
            if (recyclerViewBrawlers.visibility == View.GONE) {
                recyclerViewBrawlers.visibility = View.VISIBLE
                headerIcon.setImageResource(R.drawable.ic_arrow_down)

            } else {
                recyclerViewBrawlers.visibility = View.GONE
                headerIcon.setImageResource(R.drawable.ic_arrow_forward)
            }
        }


    }


}