package com.example.brawlmobile.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.brawlmobile.R
import org.w3c.dom.Text

class DetailsDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_NAME = "EXTRA_NAME"
        private const val ARG_MAX_LEVEL = "EXTRA_MAX_LEVEL"
        private const val ARG_MAX_EVOLUTION_LEVEL = "EXTRA_MAX_EVOLUTION_LEVEL"
        private const val ARG_ICON_NORMAL = "EXTRA_ICON_NORMAL"
        private const val ARG_ICON_EVOLUTION = "EXTRA_ICON_EVOLUTION"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details_clash, container, false)

        val cardName = view.findViewById<TextView>(R.id.cardName)
        val maxLevel = view.findViewById<TextView>(R.id.maxLevel)
        val maxEvolutionLevel = view.findViewById<TextView>(R.id.maxEvolutionLevel)
        val iconUrlMedium = view.findViewById<ImageView>(R.id.iconUrlMedium)
        val iconUrlEvolutionMedium = view.findViewById<ImageView>(R.id.iconUrlEvolutionMedium)

        // Ottieni i dati dal Bundle
        val name = arguments?.getString(ARG_NAME)
        val maxLevelValue = arguments?.getString(ARG_MAX_LEVEL)
        val maxEvolutionLevelValue = arguments?.getString(ARG_MAX_EVOLUTION_LEVEL)
        val iconUrlMediumValue = arguments?.getString(ARG_ICON_NORMAL)
        val iconUrlEvolutionMediumValue = arguments?.getString(ARG_ICON_EVOLUTION)
//        val description = arguments?.getString(ARG_DESCRIPTION)

        // Imposta i dati nelle view
        cardName.text = name
        maxLevel.text = maxLevelValue
        maxEvolutionLevel.text = maxEvolutionLevelValue
        Glide.with(this)
            .load(iconUrlMediumValue)
            .into(iconUrlMedium)

        Glide.with(this)
            .load(iconUrlEvolutionMediumValue)
            .error(R.drawable.ic_delete)
            .into(iconUrlEvolutionMedium)


//        descriptionTextView.text = description

        return view
    }
}