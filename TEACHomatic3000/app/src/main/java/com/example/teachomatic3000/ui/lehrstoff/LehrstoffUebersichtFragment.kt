package com.example.teachomatic3000.ui.lehrstoff

import android.content.Intent
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

        LehrstoffList.setOnItemClickListener { parent, view, position, id ->
            val data_pos = parent.getItemAtPosition(position).toString()
            val id_split = data_pos.split("Lehrstoff-ID: ").toTypedArray()
            val id_split1 = id_split[1].split(" ")
            val id = id_split1[0].toInt()

            val intent = Intent(root.context, LehrstoffKlassenHelper::class.java).apply {
                putExtra("lehrstoff_id", id)
                putExtra("title", db.getLehrstoffOnPos(id)[1])
                putExtra("description", db.getLehrstoffOnPos(id)[2])
                putExtra("date", db.getLehrstoffOnPos(id)[3])
                putExtra("date_create", db.getLehrstoffOnPos(id)[4])
                putExtra("date_edit", db.getLehrstoffOnPos(id)[5])
                putExtra("class", db.getLehrstoffOnPos(id)[6])
                putExtra("check_edit", true)
            }
            startActivity(intent)
        }

        return root
    }
}