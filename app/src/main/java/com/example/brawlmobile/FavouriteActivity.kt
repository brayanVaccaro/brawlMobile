package com.example.brawlmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.FavouriteAdapter
import com.example.brawlmobile.viewmodel.FavouriteActivityViewModel
import com.example.brawlmobile.viewmodel.factory.FavouriteActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavouriteActivity : AppCompatActivity(), FavouriteAdapter.OnClick {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: FavouriteActivityViewModel
    private lateinit var adapter: FavouriteAdapter
    private val TAG = "FavouriteActivity"

//    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        Log.d(TAG, "onCreate creo l'activity")

        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Intent(this, HomeActivity::class.java)
                        .also {
                            startActivity(it)
                            finish()
                        }

                    true
                }
                R.id.menu_player -> {
                    Intent(this, PlayerActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }

                    true
                }
                R.id.menu_favourite -> {
                    true

                }
                else -> {
                    false
                }
            }
        }
        bottomNavigationView.selectedItemId = R.id.menu_favourite

        viewModel = ViewModelProvider(this, FavouriteActivityViewModelFactory(applicationContext))[FavouriteActivityViewModel::class.java]

        adapter = FavouriteAdapter(this, this)
        recyclerView = findViewById<RecyclerView?>(R.id.favouriteRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allFavouriteBrawlers.observe(this, Observer {fav ->
            adapter.setData(fav)
        })



    }
    //Gestisco la eliminazione dai preferiti
    override fun deleteFromFavourites(name: String) {
        Log.d(TAG,"elimino dai preferiti")
        viewModel.deleteBrawler(name)
        Toast.makeText(this, "$name removed from favourites", Toast.LENGTH_SHORT)
            .show()
    }

}