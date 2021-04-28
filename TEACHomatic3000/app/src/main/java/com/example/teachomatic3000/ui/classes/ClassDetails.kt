package com.example.teachomatic3000.ui.classes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import kotlin.properties.Delegates

class ClassDetails : AppCompatActivity() {

    private lateinit var class_info: TextView
    private lateinit var db: DataBaseHelper
    private var class_id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_details)
        class_id = intent.getStringExtra("class_id")!!.toInt()
        class_info = findViewById(R.id.class_info)
        db = DataBaseHelper(this.applicationContext)
        updateClassInfoText()
    }


    fun updateClassInfoText() {
        val class_model = db.getClass(class_id)
        class_info.setText(class_model.toString()).toString()
    }
}