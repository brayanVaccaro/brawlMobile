package com.example.brawlmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.brawlmobile.activity.brawlStars.BrawlHomeActivity
import com.example.brawlmobile.activity.clashRoyale.ClashHomeActivity

class StartActivity : AppCompatActivity() {

    private lateinit var brawlStars: Button
    private lateinit var clashRoyale: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        brawlStars = findViewById(R.id.brawlStars)
        brawlStars.setOnClickListener {
            Intent(this, BrawlHomeActivity::class.java).also {
                startActivity(it)
            }
        }

        clashRoyale = findViewById(R.id.clashRoyale)
        clashRoyale.setOnClickListener {
            Intent(this, ClashHomeActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}