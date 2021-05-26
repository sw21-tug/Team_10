package com.example.teachomatic3000.ui.lehrstoff

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper

class LehrstoffUebersichtFragment : androidx.fragment.app.Fragment() {

    private lateinit var db: DataBaseHelper
    private lateinit var LehrstoffListAdapter: ArrayAdapter<String>
    private lateinit var LehrstoffList: ListView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_lehrstoffuebersicht, container, false)

        db = DataBaseHelper(root.context)
        LehrstoffList = root.findViewById(R.id.lehrstoff_list_database)
        LehrstoffListAdapter = ArrayAdapter(root.context, android.R.layout.simple_list_item_1, db.getLehrstoffe(root.context))
        LehrstoffList.adapter = LehrstoffListAdapter

        return root
    }
}