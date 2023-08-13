package com.example.brawlmobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brawlmobile.R
import com.example.brawlmobile.models.brawler.BrawlerModel

class BrawlerAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener
): RecyclerView.Adapter<BrawlerAdapter.ViewHolder>() {

    private var brawlers: MutableList<BrawlerModel> = mutableListOf()
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtBrawlerName: TextView
        val imageView: ImageView
        val clickableLayout: LinearLayout

        init {
            txtBrawlerName = view.findViewById(R.id.txtBrawlerName)
            imageView = view.findViewById(R.id.brawlerSprite)
            clickableLayout = view.findViewById(R.id.clickableLayout)
        }
    }

    fun setBrawlers(data: List<BrawlerModel>) {
        brawlers.clear()
        brawlers.addAll(data)
        notifyDataSetChanged()
    }
    interface OnClickListener {
        fun onLayoutClick(brawlerModel: BrawlerModel){}
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_brawler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return brawlers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brawlerModel = brawlers[position]
        Glide.with(context).load(brawlerModel.spriteUrl).into(holder.imageView)
        holder.txtBrawlerName.text = brawlerModel.name
        holder.clickableLayout.setOnClickListener {
            onClickListener.onLayoutClick(brawlerModel)
        }
    }

    fun setupGridLayoutManager(recyclerView: RecyclerView) {
        val gridLayoutManager = GridLayoutManager(context,3)
        recyclerView.layoutManager = gridLayoutManager
    }


}