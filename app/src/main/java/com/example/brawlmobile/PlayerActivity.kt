package com.example.brawlmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.adapter.PlayerAdapterBrawlersUnlocked
import com.example.brawlmobile.adapter.PlayerAdapterInfo
import com.example.brawlmobile.viewmodel.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.factory.PlayerActivityViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlayerActivity : AppCompatActivity() {
    private lateinit var viewModel: PlayerActivityViewModel

    private lateinit var recyclerViewInfo: RecyclerView
    private lateinit var recyclerViewBrawlers: RecyclerView

    private lateinit var adapterInfo: PlayerAdapterInfo
    private lateinit var adapterBrawlersUnlocked: PlayerAdapterBrawlersUnlocked

    private var TAG = "PlayerActivity"
    private lateinit var playerTag: String

    //    tag personale: #PRGG0V09G
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        viewModel = ViewModelProvider(
            this,
            PlayerActivityViewModelFactory(this)
        )[PlayerActivityViewModel::class.java]

        val arrowIconEdit: ImageView = findViewById(R.id.arrowIconEdit)
        val arrowIconUnlocked: ImageView = findViewById(R.id.arrowIconUnlocked)
        val expandible: LinearLayout = findViewById(R.id.expandibleLayout)
        val expandEdit: LinearLayout = findViewById(R.id.expandEdit)

        // Gestiamo la bottomNavigationView
        val bottomNavigationView: BottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)

                    }
                    true
                }
                R.id.menu_player -> {

                    true
                }
                R.id.menu_favourite -> {
                    Intent(this,FavouriteActivity::class.java)
                        .also {
                            startActivity(it)

                        }
                    true
                }
                else -> {
                    false
                }
            }
        }
        bottomNavigationView.selectedItemId = R.id.menu_player

        adapterInfo = PlayerAdapterInfo()
        adapterBrawlersUnlocked = PlayerAdapterBrawlersUnlocked()

        recyclerViewInfo = findViewById(R.id.playerRecyclerViewInfo)
        recyclerViewInfo.layoutManager = LinearLayoutManager(this)
        recyclerViewInfo.adapter = adapterInfo


        recyclerViewBrawlers = findViewById(R.id.playerRecyclerViewBrawlers)
        recyclerViewBrawlers.layoutManager = LinearLayoutManager(this)
        recyclerViewBrawlers.adapter = adapterBrawlersUnlocked
        recyclerViewBrawlers.visibility = View.GONE

        viewModel.playerInfo.observe(this, Observer { info ->
            adapterInfo.setInfo(info)
        })
        viewModel.playerBrawlersUnlocked.observe(this, Observer { data ->
            adapterBrawlersUnlocked.setBrawlersUnlocked(data)
        })
        viewModel.errorLiveData.observe(this, Observer { errorMessagge ->
            if (!errorMessagge.isNullOrEmpty()) {
                Toast.makeText(this, errorMessagge, Toast.LENGTH_LONG).show()
                viewModel.clearErrorMessage()
            }
        })

        // Gestiamo il submit del tag
        val editTextLayout: LinearLayout = findViewById(R.id.editLayout)
        val editText: EditText = findViewById(R.id.editText)
        val submitButton: Button = findViewById(R.id.submitTag)
        submitButton.setOnClickListener {
            playerTag = editText.text.toString()
            viewModel.getPlayerInfo(playerTag)
            expandible.visibility = View.VISIBLE
        }

        //Gestiamo l'header a scendere dei brawler sbloccati
        val headerText: TextView = findViewById(R.id.headerText)
        expandible.visibility = View.GONE
        expandible.setOnClickListener {
            if (recyclerViewBrawlers.visibility == View.GONE) {
                recyclerViewBrawlers.visibility = View.VISIBLE
                arrowIconUnlocked.setImageResource(R.drawable.ic_arrow_down)

            } else {
                recyclerViewBrawlers.visibility = View.GONE
                arrowIconUnlocked.setImageResource(R.drawable.ic_arrow_forward)
            }
        }

        //Gestiamo l'header a scendere dell'editText
        editTextLayout.visibility = View.GONE
        expandEdit.setOnClickListener {
            if (editTextLayout.visibility == View.GONE) {
                editTextLayout.visibility = View.VISIBLE
                arrowIconEdit.setImageResource(R.drawable.ic_arrow_down)


            } else {
                editTextLayout.visibility = View.GONE
                arrowIconEdit.setImageResource(R.drawable.ic_arrow_forward)
            }
        }
    }


}