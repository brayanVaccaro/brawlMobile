package com.example.brawlmobile.activity.brawlStars

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.StartActivity
import com.example.brawlmobile.adapter.ClickListener
import com.example.brawlmobile.adapter.brawlStars.HomeAdapter
import com.example.brawlmobile.data.entities.FavouriteBrawlerEntity
import com.example.brawlmobile.fragment.ErrorFragment
import com.example.brawlmobile.model.brawlStar.brawler.BrawlerModel
import com.example.brawlmobile.viewmodel.FavouriteActivityViewModel
import com.example.brawlmobile.viewmodel.HomeActivityViewModel

import com.example.brawlmobile.viewmodel.factory.MyCustomViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class BrawlHomeActivity : AppCompatActivity(), ClickListener {
    private lateinit var viewModel: HomeActivityViewModel

    //    private lateinit var viewModelFactory: MyCustomViewModelFactory
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeAdapter

    private lateinit var favouriteViewModel: FavouriteActivityViewModel


    private var TAG = "MainActivity"

    //    private lateinit var txtErrorInfo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brawl_home)


        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById(R.id.bottomNavigationView)


        viewModel = ViewModelProvider(
            this,
            MyCustomViewModelFactory(this, this::class.java)
        )[HomeActivityViewModel::class.java]

        favouriteViewModel = ViewModelProvider(
            this,
            MyCustomViewModelFactory(this, this::class.java)
        )[FavouriteActivityViewModel::class.java]

        adapter = HomeAdapter(this, this)

        recyclerView = findViewById(R.id.mainRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        viewModel.brawlers.observe(this, Observer { brawlers ->
            adapter.setBrawlers(brawlers)
        })
        viewModel.errorLiveData.observe(this, Observer { errorMessagge ->
            if (!errorMessagge.isNullOrEmpty()) {
                Log.e(TAG, "AVVIO ERROR FRAGMENT")
                Toast.makeText(this, errorMessagge, Toast.LENGTH_LONG).show()
                viewModel.clearErrorMessage()
                startErrorFragment(errorMessagge)
                bottomNavigationView.visibility = View.GONE
            }
        })


        viewModel.getBrawlers()



        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {

                    true
                }
                R.id.menu_player -> {
                    Intent(this, BrawlPlayerActivity::class.java).also {
                        startActivity(it)

                    }

                    true
                }
                R.id.menu_favourite -> {
                    Log.d(TAG, "ho cliccato favourites")
                    Intent(this, BrawlFavouriteActivity::class.java)
                        .also {
                            Log.d(TAG, "faccio partire la activity")
                            startActivity(it)

                        }
                    true
                }
                else -> {
                    Log.d(TAG, "ho cliccato EXIT")
                    Intent(this, StartActivity::class.java)
                        .also {
                            Log.d(TAG, "faccio partire la activity")
                            startActivity(it)


                        }
                    true
                }
            }
        }
        bottomNavigationView.selectedItemId = R.id.menu_home
    }

    private fun startErrorFragment(errorMessage: String) {
        val errorFragment = ErrorFragment.newInstance(errorMessage)


        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.brawlFragmentContainer, errorFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onClickViewInfo(model: Any) {

        val brawlerModel = model as BrawlerModel

        if (brawlerModel.name == "El-Primo") {
            brawlerModel.name = "El_Primo"
        }

        // Creo il bundle con i relativi dati da passare alla DetailsActivity
        val bundle = Bundle()
        bundle.putString("EXTRA_NAME", brawlerModel.name)
        bundle.putString("EXTRA_GADGET_1_NAME", brawlerModel.gadgets[0].name)
        bundle.putString("EXTRA_GADGET_2_NAME", brawlerModel.gadgets[1].name)
        bundle.putString("EXTRA_STARPOWER_1_NAME", brawlerModel.starPowers[0].name)
        bundle.putString("EXTRA_STARPOWER_2_NAME", brawlerModel.starPowers[1].name)

//        Log.d(TAG, "bundle vale = $bundle")
        // Creo l'intento in cui passo il bundle e faccio partire la DetailsActivity
        Intent(this, BrawlDetailsActivity::class.java)
            .also {
                it.putExtras(bundle)

                startActivity(it)
            }

        // Creo un Toast in cui visualizzare il nome del Brawler
        Toast.makeText(this, "Brawler: ${brawlerModel.name}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClickAddToFavourite(model: Any) {
        val brawlerModel = model as BrawlerModel

        Log.d(TAG, "aggiungo ai favoriti")
        val favouriteBrawler = FavouriteBrawlerEntity(
            id = brawlerModel.id.toString(),
            name = brawlerModel.name,
            spriteUrl = brawlerModel.spriteUrl
        )

        favouriteViewModel.insertFavouriteBrawler(favouriteBrawler)
        Toast.makeText(this, "${favouriteBrawler.name} added to favourites", Toast.LENGTH_SHORT)
            .show()

    }
}