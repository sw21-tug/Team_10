package com.example.teachomatic3000.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.teachomatic3000.MainActivity
import com.example.teachomatic3000.models.ClassModel
import com.example.teachomatic3000.models.StudentModel
import java.util.stream.IntStream.range

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "teachomatic.db", null, 2) {

    val STUDENT_TABLE = "STUDENT_TABLE"
    val STUDENT_FIRSTNAME = "STUDENT_FIRSTNAME"
    val STUDENT_LASTNAME = "STUDENT_LASTNAME"
    val STUDENT_ID = "STUDENT_ID"

    val CLASS_TABLE = "CLASS_TABLE"
    val CLASS_ID = "CLASS_ID"
    val CLASS_NAME = "CLASS_NAME"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatementStudent = "CREATE TABLE $STUDENT_TABLE($STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STUDENT_FIRSTNAME TEXT, $STUDENT_LASTNAME TEXT)"
        val createTableStatementClasses = "CREATE TABLE $CLASS_TABLE($CLASS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $CLASS_NAME TEXT)"

        db!!.execSQL(createTableStatementStudent)
        db.execSQL(createTableStatementClasses)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when (oldVersion) {
            1 -> {
                val createTableStatementClasses = "CREATE TABLE $CLASS_TABLE($CLASS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $CLASS_NAME TEXT)"
                db!!.execSQL(createTableStatementClasses)
            }
            2 -> {
                //TODO include next version
            }
        }
    }

    fun addStudent(student: StudentModel): Boolean {
        val db = this.writableDatabase
        var content = ContentValues()

        content.put(STUDENT_FIRSTNAME, student.firstName)
        content.put(STUDENT_LASTNAME, student.lastName)

        val sucess = db.insert(STUDENT_TABLE, null, content)

        if(sucess.equals(-1)) {
            return false
        }
        return true
    }

    fun getStudents() : ArrayList<String> {
        var retList = ArrayList<String>()

        var query = "SELECT * FROM $STUDENT_TABLE"

        val db = this.readableDatabase

        var curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                var student_id = curser.getString(0)
                var first_name = curser.getString(1)
                var last_name = curser.getString(2)

                val studentInfo = "$student_id $first_name $last_name"
                retList.add(studentInfo)

            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }

    fun deleteAllStudents(): Boolean{
        var db = this.writableDatabase
        val success = db.execSQL("delete from " + STUDENT_TABLE)
        if (success.equals(-1)){
            return false
        }
        return true
    }

    fun addClass(classModel: ClassModel): Boolean {
        val db = this.writableDatabase
        var content = ContentValues()

        content.put(CLASS_NAME, classModel.class_name)

        val success = db.insert(CLASS_TABLE, null, content)

        if(success.equals(-1)) {
            return false
        }
        return true
    }

    fun getClasses() : ArrayList<String> {
        var retList = ArrayList<String>()

        var query = "SELECT * FROM $CLASS_TABLE"

        val db = this.readableDatabase

        var curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                var class_id = curser.getString(0)
                var class_name = curser.getString(1)

                val classInfo = "$class_id $class_name"
                retList.add(classInfo)

            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }
}

