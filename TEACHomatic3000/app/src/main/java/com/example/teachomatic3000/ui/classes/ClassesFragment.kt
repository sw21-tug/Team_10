package com.example.teachomatic3000.ui.classes

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.ClassModel
import java.lang.Exception
import java.util.*

class ClassesFragment : Fragment() {

    private lateinit var classList: ListView
    private lateinit var classDatabase: DataBaseHelper
    private lateinit var classListAdapter: ArrayAdapter<String>
    private lateinit var textClassName: EditText
    private lateinit var root: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        root = inflater.inflate(R.layout.fragment_classes, container, false)
        val buttonAddClass: Button = root.findViewById(R.id.button_add_class)
        textClassName = root.findViewById(R.id.text_class_name)
        classList = root.findViewById(R.id.class_list)
        classDatabase = DataBaseHelper(root.context)

        fun updateClassList(){
            classListAdapter = ArrayAdapter(root.context, android.R.layout.simple_list_item_1, classDatabase.getClasses())
            classList.adapter = classListAdapter
            textClassName.text.clear()
        }

        updateClassList()

        buttonAddClass.setOnClickListener {
            if(textClassName.text.isEmpty() || textClassName.text.length > 255)
            {
                Toast.makeText(root.context,R.string.error_text_length_255,Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else
            {
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

        classList.setOnItemLongClickListener { parent, view, position, id ->
            val pop = PopupMenu(root.context, view)
            pop.inflate(R.menu.popup_edit_class_name)

            pop.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

                when (item!!.itemId) {
                    R.id.popup_edit -> {

                        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(root.context)
                        builder.setTitle("Klassenname bearbeiten:")
                        val input = EditText(root.context)
                        input.setHint("Neuen Klassennamen eingeben")
                        input.inputType = InputType.TYPE_CLASS_TEXT
                        builder.setView(input)
                        builder.setPositiveButton("Speichern", DialogInterface.OnClickListener { dialog, which ->
                            val new_class_name = input.text.toString()
                            val class_info = parent.getItemAtPosition(position)
                            val class_parts = class_info.toString().split(" ").toTypedArray()
                            val class_id = class_parts[0].toInt()

                            classDatabase.changeClassName(class_id, new_class_name)
                            updateClassList()

                            Toast.makeText(root.context,"Klassenname wurde erfolgreich geändert.",Toast.LENGTH_LONG).show()
                        })
                        builder.setNegativeButton("Abbrechen", DialogInterface.OnClickListener { dialog, which -> dialog.cancel()
                            Toast.makeText(root.context,"Klassenname wurde nicht geändert.",Toast.LENGTH_LONG).show()
                        })
                        builder.show()
                    }
                }
                return@OnMenuItemClickListener(true)
            })
            pop.show()
            return@setOnItemLongClickListener(true)
        }
        return root
    }


}