package com.example.teachomatic3000.ui.classes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.ClassModel
import com.example.teachomatic3000.ui.lehrstoff.LehrstoffKlassenHelper
import kotlin.properties.Delegates

class ClassDetails : AppCompatActivity() {

    private lateinit var class_info: TextView
    private lateinit var button_add_student_to_class: Button
    private lateinit var button_anonymize_class: Button
    private lateinit var db: DataBaseHelper
    private lateinit var pruefung_erstellen: Button
    private var class_id by Delegates.notNull<Int>()
    private lateinit var create_lehrstoff_button: Button
    private lateinit var lehrstoff_liste: ListView
    private lateinit var lehrstoffListAdapter: ArrayAdapter<String>
    private lateinit var pruefungListAdapter: ArrayAdapter<String>
    private lateinit var schuelerKlassenListAdapter: ArrayAdapter<String>
    private lateinit var pruefungList: ListView
    private lateinit var schueler_klassen_liste: ListView
    private lateinit var classModel: ClassModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_details)
        class_id = intent.getStringExtra("class_id")!!.toInt()
        class_info = findViewById(R.id.class_info)
        db = DataBaseHelper(this.baseContext)
        updateClassInfoText()

        classModel = db.getClass(class_id)!!

        pruefung_erstellen = findViewById(R.id.pruefung_erstellen)
        button_add_student_to_class = findViewById(R.id.button_add_student_to_class)
        button_anonymize_class = findViewById(R.id.anonymize_class_button)
        create_lehrstoff_button = findViewById(R.id.create_lehrstoff_button)

        schueler_klassen_liste = findViewById(R.id.schueler_klassen_liste)
        schuelerKlassenListAdapter = ArrayAdapter(this.baseContext, android.R.layout.simple_list_item_1, db.getStudentsOfClass(classModel))
        schueler_klassen_liste.adapter = schuelerKlassenListAdapter

        pruefungList = findViewById(R.id.pruefung_listview)
        pruefungListAdapter = ArrayAdapter(this.baseContext, android.R.layout.simple_list_item_1, db.getPruefung(class_id, this.baseContext))
        pruefungList.adapter = pruefungListAdapter

        lehrstoff_liste = findViewById(R.id.lehrstoff_klassen_liste)
        lehrstoffListAdapter = ArrayAdapter(this.baseContext, android.R.layout.simple_list_item_1, db.getLehrstoffeForKlasse(class_id, this.baseContext))
        lehrstoff_liste.adapter = lehrstoffListAdapter

        create_lehrstoff_button.setOnClickListener {
            val intent = Intent(this.baseContext, LehrstoffKlassenHelper::class.java).apply {
                putExtra("class_id", class_id)
            }
            startActivity(intent)
        }

        button_add_student_to_class.setOnClickListener {
            val intent = Intent(this.baseContext, ClassDetailsAddSus::class.java).apply {
                putExtra("class_id", class_id.toString())
            }
            startActivity(intent)
        }

        button_anonymize_class.setOnClickListener () {
            db.anonymizeClass(class_id)
            //Toast.makeText(root.context, R.string.class_anonym, Toast.LENGTH_LONG).show()
        }

        lehrstoff_liste.setOnItemClickListener { parent, view, position, id ->
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
            startActivity(intent)
        }

        schueler_klassen_liste.setOnTouchListener(OnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN ->
                    v.parent.requestDisallowInterceptTouchEvent(true)
                MotionEvent.ACTION_UP ->
                    v.parent.requestDisallowInterceptTouchEvent(false)
            }
            v.onTouchEvent(event)
            true
        })

        lehrstoff_liste.setOnTouchListener(OnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN ->
                    v.parent.requestDisallowInterceptTouchEvent(true)
                MotionEvent.ACTION_UP ->
                    v.parent.requestDisallowInterceptTouchEvent(false)
            }
            v.onTouchEvent(event)
            true
        })

        pruefungList.setOnTouchListener(OnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN ->
                    v.parent.requestDisallowInterceptTouchEvent(true)
                MotionEvent.ACTION_UP ->
                    v.parent.requestDisallowInterceptTouchEvent(false)
            }
            v.onTouchEvent(event)
            true
        })
    }

    fun updateClassInfoText() {
        val class_model = db.getClass(class_id)
        class_info.setText(class_model.toString()).toString()
    }

    override fun onResume() {
        super.onResume()
        schuelerKlassenListAdapter = ArrayAdapter(this.baseContext, android.R.layout.simple_list_item_1, db.getStudentsOfClass(classModel))
        schueler_klassen_liste.adapter = schuelerKlassenListAdapter
        pruefungListAdapter = ArrayAdapter(this.baseContext, android.R.layout.simple_list_item_1, db.getPruefung(class_id, this.baseContext))
        pruefungList.adapter = pruefungListAdapter
        lehrstoffListAdapter = ArrayAdapter(this.baseContext, android.R.layout.simple_list_item_1, db.getLehrstoffeForKlasse(class_id, this.baseContext))
        lehrstoff_liste.adapter = lehrstoffListAdapter
    }
}