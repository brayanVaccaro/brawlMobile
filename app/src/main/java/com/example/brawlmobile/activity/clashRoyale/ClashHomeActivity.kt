package com.example.brawlmobile.activity.clashRoyale

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.StartActivity
import com.example.brawlmobile.adapter.listener.ClickListener
import com.example.brawlmobile.adapter.clashRoyale.ClashHomeAdapter
import com.example.brawlmobile.data.entities.CardEntity
import com.example.brawlmobile.fragment.DetailsDialogFragment
import com.example.brawlmobile.fragment.ErrorFragment
import com.example.brawlmobile.model.clashRoyale.CardModel
import com.example.brawlmobile.viewmodel.FavouriteActivityViewModel
import com.example.brawlmobile.viewmodel.HomeActivityViewModel
import com.example.brawlmobile.viewmodel.factory.MyCustomViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ClashHomeActivity : AppCompatActivity(), ClickListener {

    private lateinit var viewModel: HomeActivityViewModel
    private lateinit var favouriteViewModel: FavouriteActivityViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ClashHomeAdapter

    private lateinit var bottomNavigationView: BottomNavigationView
    private var TAG = "ClashHomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clash_home)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        viewModel = ViewModelProvider(
            this,
            MyCustomViewModelFactory(this, this::class.java)
        )[HomeActivityViewModel::class.java]

        favouriteViewModel = ViewModelProvider(
            this,
            MyCustomViewModelFactory(this, this::class.java)
        )[FavouriteActivityViewModel::class.java]

        adapter = ClashHomeAdapter(this, this)

        recyclerView = findViewById(R.id.playerClashRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        viewModel.cards.observe(this) { cards ->
            adapter.setCard(cards)
        }
        viewModel.errorLiveData.observe(this, Observer { errorMessagge ->
            if (!errorMessagge.isNullOrEmpty()) {
                Toast.makeText(this, errorMessagge, Toast.LENGTH_LONG).show()
                viewModel.clearErrorMessage()
                startErrorFragment(errorMessagge)
                bottomNavigationView.visibility = View.GONE

            }
        })

        viewModel.getCards()

        // Gestiamo la bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {

                    true
                }
                R.id.menu_player -> {
                    Intent(this, ClashPlayerActivity::class.java).also {
                        startActivity(it)

                    }

                    true
                }
                R.id.menu_favourite -> {
                    Intent(this, ClashFavouriteActivity::class.java)
                        .also {
                            startActivity(it)

                        }
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
        bottomNavigationView.selectedItemId = R.id.menu_home

    }

    private fun startErrorFragment(errorMessage: String) {
        val errorFragment = ErrorFragment.newInstance(errorMessage)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.clashFragmentContainer, errorFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onClickViewInfo(model: Any) {
        val cardModel = model as CardModel
        // Creo il bundle con i relativi dati da passare alla DetailsActivity
        val bundle = Bundle()

        bundle.putString("EXTRA_NAME", cardModel.name)
        bundle.putString("EXTRA_MAX_LEVEL", cardModel.maxLevel.toString())
        if (cardModel.maxEvolutionLevel != null) {
            bundle.putString("EXTRA_MAX_EVOLUTION_LEVEL", cardModel.maxEvolutionLevel.toString())
        }
        bundle.putString("EXTRA_ICON_NORMAL", cardModel.urlNormal)
        bundle.putString("EXTRA_ICON_EVOLUTION", cardModel.urlEvolution)

        val dialog: DialogFragment = DetailsDialogFragment()
        dialog.arguments = bundle

        dialog.show(supportFragmentManager, "DetailsDialogFragment")


        // Creo un Toast in cui visualizzare il nome del Brawler
        Toast.makeText(this, "Brawler: ${cardModel.name}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClickAddToFavourite(model: Any) {
        val cardModel = model as CardModel

        val favouriteCard = CardEntity(
            id = cardModel.id.toString(),
            name = cardModel.name,
            spriteUrl = cardModel.urlNormal
        )
        favouriteViewModel.insertFavouriteCard(favouriteCard)
        Toast.makeText(this, "${favouriteCard.name} added to favourites", Toast.LENGTH_SHORT)
            .show()
    }


}