package com.example.teachomatic3000.ui.students

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.StudentModel

class EditStudent: AppCompatActivity() {

    private lateinit var dataBaseHelper: DataBaseHelper
    private lateinit var button_save_edit_student: Button
    private lateinit var edit_text_fn: EditText
    private lateinit var edit_text_ln: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_student)
        dataBaseHelper = DataBaseHelper(this.baseContext)
        val student_ID = intent.getStringExtra("student_id")
        val student_FN = intent.getStringExtra("firstname")
        val student_LN = intent.getStringExtra("lastname")

        button_save_edit_student = findViewById(R.id.btnSaveEditStudent)
        edit_text_fn = findViewById(R.id.eTEditStudentFirstname)
        edit_text_ln = findViewById(R.id.eTEditStudentLastName)

        edit_text_fn.setText(student_FN).toString()
        edit_text_ln.setText(student_LN).toString()

        button_save_edit_student.setOnClickListener {
            if ((edit_text_fn.text.isNotEmpty() && edit_text_fn.text.length < 256) &&
                    (edit_text_ln.text.isNotEmpty() && edit_text_ln.text.length < 256)){

                try {
                    val first = String(edit_text_fn.text.toString().toByteArray(), charset("UTF-8"))
                    val last = String(edit_text_ln.text.toString().toByteArray(), charset("UTF-8"))
                    if (student_ID != null) {
                        dataBaseHelper.editStudent(student_ID, first, last)
                        finish()
                    }
                } catch (exception: Exception){
                    Toast.makeText(this.baseContext, R.string.error_edit_student, Toast.LENGTH_SHORT).show()
                }
            }
            else if (edit_text_fn.text.toString().equals("Anonymisiert") && edit_text_ln.text.isEmpty()) {
                try {
                 if (student_ID != null) {
                        dataBaseHelper.editStudent(student_ID, "Anonymisiert", "")
                        finish()
                    }
                } catch (exception: Exception){
                    Toast.makeText(this.baseContext, R.string.error_edit_student, Toast.LENGTH_SHORT).show()
                }
            }
            else (
                    Toast.makeText(this.baseContext,R.string.error_input, Toast.LENGTH_SHORT).show()
                )
        }
    }
}