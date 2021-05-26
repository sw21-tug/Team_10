package com.example.teachomatic3000.ui.classes

import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.ClassModel
import com.example.teachomatic3000.models.StudentModel
import kotlin.properties.Delegates

class ClassMitarbeitsplus : AppCompatActivity() {

    private lateinit var sus_list_of_class: ListView
    private lateinit var add_mitarbeitsplus: Button
    private lateinit var tVClassDetailsAddSusInfo: TextView
    private lateinit var adapter: ArrayAdapter<String>
    private var class_id by Delegates.notNull<Int>()
    private lateinit var db: DataBaseHelper
    private lateinit var students: List<String>
    private lateinit var classModel: ClassModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_details_add_sus)
        class_id = intent.getStringExtra("class_id")!!.toInt()
        sus_list_of_class = findViewById(R.id.sus_list_of_class)
        add_mitarbeitsplus = findViewById(R.id.btnAddSusToClass)
        tVClassDetailsAddSusInfo = findViewById(R.id.tVClassDetailsAddSusInfo)

        db = DataBaseHelper(this.baseContext)

        classModel = db.getClass(class_id)!!

        students = db.getStudentsOfClass(classModel)

        tVClassDetailsAddSusInfo.text = classModel.toString()

        adapter = ArrayAdapter(this.baseContext, android.R.layout.simple_list_item_multiple_choice, students)
        sus_list_of_class.adapter = adapter
        adapter.notifyDataSetChanged()

        sus_list_of_class.setOnItemClickListener { adapterView, view, i, l ->
            adapter.notifyDataSetChanged()
        }

        add_mitarbeitsplus.setOnClickListener {
            val position: SparseBooleanArray = sus_list_of_class.checkedItemPositions
            val count = sus_list_of_class.count
            var item = count - 1

            val all_students = db.getStudentsOfClass(classModel)

            while (item >= 0) {
                if (position.get(item)) {

                    val student = db.getStudent(students.get(item).split(" ")[0])
                    var flag = false

                    for(s in all_students) {
                        if(s.equals(student)) {
                            flag = true
                        }
                    }

                    if(!flag) {
                        val studentModel: StudentModel
                        val parts = students.get(item).split(" ")

                        if(parts.size == 2) {
                            studentModel = StudentModel(parts[0].toInt(), parts[1], "")
                        } else {
                            studentModel = StudentModel(parts[0].toInt(), parts[1] , parts[2])
                        }
                        db.updateMitarbeitsPlus(studentModel, classModel)
                    }
                }
                item--
            }
            finish()
        }
    }

}