package com.example.brawlmobile.adapter.brawlStars

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.listener.ClickListener
import com.example.brawlmobile.adapter.listener.GlideRequestListener
import com.example.brawlmobile.model.brawlStar.brawler.BrawlerModel

class BrawlHomeAdapter(
    private var brawlerList: List<BrawlerModel>,
    private val context: Context,
    private val clickListener: ClickListener
): RecyclerView.Adapter<BrawlHomeAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtBrawlerName: TextView
        val imageView: ImageView
        val clickableSpriteHeart: ImageView
        val progressBar: ProgressBar
        val glideRequestListener: RequestListener<Drawable>

        init {
            txtBrawlerName = view.findViewById(R.id.txtBrawlerName)
            imageView = view.findViewById(R.id.clickableBrawlerSprite)
            clickableSpriteHeart = view.findViewById(R.id.iconFavourite)
            progressBar = view.findViewById(R.id.homeProgressBar)
            glideRequestListener = GlideRequestListener(progressBar)
        }
    }

    fun setBrawlers(data: List<BrawlerModel>) {
        brawlerList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_brawl, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return brawlerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brawlerModel = brawlerList[position]
        holder.progressBar.visibility = View.VISIBLE
        Glide.with(context)
            .load(brawlerModel.spriteUrl)
            .listener(holder.glideRequestListener)
            .error(R.drawable.ic_delete)
            .into(holder.imageView)
        holder.txtBrawlerName.text = brawlerModel.transformedName
        holder.imageView.setOnClickListener {
            clickListener.onClickViewInfo(brawlerModel)
        }
        holder.clickableSpriteHeart.setOnClickListener {
            clickListener.onClickAddToFavourite(brawlerModel)
        }
    }
}