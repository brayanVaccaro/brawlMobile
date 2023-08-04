package com.example.brawlmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.BrawlerAdapter
import com.example.brawlmobile.viewmodel.MainActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: BrawlerAdapter

    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(applicationContext)
        )[MainActivityViewModel::class.java]

        adapter = BrawlerAdapter(this)

        recyclerView = findViewById(R.id.mainRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        viewModel.brawlers.observe(this, Observer {brawlers ->
            adapter.setBrawlers(brawlers)
        })

        viewModel.getBrawlers()

    }
}