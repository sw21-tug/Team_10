package com.example.teachomatic3000.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.teachomatic3000.MainActivity
import com.example.teachomatic3000.models.DatumModel
import com.example.teachomatic3000.models.StudentModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "teachomatic.db", null, 3) {

    val STUDENT_TABLE = "STUDENT_TABLE"
    val STUDENT_FIRSTNAME = "STUDENT_FIRSTNAME"
    val STUDENT_LASTNAME = "STUDENT_LASTNAME"
    val STUDENT_ID = "STUDENT_ID"

    //T017
    val DATUM_TABLE = "DATUM_TABLE"
    val DATUM_DATUM = "DATUM_DATUM"
    val DATUM_ID = "DATUM_ID"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE $STUDENT_TABLE($STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STUDENT_FIRSTNAME TEXT, $STUDENT_LASTNAME TEXT)"

        db!!.execSQL(createTableStatement)

        //T017
        val createTableStatementDatum = "CREATE TABLE $DATUM_TABLE($DATUM_ID INTEGER, $DATUM_DATUM TEXT)"

        db!!.execSQL(createTableStatementDatum)

        val insertTableStatementDatum = "insert into $DATUM_TABLE ($DATUM_ID, $DATUM_DATUM) values (1, '-1')"

        db!!.execSQL(insertTableStatementDatum)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
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

    fun addDatum(datum: DatumModel): Boolean {
        val db = this.writableDatabase
        var content = ContentValues()

        content.put(DATUM_DATUM, datum.Datum)


        val sucess = db.insert(DATUM_TABLE, null, content)

        if(sucess.equals(-1)) {
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDatum() : String {
        var retString = ""

        var query = "SELECT $DATUM_DATUM FROM $DATUM_TABLE"

        val db = this.readableDatabase

        var curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                retString = curser.getString(0)


            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retString
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDatumHuman() : String {
        var retString = ""

        var query = "SELECT $DATUM_DATUM FROM $DATUM_TABLE"

        val db = this.readableDatabase

        var curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                retString = curser.getString(0)


            }while (curser.moveToNext())
        }
        if(retString == "-1"){
            val automaticDate = LocalDateTime.now()
            retString = automaticDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()
        }
        curser.close()
        db.close()

        return retString
    }

    fun deleteAllStudents(): Boolean{
        var db = this.writableDatabase
        val success = db.execSQL("delete from " + STUDENT_TABLE)
        if (success.equals(-1)){
            return false
        }
        return true
    }
    fun updateDatum(newDatum: String): Boolean{
        var db = this.writableDatabase
        val success = db.execSQL("update $DATUM_TABLE SET $DATUM_DATUM = '" + newDatum + "'")
        if (success.equals(-1)){
            return false
        }
        return true
    }
}

