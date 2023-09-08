package com.example.brawlmobile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.brawlmobile.R
import com.example.brawlmobile.viewmodel.PlayerActivityViewModel


class InputFragment : Fragment() {

    private lateinit var viewModel: PlayerActivityViewModel
    private lateinit var notValidTag: TextView

    var onTagSubmitted: ((tag: String) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        val editText: EditText = view.findViewById(R.id.editText)
        val submitButton: Button = view.findViewById(R.id.submitTag)
        notValidTag = view.findViewById(R.id.notValidPlayerTag)
        notValidTag.visibility = View.GONE

        viewModel = ViewModelProvider(requireActivity())[PlayerActivityViewModel::class.java]

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { errorMessagge ->
            if (!errorMessagge.isNullOrEmpty()) {
                showTagError()
            }
        })
        // Gestisco il submit del tag
        submitButton.setOnClickListener {
            val playerTag = editText.text.toString()

            onTagSubmitted?.invoke(playerTag)
        }
        return view
    }

    fun showTagError() {
        notValidTag.visibility = View.VISIBLE
    }
}