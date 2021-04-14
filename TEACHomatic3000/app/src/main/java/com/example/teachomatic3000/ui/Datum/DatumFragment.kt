package com.example.teachomatic3000.ui.Datum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R

class DatumFragment : Fragment() {

    private lateinit var datumViewModel: DatumViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        datumViewModel =
                ViewModelProvider(this).get(DatumViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_datum, container, false)
        val textView: TextView = root.findViewById(R.id.text_datum)
        datumViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}