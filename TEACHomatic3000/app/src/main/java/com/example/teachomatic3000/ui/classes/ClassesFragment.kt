package com.example.teachomatic3000.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.ClassModel
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class ClassesFragment : Fragment() {

    private lateinit var classesViewModel: ClassesViewModel
    private lateinit var classList: ListView
    private lateinit var classDatabase: DataBaseHelper

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        classesViewModel = ViewModelProvider(this).get(ClassesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_classes, container, false)
        val buttonAddClass: Button = root.findViewById(R.id.button_add_class)
        val textClassName: TextView = root.findViewById(R.id.text_class_name)
        classList = root.findViewById(R.id.class_list)
        classDatabase = DataBaseHelper(root.context) //context vom layout wird hier erstellt, damit wirs unten verwenden können
        val classID: Integer; // Hier später die ID aus der Datenbank auslesen.

        fun updateClassList(){
            //Todo: update list view
        }

        buttonAddClass.setOnClickListener {
            if(textClassName.text.isEmpty())
            {
                Toast.makeText(root.context,"Der Klassenname darf nicht leer sein.",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // ToDo Klasse zur Datenbank hinzufügen
            if(textClassName.text.isNotEmpty()) //TODO: andere conditions ev.
            {
                val className = textClassName.text.toString()
                var classModel = ClassModel(0,className)
                // database helper (mit try catch)
                try {
                    classDatabase.addClass(classModel) //implement in dbHelper

                    //TODO: ähnlich wie in studentFragment
                }
                catch (exception: Exception) {
                    Toast.makeText(root.context,"Klasse konnte nicht hinzugefügt werden",Toast.LENGTH_LONG).show()
                }
            }

        }

        return root
    }
}