package com.example.brawlmobile.adapter.listener

import android.graphics.drawable.Drawable
import android.util.Log
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
        Log.d("RequestListener","on LoadFailed")
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
        Log.d("RequestListener","onResourceReady")
        progressBar.visibility = View.GONE
        return false
    }
}