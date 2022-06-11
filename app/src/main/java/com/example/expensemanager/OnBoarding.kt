package com.example.expensemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class OnBoarding : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         return inflater.inflate(R.layout.fragment_on_boarding, container, false)
       }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        name_text.doOnTextChanged { text, _, _, _ ->
            if(text!!.isEmpty())
                name_layout.error = "This is a required field"
            else
                name_layout.error = null
        }

        income_text.doOnTextChanged { text, _, _, _ ->
            if(text!!.isEmpty())
                income_layout.error = "This is a required field"
            else
                income_layout.error = null
        }

        cont_button.setOnClickListener {
            // make sure ki null na ho .
        }
    }

    }



