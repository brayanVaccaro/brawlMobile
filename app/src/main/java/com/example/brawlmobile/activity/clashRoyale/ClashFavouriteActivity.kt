package com.example.brawlmobile.activity.clashRoyale

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.StartActivity
import com.example.brawlmobile.adapter.listener.ClickListener
import com.example.brawlmobile.adapter.FavouriteAdapter
import com.example.brawlmobile.viewmodel.FavouriteActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MyCustomViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ClashFavouriteActivity : AppCompatActivity(), ClickListener {

    private lateinit var viewModel: FavouriteActivityViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavouriteAdapter
    private val TAG = "FavouriteActivity"
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clash_favourite)

        viewModel = ViewModelProvider(this, MyCustomViewModelFactory(this, this::class.java))[FavouriteActivityViewModel::class.java]

        adapter = FavouriteAdapter(this,this)

        recyclerView = findViewById(R.id.clashFavouriteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allFavouriteCards.observe(this, Observer {fav ->
            adapter.setFavouriteItem(fav)
        })


        // Gestiamo la bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Intent(this, ClashHomeActivity::class.java).also {
                        startActivity(it)

                    }
                    true
                }
                R.id.menu_player -> {
                    Intent(this, ClashPlayerActivity::class.java)
                        .also {
                            startActivity(it)
                        }
                    true
                }
                R.id.menu_favourite -> {

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
        bottomNavigationView.selectedItemId = R.id.menu_favourite

    }

    override fun deleteFromFavourites(name: String) {
        viewModel.deleteCard(name)
        Toast.makeText(this, "$name removed from favourites", Toast.LENGTH_SHORT)
            .show()
    }
}