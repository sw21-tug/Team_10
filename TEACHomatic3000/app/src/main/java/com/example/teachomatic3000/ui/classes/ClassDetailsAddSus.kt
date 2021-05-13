package com.example.teachomatic3000.ui.classes

import CustomAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import app.com.q3.DataModel
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.ClassModel
import com.example.teachomatic3000.models.StudentModel
import kotlin.properties.Delegates

class ClassDetailsAddSus : AppCompatActivity() {

    private lateinit var sus_list_of_class: ListView
    private lateinit var btnAddSusToClass: Button
    private lateinit var tVClassDetailsAddSusInfo: TextView
    private lateinit var adapter: CustomAdapter
    private var dataModel: ArrayList<DataModel>? = null
    private var class_id by Delegates.notNull<Int>()
    private lateinit var db: DataBaseHelper
    private lateinit var students: List<String>
    private lateinit var classModel: ClassModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_details_add_sus)
        class_id = intent.getStringExtra("class_id")!!.toInt()
        sus_list_of_class = findViewById(R.id.sus_list_of_class)
        btnAddSusToClass = findViewById(R.id.btnAddSusToClass)
        tVClassDetailsAddSusInfo = findViewById(R.id.tVClassDetailsAddSusInfo)

        db = DataBaseHelper(this.baseContext)
        students = db.getStudents()
        classModel = db.getClass(class_id)!!
        dataModel = ArrayList<DataModel>()

        tVClassDetailsAddSusInfo.text = classModel.toString()

        for(student in students) {
            dataModel!!.add(DataModel(student, false))
        }

        adapter = CustomAdapter(dataModel!!, this.baseContext)
        sus_list_of_class.adapter = adapter

        /*sus_list_of_class.setOnItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val dataModel: DataModel = dataModel!![position]
                dataModel.checked = !dataModel.checked
                Log.d("SUS", "Going to check:" + dataModel.checked.toString())
                adapter.notifyDataSetChanged()
            }*/

        sus_list_of_class.setOnItemClickListener { parent, view, position, id ->
            val dataModel: DataModel = dataModel!![position]
            dataModel.checked = !dataModel.checked
            adapter.notifyDataSetChanged()
        }

        btnAddSusToClass.setOnClickListener {

            for (data in dataModel!!) {
                if(data.checked) {
                    val all_students = db.getStudentsOfClass(classModel)
                    val student = db.getStudent(data.toString().split(" ")[0])
                    var flag = false

                    for(s in all_students) {
                        if(s.equals(student)) {
                            flag = true
                        }
                    }

                    if(!flag) {

                        //val studentModel = StudentModel()
                        //db.addStudentToClass(student, classModel)
                    }
                }
            }
        }
    }
}