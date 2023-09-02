package com.example.brawlmobile.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestListener
import com.example.brawlmobile.R
import com.example.brawlmobile.adapter.listener.GlideRequestListener
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

    class ViewHolder(
        view: View,
        textViewIds: List<Int>,
        imageViewIds: List<Int>,
        private val context: Context,
        private val glide: RequestManager

    ) : RecyclerView.ViewHolder(view) {


        private val textViews: List<TextView> = textViewIds.map { view.findViewById(it) }
        private val imageViews: List<ImageView> = imageViewIds.map { view.findViewById(it) }
        val progressBarDefaultSkin: ProgressBar
        val glideRequestListenerDefaultSkin: RequestListener<Drawable>

        val progressBarFirstGadget: ProgressBar
        val glideRequestListenerFirstGadget: RequestListener<Drawable>

        val progressBarSecondGadget: ProgressBar
        val glideRequestListenerSecondGadget: RequestListener<Drawable>

        val progressBarFirstStarPower: ProgressBar
        val glideRequestListenerFirstStarPower: RequestListener<Drawable>

        val progressBarSecondStarPower: ProgressBar
        val glideRequestListenerSecondStarPower: RequestListener<Drawable>
        init {
            progressBarDefaultSkin = view.findViewById(R.id.detailsProgressBarDefaultSkin)
            glideRequestListenerDefaultSkin = GlideRequestListener(progressBarDefaultSkin)

            progressBarFirstGadget = view.findViewById(R.id.detailsProgressBarFirstGadget)
            glideRequestListenerFirstGadget = GlideRequestListener(progressBarFirstGadget)

            progressBarSecondGadget = view.findViewById(R.id.detailsProgressBarSecondGadget)
            glideRequestListenerSecondGadget = GlideRequestListener(progressBarSecondGadget)

            progressBarFirstStarPower = view.findViewById(R.id.detailsProgressBarFirstStarPower)
            glideRequestListenerFirstStarPower = GlideRequestListener(progressBarFirstStarPower)

            progressBarSecondStarPower = view.findViewById(R.id.detailsProgressBarSecondStarPower)
            glideRequestListenerSecondStarPower = GlideRequestListener(progressBarSecondStarPower)
        }


        fun bindData(
            text: TextModel,
            headers: HeaderModel,
            urls: ImagesModel

        ) {
            Log.d("TextAdapter", "Sono in bindData()")
            progressBarDefaultSkin.visibility = View.VISIBLE
            progressBarFirstGadget.visibility = View.VISIBLE
            progressBarSecondGadget.visibility = View.VISIBLE
            progressBarFirstStarPower.visibility = View.VISIBLE
            progressBarSecondStarPower.visibility = View.VISIBLE
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
            glide
                .load(urls.defaultSkin)
                .listener(glideRequestListenerDefaultSkin)
                .centerInside()
                .into(imageViews[0])
            glide
                .load(urls.firstGadgetUrl)
                .listener(glideRequestListenerFirstGadget)
                .into(imageViews[1])
            glide
                .load(urls.secondGadgetUrl)
                .listener(glideRequestListenerSecondGadget)
                .into(imageViews[2])
            glide
                .load(urls.firstStarPowerUrl)
                .listener(glideRequestListenerFirstStarPower)
                .into(imageViews[3])
            glide
                .load(urls.secondStarPowerUrl)
                .listener(glideRequestListenerSecondStarPower)
                .into(imageViews[4])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val glide = Glide.with(context)
//        Log.d(TAG, "viewType vale = $viewType")
        val view = inflater.inflate(viewType, parent, false)
        val textViewIds = getTextViewIdsForLayout(viewType)
        val imageViewIds = getImageViewIdsForLayout(viewType)
        Log.d(TAG, "onCreateViewHolder")
        return ViewHolder(view, textViewIds, imageViewIds, context, glide)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")

        text?.let { it ->
            headers?.let { it1 ->
                urls?.let { it2 -> holder.bindData(it, it1, it2) }

            }
        }
    }

    override fun getItemCount(): Int {
        return if (text != null) 1 else 0
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

    override fun getItemViewType(position: Int): Int {
//        Log.d(TAG, "layoutResId = ${text?.layoutResId}")
        return text?.layoutResId ?: 0
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