package com.example.brawlmobile.activity.clashRoyale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.brawlStars.FavouriteAdapter
import com.example.brawlmobile.viewmodel.FavouriteActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MyCustomViewModelFactory

class ClashFavouriteActivity : AppCompatActivity(), FavouriteAdapter.OnClick {

    private lateinit var viewModel: FavouriteActivityViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavouriteAdapter
    private val TAG = "FavouriteActivity"
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

    }

    override fun deleteFromFavourites(name: String) {
        Log.d(TAG,"elimino dai preferiti")
        viewModel.deleteCard(name)
        Toast.makeText(this, "$name removed from favourites", Toast.LENGTH_SHORT)
            .show()
    }
}