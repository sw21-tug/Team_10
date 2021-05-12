package com.example.teachomatic3000.ui.classes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.MainActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.ui.lehrstoff.LehrstoffFragment
import com.example.teachomatic3000.ui.lehrstoff.LehrstoffKlassenHelper
import com.example.teachomatic3000.ui.lehrstoff.LehrstoffViewModel
import kotlin.properties.Delegates


class ClassDetails : AppCompatActivity() {

    private lateinit var class_info: TextView
    private lateinit var db: DataBaseHelper
    private var class_id by Delegates.notNull<Int>()
    private lateinit var create_lehrstoff_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_details)
        class_id = intent.getStringExtra("class_id")!!.toInt()
        class_info = findViewById(R.id.class_info)
        db = DataBaseHelper(this.applicationContext)
        updateClassInfoText()

        create_lehrstoff_button = findViewById(R.id.create_lehrstoff_button)
        create_lehrstoff_button.setOnClickListener {
            //this.setContentView(R.layout.fragment_lehrstoff)

            val intent = Intent(this.baseContext, LehrstoffKlassenHelper::class.java).apply {
                putExtra("class_id", class_id)
                println(class_id)
                //putExtra("pos", )
            }
            startActivity(intent)
            //startActivity(Intent(this, LehrstoffFragment::class.java))
            //setContentView(R.layout.fragment_lehrstoff)
            //startActivity( Intent(MainActivity::class, LehrstoffFragment::class.java))

        }

    }


    fun updateClassInfoText() {
        val class_model = db.getClass(class_id)
        class_info.setText(class_model.toString()).toString()
    }

    fun openActivity2(view: View) {
        intent = Intent(view.context,LehrstoffFragment::class.java)
        startActivity(intent)
    }
}
