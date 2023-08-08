package com.example.brawlmobile.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlmobile.R
import com.example.brawlmobile.models.brawler.HeaderModel
import com.example.brawlmobile.models.web.TextModel

class TextAdapter : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    private var headers: HeaderModel? = null
    private var text: TextModel? = null
    private var TAG = "TextAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)
        val textViewIds = getTextViewIdsForLayout(viewType)
        return ViewHolder(view, textViewIds)
    }

    class ViewHolder(
        view: View,
        textViewIds: List<Int>

    ) : RecyclerView.ViewHolder(view) {
        private val textViews: List<TextView> = textViewIds.map { view.findViewById(it) }

        fun bindData(
            text: TextModel,
            headers: HeaderModel
        ) {
//            Log.d(TAG, )
            when(textViews.size) {
                12 -> {
                    textViews[0].text = headers.name
                    textViews[1].text = text.description
                    textViews[2].text = text.firstAttack
                    textViews[3].text = text.firstSuper
                    textViews[4].text = headers.firstGadget
                    textViews[5].text = text.firstGadget
                    textViews[6].text = headers.secondGadget
                    textViews[7].text = text.secondGadget
                    textViews[8].text = headers.firstStarPower
                    textViews[9].text = text.firstStarPower
                    textViews[10].text = headers.secondStarPower
                    textViews[11].text = text.secondStarPower
                }
                13 -> {
                    textViews[0].text = headers.name
                    textViews[1].text = text.description
                    textViews[2].text = text.trait
                    textViews[3].text = text.firstAttack
                    textViews[4].text = text.firstSuper
                    textViews[5].text = headers.firstGadget
                    textViews[6].text = text.firstGadget
                    textViews[7].text = headers.secondGadget
                    textViews[8].text = text.secondGadget
                    textViews[9].text = headers.firstStarPower
                    textViews[10].text = text.firstStarPower
                    textViews[11].text = headers.secondStarPower
                    textViews[12].text = text.secondStarPower
                }
            }
        }
    }

    fun setData(data_text: TextModel, data_headers: HeaderModel) {
        text = data_text
        headers = data_headers
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        text?.let {headers?.let { it1 -> holder.bindData(it, it1) }

        }
    }

    override fun getItemCount(): Int {
        return 1
    }


    private fun getTextViewIdsForLayout(layoutId: Int): List<Int> {

        return when (layoutId) {
            R.layout.item_text_size7 -> listOf(
                // Info generali
                R.id.BrawlerName,
                R.id.txtDescription,
                // Attacchi
                R.id.txtFirstAttack,
                R.id.txtFirstSuper,
                // Gadgets
                R.id.placeholder_G1,
                R.id.txtFirstGadget,
                R.id.placeholder_G2,
                R.id.txtSecondGadget,
                // StarPowers
                R.id.placeholder_S1,
                R.id.txtFirstSuper,
                R.id.placeholder_S2,
                R.id.txtSecondStarPower
            )
            R.layout.item_text_size8 -> listOf(
                // Info generali
                R.id.BrawlerName,
                R.id.txtDescription,
                R.id.txtTrait,
                // Attacchi
                R.id.txtFirstAttack,
                R.id.txtFirstSuper,
                // Gadgets
                R.id.placeholder_G1,
                R.id.txtFirstGadget,
                R.id.placeholder_G2,
                R.id.txtSecondGadget,
                // StarPowers
                R.id.placeholder_S1,
                R.id.txtFirstSuper,
                R.id.placeholder_S2,
                R.id.txtSecondStarPower
            )
            else -> emptyList()
        }

    }
}