package com.example.teachomatic3000.ui.students

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.StudentModel
import com.example.teachomatic3000.ui.classes.ClassDetails

class StudentFragment : Fragment() {

    private lateinit var btnSaveStudent: Button
    private lateinit var eTStudentFirstName: EditText
    private lateinit var eTStudentLastName: EditText
    private lateinit var studentList: ListView
    private lateinit var studentDatabase: DataBaseHelper
    private lateinit var studentListAdapter: ArrayAdapter<String>
    private lateinit var rootcontext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_students, container, false)

        btnSaveStudent = root.findViewById(R.id.btnSaveStudent)
        eTStudentFirstName = root.findViewById(R.id.eTStudentFirstName)
        eTStudentLastName = root.findViewById(R.id.eTStudentLastName)
        studentList = root.findViewById(R.id.studentList)
        studentDatabase = DataBaseHelper(root.context)

        rootcontext = root.context

        fun updateStudentList(){
            studentListAdapter = ArrayAdapter(root.context, android.R.layout.simple_list_item_1, studentDatabase.getStudents())
            studentList.adapter = studentListAdapter
            eTStudentFirstName.text.clear()
            eTStudentLastName.text.clear()
        }

        updateStudentList()

        btnSaveStudent.setOnClickListener {
            val student: StudentModel

            if ((eTStudentFirstName.text.isNotEmpty() && eTStudentFirstName.text.length < 256) &&
                    (eTStudentLastName.text.isNotEmpty() && eTStudentLastName.text.length < 256)){

                try {
                    val first = String(eTStudentFirstName.text.toString().toByteArray(), charset("UTF-8"))
                    val last = String(eTStudentLastName.text.toString().toByteArray(), charset("UTF-8"))
                    student = StudentModel(0, first, last)
                    studentDatabase.addStudent(student)
                } catch (exception: Exception){
                    Toast.makeText(root.context,R.string.error_add_student, Toast.LENGTH_SHORT).show()
                }
            } else (
                Toast.makeText(root.context,R.string.error_input, Toast.LENGTH_SHORT).show()
            )
            updateStudentList()
        }

        studentList.setOnItemLongClickListener { parent, view, position, id ->
            val student_info = parent.getItemAtPosition(position)
            val student_parts = student_info.toString().split(" ").toTypedArray()
            val student_id = student_parts[0]
            val student_fn = student_parts[1]
            var student_ln = ""
            if (student_parts.size > 2) {
                student_ln = student_parts[2]
            }
            val intent = Intent(root.context, EditStudent::class.java).apply {
                putExtra("student_id", student_id)
                putExtra("firstname", student_fn)
                putExtra("lastname", student_ln)
            }
            startActivity(intent)
            return@setOnItemLongClickListener(true)
        }
        return root
    }
    override fun onResume() {
        super.onResume()
        studentListAdapter = ArrayAdapter(rootcontext, android.R.layout.simple_list_item_1, studentDatabase.getStudents())
        studentList.adapter = studentListAdapter
    }
}