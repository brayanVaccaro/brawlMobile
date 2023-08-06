package com.example.brawlmobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R

class TextAdapter: RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    class ViewHolder (
        view: View,

        ) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    fun getTextViewIdsForLayout(layoutId: Int): List<Int> {

        return when(layoutId) {
            R.layout.item_text_size7
        }

    }
}