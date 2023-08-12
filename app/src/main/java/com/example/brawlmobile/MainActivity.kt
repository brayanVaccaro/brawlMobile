package com.example.brawlmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.BrawlerAdapter
import com.example.brawlmobile.models.brawler.BrawlerModel
import com.example.brawlmobile.viewmodel.MainActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MainActivityViewModelFactory

class MainActivity : AppCompatActivity(), BrawlerAdapter.OnLayoutClickListener {
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

        adapter = BrawlerAdapter(this, this)

        recyclerView = findViewById(R.id.mainRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        viewModel.brawlers.observe(this, Observer {brawlers ->
            adapter.setBrawlers(brawlers)
        })

        viewModel.getBrawlers()

    }

    override fun onLayoutClick(brawlerModel: BrawlerModel) {

        if(brawlerModel.name == "El-Primo") {
            brawlerModel.name = "El_Primo"
        }

        // Creo il bundle con i relativi dati da passare alla DetailsActivity
        val bundle = Bundle()
        bundle.putString("EXTRA_NAME", brawlerModel.name)
        bundle.putString("EXTRA_GADGET_1_NAME", brawlerModel.gadgets[0].name)
        bundle.putString("EXTRA_GADGET_2_NAME", brawlerModel.gadgets[1].name)
        bundle.putString("EXTRA_STARPOWER_1_NAME", brawlerModel.starPowers[0].name)
        bundle.putString("EXTRA_STARPOWER_2_NAME", brawlerModel.starPowers[1].name)

        Log.d(TAG,"bundle vale = $bundle")
        // Creo l'intento in cui passo il bundle e faccio partire la DetailsActivity
        Intent(this, DetailsActivity::class.java)
            .also {
                it.putExtras(bundle)

                startActivity(it)
            }

        // Creo un Toast in cui visualizzare il nome del Brawler
        Toast.makeText(this,"Brawler: ${brawlerModel.name}", Toast.LENGTH_SHORT)
            .show()
    }
}