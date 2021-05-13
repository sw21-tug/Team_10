package com.example.teachomatic3000.ui.lehrstoffuebersicht

import android.app.DatePickerDialog
import android.os.Build
//import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
//import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.LehrstoffModel
import java.lang.Exception
import java.util.*

class LehrstoffUebersichtFragment : androidx.fragment.app.Fragment() {

    private lateinit var lehrstoffUebersichtViewModel: LehrstoffUebersichtViewModel
    private lateinit var Database: DataBaseHelper
    private lateinit var LehrstoffListAdapter: ArrayAdapter<String>
    private lateinit var LehrstoffList: ListView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lehrstoffUebersichtViewModel =
                ViewModelProvider(this).get(LehrstoffUebersichtViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lehrstoffuebersicht, container, false)

        Database = DataBaseHelper(root.context)
        LehrstoffList = root.findViewById(R.id.lehrstoff_list_database)
        LehrstoffListAdapter = ArrayAdapter<String>(root.context, android.R.layout.simple_list_item_1, Database.getLehrstoffe())
        LehrstoffList.adapter = LehrstoffListAdapter



        return root
    }



}