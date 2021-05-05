package com.example.teachomatic3000.ui.classes

import android.content.Intent
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
import java.util.*

class ClassesFragment : Fragment() {

    private lateinit var classesViewModel: ClassesViewModel
    private lateinit var classList: ListView
    private lateinit var classDatabase: DataBaseHelper
    private lateinit var classListAdapter: ArrayAdapter<String>
    private lateinit var textClassName: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        classesViewModel = ViewModelProvider(this).get(ClassesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_classes, container, false)
        val buttonAddClass: Button = root.findViewById(R.id.button_add_class)
        textClassName = root.findViewById(R.id.text_class_name)
        classList = root.findViewById(R.id.class_list)
        classDatabase = DataBaseHelper(root.context) //context vom layout wird hier erstellt, damit wirs unten verwenden können

        fun updateClassList(){
            classListAdapter = ArrayAdapter<String>(root.context, android.R.layout.simple_list_item_1, classDatabase.getClasses())
            classList.adapter = classListAdapter
            textClassName.text.clear()
        }

        updateClassList()

        buttonAddClass.setOnClickListener {
            if(textClassName.text.isEmpty() || textClassName.text.length > 255)
            {
                Toast.makeText(root.context,R.string.text_length255,Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else
            {
                // database helper (mit try catch)
                try {
                    val className = String(textClassName.text.toString().toByteArray(), charset("UTF-8"))
                    val classModel = ClassModel(0, className)
                    val success = classDatabase.addClass(classModel) //implement in dbHelper

                    if(!success) {
                        Toast.makeText(root.context,R.string.error_add_class,Toast.LENGTH_LONG).show()
                    }
                }
                catch (exception: Exception) {
                    Toast.makeText(root.context,R.string.error_add_class,Toast.LENGTH_LONG).show()
                }
            }
            updateClassList()
        }

        classList.setOnItemClickListener { parent, view, position, id ->
            val class_info = parent.getItemAtPosition(position)
            val class_parts = class_info.toString().split(" ").toTypedArray()
            val class_id = class_parts[0]

            val intent = Intent(root.context, ClassDetails::class.java).apply {
                putExtra("class_id", class_id)
            }
            startActivity(intent)
        }

        return root
    }
}