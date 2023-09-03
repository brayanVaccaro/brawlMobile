package com.example.brawlmobile.adapter.brawlStars.listener

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class GlideRequestListener(private val progressBar: ProgressBar) :
    RequestListener<Drawable> {

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        // Nascondi il ProgressBar in caso di errore
        progressBar.visibility = View.GONE
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: com.bumptech.glide.request.target.Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        // Nascondi il ProgressBar quando l'immagine è stata caricata con successo
        progressBar.visibility = View.GONE
        return false
    }
}