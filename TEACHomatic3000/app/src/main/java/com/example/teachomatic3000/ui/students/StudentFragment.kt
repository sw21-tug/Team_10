package com.example.teachomatic3000.ui.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.StudentModel
import com.example.teachomatic3000.ui.home.StudentViewModel
import java.lang.Exception

class StudentFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var btnSaveStudent: Button
    private lateinit var eTStudentFirstName: EditText
    private lateinit var eTStudentLastName: EditText
    private lateinit var studentList: ListView
    private lateinit var studentDatabase: DataBaseHelper
    private lateinit var studentListAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentViewModel =
            ViewModelProvider(this).get(StudentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_students, container, false)

        btnSaveStudent = root.findViewById(R.id.btnSaveStudent)
        eTStudentFirstName = root.findViewById(R.id.eTStudentFirstName)
        eTStudentLastName = root.findViewById(R.id.eTStudentLastName)
        studentList = root.findViewById(R.id.studentList)
        studentDatabase = DataBaseHelper(root.context)

        fun updateStudentList(){
            studentListAdapter = ArrayAdapter<String>(root.context, android.R.layout.simple_list_item_1, studentDatabase.getStudents())
            studentList.adapter = studentListAdapter
            eTStudentFirstName.text.clear()
            eTStudentLastName.text.clear()
        }

        updateStudentList()

        btnSaveStudent.setOnClickListener {
            var student: StudentModel
            //eingabe valid
            if ((eTStudentFirstName.text.isNotEmpty() && eTStudentFirstName.text.length < 256) &&
                    (eTStudentLastName.text.isNotEmpty() && eTStudentLastName.text.length < 256)){

                try {
                    val first = String(eTStudentFirstName.text.toString().toByteArray(), charset("UTF-8"))
                    val last = String(eTStudentLastName.text.toString().toByteArray(), charset("UTF-8"))
                    student = StudentModel(0, first, last)
                    var success = studentDatabase.addStudent(student)
                } catch (exception: Exception){
                    Toast.makeText(root.context,"Student cannot added to Database", Toast.LENGTH_SHORT).show()
                }

            } else (
                Toast.makeText(root.context,"Fehlerhafte Eingabe", Toast.LENGTH_SHORT).show()
            )
            updateStudentList()
        }

        return root
    }
}