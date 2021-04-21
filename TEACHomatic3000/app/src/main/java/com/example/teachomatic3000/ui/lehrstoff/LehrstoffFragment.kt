package com.example.teachomatic3000.ui.lehrstoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.example.teachomatic3000.ui.home.HomeViewModel

class LehrstoffFragment : Fragment() {

    private lateinit var lehrstoffViewModel: LehrstoffViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lehrstoffViewModel =
                ViewModelProvider(this).get(LehrstoffViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lehrstoff, container, false)

        //code

        return root
    }

}