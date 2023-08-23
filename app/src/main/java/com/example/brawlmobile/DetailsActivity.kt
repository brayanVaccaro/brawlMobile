package com.example.brawlmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.BrawlerAdapter
import com.example.brawlmobile.adapter.TextAdapter
import com.example.brawlmobile.models.brawler.HeaderModel
import com.example.brawlmobile.viewmodel.MainActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MainActivityViewModelFactory

class DetailsActivity : AppCompatActivity() {

    private val TAG = "DetailsActivity"
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TextAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val bundle = intent.extras

        val name = bundle?.getString("EXTRA_NAME")
        val firstGadget = bundle?.getString("EXTRA_GADGET_1_NAME")
        val secondGadget = bundle?.getString("EXTRA_GADGET_2_NAME")
        val firstStarPower = bundle?.getString("EXTRA_STARPOWER_1_NAME")
        val secondStarPower = bundle?.getString("EXTRA_STARPOWER_2_NAME")

        val headers = HeaderModel(
            name = name,
            firstGadget = firstGadget,
            secondGadget = secondGadget,
            firstStarPower = firstStarPower,
            secondStarPower = secondStarPower
        )

//        Log.d(TAG, "headers vale = $headers")

        viewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(applicationContext)
        )[MainActivityViewModel::class.java]

        adapter = TextAdapter(this)

        recyclerView = findViewById(R.id.detailsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.webText.observe(this, Observer { text ->
            Log.d(TAG,"sto invocando setData")
            adapter.setData(text, headers)
        })
        viewModel.webUrls.observe(this, Observer { urls ->
            Log.d(TAG,"sto invocando setImages, url vale $urls")
            adapter.setImages(urls)
        })

        if (name != null) {
            viewModel.getWebText(name)
            viewModel.getWebUrls(name)
        }

    }
}