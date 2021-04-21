package com.example.teachomatic3000.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.google.android.material.snackbar.Snackbar

class ClassesFragment : Fragment() {

    private lateinit var classesViewModel: ClassesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        classesViewModel = ViewModelProvider(this).get(ClassesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_classes, container, false)
        val buttonAddClass: Button = root.findViewById(R.id.button_add_class)
        val textClassName: TextView = root.findViewById(R.id.text_class_name)
        val classID: Integer; // Hier später die ID aus der Datenbank auslesen.

        buttonAddClass.setOnClickListener {
            if(textClassName.text.isEmpty())
            {
                Toast.makeText(this.requireContext(),"Der Klassenname darf nicht leer sein.",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            // ToDo Klasse zur Datenbank hinzufügen

        }

        return root
    }
}