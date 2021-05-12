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
import com.example.teachomatic3000.models.ClassModel
import com.example.teachomatic3000.models.LehrstoffModel
import com.example.teachomatic3000.models.PruefungModel

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "teachomatic.db", null, 8) {

    val STUDENT_TABLE = "STUDENT_TABLE"
    val STUDENT_FIRSTNAME = "STUDENT_FIRSTNAME"
    val STUDENT_LASTNAME = "STUDENT_LASTNAME"
    val STUDENT_ID = "STUDENT_ID"

    val DATUM_TABLE = "DATUM_TABLE"
    val DATUM_DATUM = "DATUM_DATUM"
    val DATUM_ID = "DATUM_ID"

    val CLASS_TABLE = "CLASS_TABLE"
    val CLASS_ID = "CLASS_ID"
    val CLASS_NAME = "CLASS_NAME"

    val LEHRSTOFF_TABLE = "LEHRSTOFF_TABLE"
    val LEHRSTOFF_TITEL = "LEHRSTOFF_TITEL"
    val LEHRSTOFF_ID = "LEHRSTOFF_ID"
    val LEHRSTOFF_LANGTEXT = "LEHRSTOFF_LANGTEXT"
    val LEHRSTOFF_DATUM = "LEHRSTOFF_DATUM"
    val LEHRSTOFF_ERSTELLDATUM = "LEHRSTOFF_ERSTELLDATUM"
    val LEHRSTOFF_BEARBEITUNGSDATUM = "LEHRSTOFF_BEARBEITUNGSDATUM"

    val LANGUAGE_TABLE = "LANGUAGE_TABLE"
    val LANGUAGE_ID = "LANGUAGE_ID"
    val LANGUAGE_CODE = "LANGUAGE_CODE"

    val PRUEFUNG_TABLE = "PRUEFUNG_TABLE"
    val PRUEFUNG_ID = "PRUEFUNG_ID"
    val PRUEFUNG_LANGTEXT = "PRUEFUNG_LANGTEXT"
    val PRUEFUNG_DATUM = "PRUEFUNG_DATUM"
    val PRUEFUNG_ART = "PRUEFUNG_ART"


    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatementStudent = "CREATE TABLE $STUDENT_TABLE($STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STUDENT_FIRSTNAME TEXT, $STUDENT_LASTNAME TEXT)"
        val createTableStatementClasses = "CREATE TABLE $CLASS_TABLE($CLASS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $CLASS_NAME TEXT)"
        val createTableStatementLanguage = "CREATE TABLE $LANGUAGE_TABLE($LANGUAGE_ID INTEGER, $LANGUAGE_CODE TEXT)"
        val createTableStatementDatum = "CREATE TABLE $DATUM_TABLE($DATUM_ID INTEGER, $DATUM_DATUM TEXT)"
        val createTableStatementPruefung = "CREATE TABLE $PRUEFUNG_TABLE($PRUEFUNG_ID INTEGER PRIMARY KEY AUTOINCREMENT, $PRUEFUNG_LANGTEXT TEXT, $PRUEFUNG_DATUM TEXT, $PRUEFUNG_ART TEXT)"

        db!!.execSQL(createTableStatementDatum)

        val insertTableStatementDatum = "insert into $DATUM_TABLE ($DATUM_ID, $DATUM_DATUM) values (1, '-1')"
        val insertTableStatementLanguage = "insert into $LANGUAGE_TABLE ($LANGUAGE_ID, $LANGUAGE_CODE) values (1, 'en')"

       val createTableStatementLehrstoff = "CREATE TABLE $LEHRSTOFF_TABLE($LEHRSTOFF_ID INTEGER PRIMARY KEY AUTOINCREMENT, $LEHRSTOFF_TITEL TEXT, $LEHRSTOFF_LANGTEXT TEXT," +
                "$LEHRSTOFF_DATUM TEXT, $LEHRSTOFF_ERSTELLDATUM TEXT, $LEHRSTOFF_BEARBEITUNGSDATUM TEXT)"

        db.execSQL(insertTableStatementDatum)
        db.execSQL(createTableStatementStudent)
        db.execSQL(createTableStatementClasses)
        db.execSQL(createTableStatementLehrstoff)
        db.execSQL(createTableStatementLanguage)
        db.execSQL(insertTableStatementLanguage)
        db.execSQL(createTableStatementPruefung)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when (oldVersion) {
            1 -> {
                val createTableStatementClasses = "CREATE TABLE $CLASS_TABLE($CLASS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $CLASS_NAME TEXT)"
                db!!.execSQL(createTableStatementClasses)
            }
            2 -> {
                val createTableStatementDatum = "CREATE TABLE $DATUM_TABLE($DATUM_ID INTEGER, $DATUM_DATUM TEXT)"
                db!!.execSQL(createTableStatementDatum)
                val insertTableStatementDatum = "insert into $DATUM_TABLE ($DATUM_ID, $DATUM_DATUM) values (1, '-1')"
                db.execSQL(insertTableStatementDatum)
            }
            3 -> {

                val createTableStatementLehrstoff = "CREATE TABLE $LEHRSTOFF_TABLE($LEHRSTOFF_ID INTEGER PRIMARY KEY AUTOINCREMENT, $LEHRSTOFF_TITEL TEXT, $LEHRSTOFF_LANGTEXT TEXT," +
                        "$LEHRSTOFF_DATUM TEXT, $LEHRSTOFF_ERSTELLDATUM TEXT, $LEHRSTOFF_BEARBEITUNGSDATUM TEXT)"

                 db!!.execSQL(createTableStatementLehrstoff)
            }
            4 -> {

                val createTableStatementLanguage = "CREATE TABLE $LANGUAGE_TABLE($LANGUAGE_ID INTEGER, $LANGUAGE_CODE TEXT)"

                db!!.execSQL(createTableStatementLanguage)

                val insertTableStatementLanguage = "insert into $LANGUAGE_TABLE ($LANGUAGE_ID, $LANGUAGE_CODE) values (1, 'en')"
                db.execSQL(insertTableStatementLanguage)
            }
            7 -> {
                val createTableStatementPruefung = "CREATE TABLE $PRUEFUNG_TABLE($PRUEFUNG_ID INTEGER PRIMARY KEY AUTOINCREMENT, $PRUEFUNG_LANGTEXT TEXT, $PRUEFUNG_DATUM TEXT, $PRUEFUNG_ART TEXT)"
                db!!.execSQL(createTableStatementPruefung)
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

    fun anonymizeCurrentStudents(): Boolean{
        var db = this.writableDatabase
        val success = db.execSQL("update $STUDENT_TABLE SET " +
                "$STUDENT_FIRSTNAME = 'Anonymisiert', $STUDENT_LASTNAME = ''")
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

    fun getClass(id: Int): ClassModel? {
        var classInfo: ClassModel? = null
        var query = "SELECT * FROM $CLASS_TABLE WHERE $CLASS_ID = "+id.toString()

        val db = this.readableDatabase

        var curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                var class_id = curser.getString(0)
                var class_name = curser.getString(1)

                classInfo = ClassModel(class_id.toInt(), class_name)

            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return classInfo
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

                val classInfo = ClassModel(class_id.toInt(), class_name)
                retList.add(classInfo.toString())

            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }

    fun addLehrstoff(lehrstoff: LehrstoffModel) : Boolean {

        val db = this.writableDatabase

        var content = ContentValues()

        content.put(LEHRSTOFF_TITEL, lehrstoff.LehrstoffTitel)
        content.put(LEHRSTOFF_LANGTEXT, lehrstoff.LehrstoffLangtext)
        content.put(LEHRSTOFF_DATUM,lehrstoff.LehrstoffDatum)
        content.put(LEHRSTOFF_ERSTELLDATUM, lehrstoff.ErstellDatum)
        content.put(LEHRSTOFF_BEARBEITUNGSDATUM, lehrstoff.Bearbeitungsdatum)

        val sucess = db.insert(LEHRSTOFF_TABLE, null, content)

        if(sucess.equals(-1)) {
            return false
        }
        return true;
    }

    fun getLehrstoffe() : ArrayList<String> {
        var retList = ArrayList<String>()

        var query = "SELECT * FROM $LEHRSTOFF_TABLE"

        val db = this.readableDatabase

        var curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                var lehrstoff_id = curser.getString(0)
                var lehrstoff_title = curser.getString(1)
                var lehrstoff_description = curser.getString(2)
                var lehrstoff_datum = curser.getString(3)
                var lehrstoff_date_create = curser.getString(4)
                var lehrstoff_date_edit = curser.getString(5)

                val lehrstoffInfo = " Lehrstoff-ID: $lehrstoff_id \n Lehrstofftitel: $lehrstoff_title \n " +
                        "Lehrstoffbeschreibung: $lehrstoff_description \n Lehrstoffdatum: $lehrstoff_datum \n " +
                        "Erstelldatum: $lehrstoff_date_create \n Bearbeitungsdatum: $lehrstoff_date_edit"
                retList.add(lehrstoffInfo)

            }while (curser.moveToNext())
        }


        curser.close()
        db.close()

        return retList
    }
        fun updateLanguage(newLanguage: String): Boolean{
            var db = this.writableDatabase
            val success = db.execSQL("update $LANGUAGE_TABLE SET $LANGUAGE_CODE = '" + newLanguage + "'")
            if (success.equals(-1)){
                return false
            }
            return true
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getLanguage() : String {
            var retString = ""

            var query = "SELECT $LANGUAGE_CODE FROM $LANGUAGE_TABLE"

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


    fun addPruefung(pruefung: PruefungModel) : Boolean {

        val db = this.writableDatabase

        var content = ContentValues()

        content.put(PRUEFUNG_LANGTEXT, pruefung.PruefungLangtext)
        content.put(PRUEFUNG_DATUM,pruefung.PruefungDatum)
        content.put(PRUEFUNG_ART, pruefung.PruefungArt)

        val sucess = db.insert(PRUEFUNG_TABLE, null, content)

        if(sucess.equals(-1)) {
            return false
        }
        return true;
    }

    fun getPruefung() : ArrayList<String> {
        var retList = ArrayList<String>()

        var query = "SELECT * FROM $PRUEFUNG_TABLE"

        val db = this.readableDatabase

        var curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                var pruefung_id = curser.getString(0)
                var pruefung_datum = curser.getString(1)
                var pruefung_langtext = curser.getString(2)
                var pruefung_art = curser.getString(3)

                val pruefungInfo = "Prüfung-ID: $pruefung_id \n" +
                        "Prüfungsdatum: $pruefung_datum \n " +
                                "Prüfungsart: $pruefung_art \n " +
                                "Prüfungsstoff: $pruefung_langtext \n"
                retList.add(pruefungInfo)

            }while (curser.moveToNext())
        }


        curser.close()
        db.close()

        return retList
    }
    }
