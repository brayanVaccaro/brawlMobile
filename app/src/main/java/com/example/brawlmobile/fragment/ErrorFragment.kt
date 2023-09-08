package com.example.brawlmobile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.brawlmobile.R

class ErrorFragment : Fragment() {
    companion object {
        private const val ARG_ERROR_MESSAGE = ""

        fun newInstance(errorMessage: String): ErrorFragment {
            val fragment = ErrorFragment()
            val args = Bundle()
            args.putString(ARG_ERROR_MESSAGE, errorMessage)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val errorMessage = arguments?.getString(ARG_ERROR_MESSAGE)
        val txtErrorInfo: TextView = view.findViewById(R.id.txtErrorInfo)
        txtErrorInfo.text = errorMessage
    }
}