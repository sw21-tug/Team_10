package com.example.teachomatic3000.ui.lehrstoff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R


class LehrstoffKlassenHelper : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lehrstoff_kassen_helper)
        val test = intent.getStringExtra("class_id")!!.toInt()
    }
}
