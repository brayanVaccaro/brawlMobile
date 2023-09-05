package com.example.brawlmobile.adapter.brawlStars

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.OnClickListener
import com.example.brawlmobile.adapter.listener.GlideRequestListener
import com.example.brawlmobile.model.brawlStar.brawler.BrawlerModel

class HomeAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener
): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var brawlers: MutableList<BrawlerModel> = mutableListOf()
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
        brawlers.clear()
        brawlers.addAll(data)
        notifyDataSetChanged()
    }
    /*interface OnClickListener {
        fun onClickViewInfo(brawlerModel: BrawlerModel){}
        fun onClickAddToFavourite(brawlerModel: BrawlerModel){}
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_brawl, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return brawlers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brawlerModel = brawlers[position]
        holder.progressBar.visibility = View.VISIBLE
        Glide.with(context)
            .load(brawlerModel.spriteUrl)
            .listener(holder.glideRequestListener)
            .into(holder.imageView)
        holder.txtBrawlerName.text = brawlerModel.name
        holder.imageView.setOnClickListener {
            onClickListener.onClickViewInfo(brawlerModel)
        }
        holder.clickableSpriteHeart.setOnClickListener {
            onClickListener.onClickAddToFavourite(brawlerModel)
        }
    }

    fun setupGridLayoutManager(recyclerView: RecyclerView) {
        val gridLayoutManager = GridLayoutManager(context,3)
        recyclerView.layoutManager = gridLayoutManager
    }


}