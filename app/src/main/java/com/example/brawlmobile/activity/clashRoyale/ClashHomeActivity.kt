package com.example.brawlmobile.activity.clashRoyale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.activity.brawlStars.DetailsActivity
import com.example.brawlmobile.adapter.OnClickListener
import com.example.brawlmobile.adapter.clashRoyale.HomeAdapter
import com.example.brawlmobile.fragment.DetailsDialogFragment
import com.example.brawlmobile.model.clashRoyale.CardModel
import com.example.brawlmobile.viewmodel.clashRoyale.HomeActivityViewModel
import com.example.brawlmobile.viewmodel.clashRoyale.factory.HomeActivityViewModelFactory

class ClashHomeActivity : AppCompatActivity(), OnClickListener {

    private var TAG = "ClashHomeActivity"

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: HomeActivityViewModel
    private lateinit var adapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_clash)

        viewModel = ViewModelProvider(
            this,
            HomeActivityViewModelFactory(this)
        )[HomeActivityViewModel::class.java]

        adapter = HomeAdapter(this, this)

        recyclerView = findViewById(R.id.playerClashRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        viewModel.cards.observe(this) { cards ->
            adapter.setCard(cards)
        }

        viewModel.getCards()

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

        Log.d(TAG, "il bundle vale $bundle")
        val dialog: DialogFragment = DetailsDialogFragment()
        dialog.arguments = bundle

        dialog.show(supportFragmentManager, "DetailsDialogFragment")


        // Creo un Toast in cui visualizzare il nome del Brawler
        Toast.makeText(this, "Brawler: ${cardModel.name}", Toast.LENGTH_SHORT)
            .show()
    }
}