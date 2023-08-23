package com.example.brawlmobile.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brawlmobile.R
import com.example.brawlmobile.models.brawler.HeaderModel
import com.example.brawlmobile.models.web.ImagesModel
import com.example.brawlmobile.models.web.TextModel

class TextAdapter(
    private val context: Context
) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    private var headers: HeaderModel? = null
    private var text: TextModel? = null
    private var urls: ImagesModel? = null
    private var TAG = "TextAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        Log.d(TAG, "viewType vale = $viewType")
        val view = inflater.inflate(viewType, parent, false)
        val textViewIds = getTextViewIdsForLayout(viewType)
        val imageViewIds = getImageViewIdsForLayout(viewType)
        Log.d(TAG, "onCreateViewHolder")
        return ViewHolder(view, textViewIds, imageViewIds, context)
    }

    private fun getImageViewIdsForLayout(layoutId: Int): List<Int> {
        Log.d(TAG, "Sto recuperando gli id delle ImageView")

        return when (layoutId) {
            R.layout.item_text_size7,
            R.layout.item_text_size9,
            R.layout.item_text_size8 -> {
                Log.d(TAG, "Sono in size 7/8/9")
                listOf(
                    R.id.imgDefaultSkin,
                    R.id.imgFirstGadget,
                    R.id.imgSecondGadget,
                    R.id.imgFirstStarPower,
                    R.id.imgSecondStarPower

                )
            }
            else -> emptyList()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")

        text?.let {it ->
            headers?.let { it1 ->
                urls?.let { it2 -> holder.bindData(it, it1, it2) }

            }
        }
    }

    class ViewHolder(
        view: View,
        textViewIds: List<Int>,
        imageViewIds: List<Int>,
        private val context: Context

    ) : RecyclerView.ViewHolder(view) {


        private val textViews: List<TextView> = textViewIds.map { view.findViewById(it) }
        private val imageViews: List<ImageView> = imageViewIds.map { view.findViewById(it) }

        fun bindData(
            text: TextModel,
            headers: HeaderModel,
            urls: ImagesModel

        ) {
            Log.d("TextAdapter", "Sono in bindData()")

            when (textViews.size) {
                12 -> {
                    Log.d("TextAdapter", "Sono in size 12")

                    textViews[0].text = headers.name
                    textViews[1].text = text.description
                    textViews[2].text = text.firstAttack
                    textViews[3].text = text.firstSuper
                    textViews[4].text = headers.firstGadget
                    textViews[5].text = text.firstGadget
                    textViews[6].text = headers.secondGadget
                    textViews[7].text = text.secondGadget
                    textViews[8].text = headers.firstStarPower
                    Log.d(
                        "TextAdapter",
                        "setto i dati della firstStarPower: ${text.firstStarPower}"
                    )
                    textViews[9].text = text.firstStarPower
                    textViews[10].text = headers.secondStarPower
                    textViews[11].text = text.secondStarPower
                }
                13 -> {
                    Log.d("TextAdapter", "Sono in size 13")

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
                14 -> {
                    Log.d("TextAdapter", "Sono in size 14")

                    textViews[0].text = headers.name
                    textViews[1].text = text.description
                    textViews[2].text = text.firstAttack
                    textViews[3].text = text.secondAttack
                    textViews[4].text = text.firstSuper
                    textViews[5].text = text.secondSuper
                    textViews[6].text = headers.firstGadget
                    textViews[7].text = text.firstGadget
                    textViews[8].text = headers.secondGadget
                    textViews[9].text = text.secondGadget
                    textViews[10].text = headers.firstStarPower
                    textViews[11].text = text.firstStarPower
                    textViews[12].text = headers.secondStarPower
                    textViews[13].text = text.secondStarPower
                }
            }
            Glide.with(context).load(urls.defaultSkin).into(imageViews[0])
            Glide.with(context).load(urls.firstGadgetUrl).into(imageViews[1])
            Glide.with(context).load(urls.secondGadgetUrl).into(imageViews[2])
            Glide.with(context).load(urls.firstStarPowerUrl).into(imageViews[3])
            Glide.with(context).load(urls.secondStarPowerUrl).into(imageViews[4])
        }
    }

    override fun getItemCount(): Int {
        return if (text != null) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
//        Log.d(TAG, "layoutResId = ${text?.layoutResId}")
        return text?.layoutResId ?: 0
    }

    fun setData(data_text: TextModel, data_headers: HeaderModel) {
        Log.d(TAG, "sono in setdata, setto text e headers")
//        Log.d(TAG, "text vale = $data_text")
//        Log.d(TAG, "headers vale = $data_headers")
        text = data_text
        headers = data_headers
        notifyDataSetChanged()
    }

    fun setImages(data_urls: ImagesModel) {

        Log.d(TAG, "sono in setImages, setto gli urls")
//        Log.d(TAG, "urls vale = $data_urls")
        urls = data_urls
        notifyDataSetChanged()

    }


    private fun getTextViewIdsForLayout(layoutId: Int): List<Int> {
        Log.d(TAG, "Sto recuperando gli id delle TextView")


        return when (layoutId) {
            R.layout.item_text_size7 -> {
                Log.d(TAG, "Sono in size 7")
                listOf(
                    // Info generali
                    R.id.HeaderName,
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
                    R.id.txtFirstStarPower,
                    R.id.placeholder_S2,
                    R.id.txtSecondStarPower
                )
            }
            R.layout.item_text_size8 -> listOf(
                // Info generali
                R.id.HeaderName,
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
                R.id.txtFirstStarPower,
                R.id.placeholder_S2,
                R.id.txtSecondStarPower
            )
            R.layout.item_text_size9 -> listOf(
                // Info generali
                R.id.HeaderName,
                R.id.txtDescription,
                // Attacchi
                R.id.txtFirstAttack,
                R.id.txtSecondAttack,
                R.id.txtFirstSuper,
                R.id.txtSecondSuper,
                // Gadgets
                R.id.placeholder_G1,
                R.id.txtFirstGadget,
                R.id.placeholder_G2,
                R.id.txtSecondGadget,
                // StarPowers
                R.id.placeholder_S1,
                R.id.txtFirstStarPower,
                R.id.placeholder_S2,
                R.id.txtSecondStarPower
            )
            else -> emptyList()
        }

    }
}