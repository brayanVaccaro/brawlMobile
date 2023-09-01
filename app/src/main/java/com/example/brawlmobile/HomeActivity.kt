package com.example.brawlmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.HomeAdapter
import com.example.brawlmobile.data.entities.FavouriteBrawlerEntity
import com.example.brawlmobile.fragment.ErrorFragment
import com.example.brawlmobile.models.brawler.BrawlerModel
import com.example.brawlmobile.viewmodel.FavouriteActivityViewModel
import com.example.brawlmobile.viewmodel.HomeActivityViewModel
import com.example.brawlmobile.viewmodel.factory.FavouriteActivityViewModelFactory
import com.example.brawlmobile.viewmodel.factory.HomeActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), HomeAdapter.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: HomeActivityViewModel
    private lateinit var adapter: HomeAdapter

    private lateinit var favouriteViewModel: FavouriteActivityViewModel


    private var TAG = "MainActivity"

    //    private lateinit var txtErrorInfo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_v2)


        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {

                    true
                }
                R.id.menu_player -> {
                    Intent(this, PlayerActivity::class.java).also {
                        startActivity(it)

                    }

                    true
                }
                R.id.menu_favourite -> {
                    Log.d(TAG, "ho cliccato favourites")
                    Intent(this, FavouriteActivity::class.java)
                        .also {
                            Log.d(TAG, "faccio partire la activity")
                            startActivity(it)

                        }
                    true
                }
                else -> {
                    false
                }
            }
        }
        bottomNavigationView.selectedItemId = R.id.menu_home

        viewModel = ViewModelProvider(
            this,
            HomeActivityViewModelFactory(applicationContext)
        )[HomeActivityViewModel::class.java]
        favouriteViewModel = ViewModelProvider(
            this,
            FavouriteActivityViewModelFactory(applicationContext)
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
                Toast.makeText(this, errorMessagge, Toast.LENGTH_LONG).show()
                viewModel.clearErrorMessage()
                startErrorFragment(errorMessagge)
                bottomNavigationView.visibility = View.GONE
            }
        })

        viewModel.getBrawlers()
    }

    private fun startErrorFragment(errorMessage: String) {
        val errorFragment = ErrorFragment.newInstance(errorMessage)
//        val txtErrorInfo = errorFragment.view?.findViewById<TextView>(R.id.txtErrorInfo)
//        txtErrorInfo?.text = errorMessage

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, errorFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onClickViewInfo(brawlerModel: BrawlerModel) {

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
        Intent(this, DetailsActivity::class.java)
            .also {
                it.putExtras(bundle)

                startActivity(it)
            }

        // Creo un Toast in cui visualizzare il nome del Brawler
        Toast.makeText(this, "Brawler: ${brawlerModel.name}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClickAddToFavourite(brawlerModel: BrawlerModel) {

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