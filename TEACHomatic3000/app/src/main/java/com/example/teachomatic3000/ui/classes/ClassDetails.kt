package com.example.teachomatic3000.ui.classes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.ui.lehrstoff.LehrstoffKlassenHelper
import com.example.teachomatic3000.ui.lehrstoff.LehrstoffViewModel
import com.example.teachomatic3000.ui.classes.PruefungErstellen
import com.example.teachomatic3000.models.ClassModel
import java.lang.Exception
import kotlin.properties.Delegates


class ClassDetails : AppCompatActivity() {

    private lateinit var class_info: TextView
    private lateinit var button_add_student_to_class: Button
    private lateinit var db: DataBaseHelper
    private lateinit var pruefung_erstellen: Button
    private var class_id by Delegates.notNull<Int>()
    private lateinit var create_lehrstoff_button: Button
    private lateinit var lehrstoff_liste: ListView
    private lateinit var LehrstoffListAdapter: ArrayAdapter<String>
    private lateinit var lehrstoff_klassen_liste: ListView
    private lateinit var PruefungListAdapter: ArrayAdapter<String>
    private lateinit var PruefungList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_details)
        class_id = intent.getStringExtra("class_id")!!.toInt()
        class_info = findViewById(R.id.class_info)
        pruefung_erstellen = findViewById(R.id.pruefung_erstellen)
        button_add_student_to_class = findViewById(R.id.button_add_student_to_class)
        db = DataBaseHelper(this.applicationContext)
        updateClassInfoText()

        create_lehrstoff_button = findViewById(R.id.create_lehrstoff_button)
        create_lehrstoff_button.setOnClickListener {

            val intent = Intent(this.baseContext, LehrstoffKlassenHelper::class.java).apply {
                putExtra("class_id", class_id)
                println(class_id)
            }
            startActivity(intent)
        }


        PruefungList = this.findViewById(R.id.pruefung_listview)
        PruefungListAdapter = ArrayAdapter<String>(this.baseContext, android.R.layout.simple_list_item_1, db.getPruefung(class_id))
        PruefungList.adapter = PruefungListAdapter
        button_add_student_to_class.setOnClickListener {
            val intent = Intent(this.baseContext, ClassDetailsAddSus::class.java).apply {
                putExtra("class_id", class_id.toString())
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


        pruefung_erstellen.setOnClickListener {
            val intent = Intent (this.baseContext, PruefungErstellen::class.java).apply {
            putExtra("class_id", class_id)
            }
            startActivity(intent) }
    }
    fun updateClassInfoText() {
        val class_model = db.getClass(class_id)
        class_info.setText(class_model.toString()).toString()
    }
}