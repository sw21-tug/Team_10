package com.example.teachomatic3000.ui.classes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.ui.lehrstoff.LehrstoffKlassenHelper
import kotlin.properties.Delegates


class ClassDetails : AppCompatActivity() {

    private lateinit var class_info: TextView
    private lateinit var db: DataBaseHelper
    private var class_id by Delegates.notNull<Int>()
    private lateinit var create_lehrstoff_button: Button
    private lateinit var lehrstoff_liste: ListView
    private lateinit var LehrstoffListAdapter: ArrayAdapter<String>
    private lateinit var lehrstoff_klassen_liste: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_details)
        class_id = intent.getStringExtra("class_id")!!.toInt()
        class_info = findViewById(R.id.class_info)
        db = DataBaseHelper(this.applicationContext)
        updateClassInfoText()

        create_lehrstoff_button = findViewById(R.id.create_lehrstoff_button)
        create_lehrstoff_button.setOnClickListener {

            val intent = Intent(this.baseContext, LehrstoffKlassenHelper::class.java).apply {
                putExtra("class_id", class_id)
            }
            startActivity(intent)
        }

        //Database = DataBaseHelper(root.context)
        lehrstoff_liste = findViewById(R.id.lehrstoff_klassen_liste)
        LehrstoffListAdapter = ArrayAdapter<String>(this.applicationContext, android.R.layout.simple_list_item_1, db.getLehrstoffeForKlasse(class_id))
        lehrstoff_liste.adapter = LehrstoffListAdapter

        //lehrstoff_liste.invalidate()

        //Lehrstoff bearbeiten
        lehrstoff_klassen_liste = findViewById(R.id.lehrstoff_klassen_liste)
        lehrstoff_klassen_liste.setOnItemClickListener { parent, view, position, id ->
            val data_pos = parent.getItemAtPosition(position).toString()
            val id_split = data_pos.split("Lehrstoff-ID: ").toTypedArray()
            val id_split1 = id_split[1].split(" ")
            val id = id_split1[0].toInt()

            val intent = Intent(this.baseContext, LehrstoffKlassenHelper::class.java).apply {
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

    }

    fun updateClassInfoText() {
        val class_model = db.getClass(class_id)
        class_info.setText(class_model.toString()).toString()
    }
}