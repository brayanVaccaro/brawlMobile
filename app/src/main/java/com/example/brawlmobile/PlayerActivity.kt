package com.example.brawlmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.PlayerAdapterInfo
import com.example.brawlmobile.viewmodel.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.factory.PlayerActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayerActivityViewModel
    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var adapterInfo: PlayerAdapterInfo
    private var TAG = "PlayerActivity"
    private lateinit var playerTag: String

    //    tag personale: #PRGG0V09G
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
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




        viewModel = ViewModelProvider(
            this,
            PlayerActivityViewModelFactory(this)
        )[PlayerActivityViewModel::class.java]

        adapterInfo = PlayerAdapterInfo()

        recyclerViewInfo = findViewById(R.id.playerRecyclerViewInfo)
        recyclerViewInfo.layoutManager = LinearLayoutManager(this)
        recyclerViewInfo.adapter = adapterInfo

        viewModel.playerInfo.observe(this, Observer { info ->
            adapterInfo.setInfo(info)
        })

        val editText: EditText = findViewById(R.id.editText)
        val submitButton: Button = findViewById(R.id.submitTag)
        submitButton.setOnClickListener {
            playerTag = editText.text.toString()
            viewModel.getPlayerInfo(playerTag)
        }


    }


}