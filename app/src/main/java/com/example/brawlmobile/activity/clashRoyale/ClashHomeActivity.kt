package com.example.brawlmobile.activity.clashRoyale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.clashRoyale.HomeAdapter
import com.example.brawlmobile.viewmodel.clashRoyale.HomeActivityViewModel
import com.example.brawlmobile.viewmodel.clashRoyale.factory.HomeActivityViewModelFactory

class ClashHomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: HomeActivityViewModel
    private lateinit var adapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_clash)

        viewModel = ViewModelProvider(this,
            HomeActivityViewModelFactory(this)
        )[HomeActivityViewModel::class.java]

        adapter = HomeAdapter(this)

        recyclerView = findViewById(R.id.playerClashRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this,3)
        recyclerView.adapter = adapter

        viewModel.cards.observe(this) { cards ->
            adapter.setCard(cards)
        }

        viewModel.getCards()

    }
}