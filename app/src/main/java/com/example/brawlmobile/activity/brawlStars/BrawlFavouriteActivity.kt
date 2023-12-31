package com.example.brawlmobile.activity.brawlStars

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

class BrawlFavouriteActivity : AppCompatActivity(), ClickListener {

    private lateinit var viewModel: FavouriteActivityViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavouriteAdapter

    private val TAG = "FavouriteActivity"
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brawl_favourite)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        viewModel = ViewModelProvider(
            this,
            MyCustomViewModelFactory(this, this::class.java)
        )[FavouriteActivityViewModel::class.java]

        adapter = FavouriteAdapter(this, this)

        recyclerView = findViewById(R.id.favouriteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allFavouriteBrawlers.observe(this, Observer { fav ->
            adapter.setFavouriteItem(fav)
        })

        // Gestisco la bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Intent(this, BrawlHomeActivity::class.java)
                        .also {
                            startActivity(it)

                        }

                    true
                }
                R.id.menu_player -> {
                    Intent(this, BrawlPlayerActivity::class.java).also {
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

    //Gestisco la eliminazione dai preferiti
    override fun deleteFromFavourites(name: String) {
        viewModel.deleteBrawler(name)
        Toast.makeText(this, "$name removed from favourites", Toast.LENGTH_SHORT)
            .show()
    }

}