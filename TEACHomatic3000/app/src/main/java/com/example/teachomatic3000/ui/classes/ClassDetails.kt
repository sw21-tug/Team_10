package com.example.teachomatic3000.ui.classes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.ClassModel
import java.lang.Exception
import kotlin.properties.Delegates

class ClassDetails : AppCompatActivity() {

    private lateinit var class_info: TextView
    private lateinit var button_add_student_to_class: Button
    private lateinit var db: DataBaseHelper
    private var class_id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_details)
        class_id = intent.getStringExtra("class_id")!!.toInt()
        class_info = findViewById(R.id.class_info)
        button_add_student_to_class = findViewById(R.id.button_add_student_to_class)
        db = DataBaseHelper(this.applicationContext)
        updateClassInfoText()

        button_add_student_to_class.setOnClickListener {
            val intent = Intent(this.baseContext, ClassDetailsAddSus::class.java).apply {
                putExtra("class_id", class_id.toString())
            }
            startActivity(intent)
        }
    }

    fun updateClassInfoText() {
        val class_model = db.getClass(class_id)
        class_info.setText(class_model.toString()).toString()
    }
}