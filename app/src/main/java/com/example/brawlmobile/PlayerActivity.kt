package com.example.brawlmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.BrawlerAdapter
import com.example.brawlmobile.adapter.PlayerAdapter
import com.example.brawlmobile.viewmodel.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.factory.PlayerActivityViewModelFactory

class PlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayerActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlayerAdapter
    private var TAG = "PlayerActivity"
    private var playerTag: String = ""

//    tag personale: #PRGG0V09G
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        viewModel = ViewModelProvider(
            this,
            PlayerActivityViewModelFactory(this)
        )[PlayerActivityViewModel::class.java]

        adapter = PlayerAdapter()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.playerInfo.observe(this, Observer { info ->
            adapter.setInfo(info)
        })

        val editText: EditText = findViewById(R.id.editText)
        val submitButton: Button = findViewById(R.id.submitTag)
        submitButton.setOnClickListener {
            playerTag = editText.text.toString()
            viewModel.getPlayerInfo(playerTag)
        }



    }




}