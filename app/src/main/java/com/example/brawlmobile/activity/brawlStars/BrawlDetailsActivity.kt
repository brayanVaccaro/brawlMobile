package com.example.brawlmobile.activity.brawlStars

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.brawlStars.DetailsAdapter
import com.example.brawlmobile.fragment.ErrorFragment
import com.example.brawlmobile.model.brawlStar.brawler.HeaderModel
import com.example.brawlmobile.viewmodel.brawlStars.DetailsActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MyCustomViewModelFactory


class BrawlDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsActivityViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailsAdapter

    private val TAG = "DetailsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brawl_details)

        // Gestisco il bundle
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

        viewModel = ViewModelProvider(
            this,
            MyCustomViewModelFactory(this, this::class.java)
        )[DetailsActivityViewModel::class.java]

        adapter = DetailsAdapter(this)

        recyclerView = findViewById(R.id.detailsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.webText.observe(this, Observer { text ->
            Log.d(TAG, "sto invocando setData")
            adapter.setData(text, headers)
        })

        viewModel.webUrls.observe(this, Observer { urls ->
            adapter.setImages(urls)
        })

        viewModel.errorLiveData.observe(this, Observer { errorMessagge ->
            if (!errorMessagge.isNullOrEmpty()) {
                Log.e(TAG, "AVVIO ERROR FRAGMENT")
                Toast.makeText(this, errorMessagge, Toast.LENGTH_LONG).show()
                viewModel.clearErrorMessage()
                startErrorFragment(errorMessagge)
            }
        })

        if (name != null) {
            viewModel.getWebText(name)
            viewModel.getWebUrls(name)
        }
    }

    private fun startErrorFragment(errorMessage: String) {
        val errorFragment = ErrorFragment.newInstance(errorMessage)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.brawlErrorFragmentContainer, errorFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}