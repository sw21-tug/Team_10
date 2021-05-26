package com.example.teachomatic3000.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.teachomatic3000.R
import com.example.teachomatic3000.models.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.IntStream.range

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "teachomatic.db", null, 10) {

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

    val LEHRSTOFF_F_KLASSE = "LEHRSTOFF_F_KLASSE"

    val LANGUAGE_TABLE = "LANGUAGE_TABLE"
    val LANGUAGE_ID = "LANGUAGE_ID"
    val LANGUAGE_CODE = "LANGUAGE_CODE"

    val PRUEFUNG_TABLE = "PRUEFUNG_TABLE"
    val PRUEFUNG_ID = "PRUEFUNG_ID"
    val PRUEFUNGKLASSEID = "PRUEFUNGKLASSEID"
    val PRUEFUNG_LANGTEXT = "PRUEFUNG_LANGTEXT"
    val PRUEFUNG_DATUM = "PRUEFUNG_DATUM"
    val PRUEFUNG_ART = "PRUEFUNG_ART"

    val STUDENT_CLASS_TABLE = "STUDENT_CLASS_TABLE"
    val STUDENT_CLASS_ID = "STUDENT_CLASS_ID"
    val STUDENT_CLASS_F_CLASS_ID = "STUDENT_CLASS_F_CLASS_ID"
    val STUDENT_CLASS_F_SUS_ID = "STUDENT_CLASS_F_SUS_ID"
    val STUDENT_CLASS_MITARBEITSPLUS = "STUDENT_CLASS_MITARBEITSPLUS"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatementStudent = "CREATE TABLE $STUDENT_TABLE($STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STUDENT_FIRSTNAME TEXT, $STUDENT_LASTNAME TEXT)"
        val createTableStatementClasses = "CREATE TABLE $CLASS_TABLE($CLASS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $CLASS_NAME TEXT)"
        val createTableStatementLanguage = "CREATE TABLE $LANGUAGE_TABLE($LANGUAGE_ID INTEGER, $LANGUAGE_CODE TEXT)"
        val createTableStatementDatum = "CREATE TABLE $DATUM_TABLE($DATUM_ID INTEGER, $DATUM_DATUM TEXT)"
        val alterTableLehrstoff = "ALTER TABLE  $LEHRSTOFF_TABLE ADD $LEHRSTOFF_F_KLASSE INTEGER"
        val alterTableMitarbeitsplus = "ALTER TABLE  ${STUDENT_CLASS_TABLE} ADD $STUDENT_CLASS_MITARBEITSPLUS INTEGER"
        val createTableStatementPruefung = "CREATE TABLE $PRUEFUNG_TABLE($PRUEFUNG_ID INTEGER PRIMARY KEY AUTOINCREMENT, $PRUEFUNGKLASSEID INTEGER, $PRUEFUNG_LANGTEXT TEXT, $PRUEFUNG_DATUM TEXT, $PRUEFUNG_ART TEXT)"
        val createTableStatementSC = "CREATE TABLE $STUDENT_CLASS_TABLE($STUDENT_CLASS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STUDENT_CLASS_F_CLASS_ID INTEGER, $STUDENT_CLASS_F_SUS_ID INTEGER)"

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
        db.execSQL(alterTableLehrstoff)
        db.execSQL(createTableStatementPruefung)
        db.execSQL(createTableStatementSC)
        db.execSQL(alterTableMitarbeitsplus)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        for (x: Int in range(oldVersion, newVersion)) {
             when (x) {
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
                5 -> {
                    val createTableStatementSC = "CREATE TABLE $STUDENT_CLASS_TABLE($STUDENT_CLASS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STUDENT_CLASS_F_CLASS_ID INTEGER, $STUDENT_CLASS_F_SUS_ID INTEGER)"
                    db!!.execSQL(createTableStatementSC)
                }
                 6 -> {
                     val alterTableLehrstoff = "ALTER TABLE  $LEHRSTOFF_TABLE ADD $LEHRSTOFF_F_KLASSE INTEGER"
                     db!!.execSQL(alterTableLehrstoff)
                 }
                7 -> {
                    val createTableStatementPruefung = "CREATE TABLE $PRUEFUNG_TABLE($PRUEFUNG_ID INTEGER PRIMARY KEY AUTOINCREMENT, $PRUEFUNGKLASSEID INTEGER, $PRUEFUNG_LANGTEXT TEXT, $PRUEFUNG_DATUM TEXT, $PRUEFUNG_ART TEXT)"
                    db!!.execSQL(createTableStatementPruefung)
                }
                 9 -> {
                     val alterTableMitarbeitsplus = "ALTER TABLE  ${STUDENT_CLASS_TABLE} ADD $STUDENT_CLASS_MITARBEITSPLUS INTEGER"
                     db!!.execSQL(alterTableMitarbeitsplus)
                 }
            }
        }
    }

    fun addStudent(student: StudentModel): Boolean {
        val db = this.writableDatabase

        val content = ContentValues()
        content.put(STUDENT_FIRSTNAME, student.firstName)
        content.put(STUDENT_LASTNAME, student.lastName)

        val sucess = db.insert(STUDENT_TABLE, null, content)

        if(sucess.equals(-1)) {
            return false
        }

        student.studentID = sucess.toInt()
        return true
    }

    fun getStudent(id: String) : String {
        var student = String()

        val query = "SELECT * FROM $STUDENT_TABLE WHERE $STUDENT_ID = $id"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            val student_id = curser.getString(0)
            val first_name = curser.getString(1)
            val last_name = curser.getString(2)

            student = "$student_id $first_name $last_name"
        }

        curser.close()
        db.close()

        return student
    }

    fun getStudents() : ArrayList<String> {
        val retList = ArrayList<String>()

        val query = "SELECT * FROM $STUDENT_TABLE"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                val student_id = curser.getString(0)
                val first_name = curser.getString(1)
                val last_name = curser.getString(2)

                val studentInfo = "$student_id $first_name $last_name"
                retList.add(studentInfo)

            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }

    fun anonymizeCurrentStudents(): Boolean{
        val db = this.writableDatabase

        val success = db.execSQL("update $STUDENT_TABLE SET " +
                "$STUDENT_FIRSTNAME = 'Anonymisiert', $STUDENT_LASTNAME = ''")

        if (success.equals(-1)){
            return false
        }
        return true
    }

    fun addClass(classModel: ClassModel): Boolean {
        val db = this.writableDatabase

        val content = ContentValues()
        content.put(CLASS_NAME, classModel.className)

        val success = db.insert(CLASS_TABLE, null, content)

        if(success.equals(-1)) {
            return false
        }

        classModel.classId = success.toInt()
        return true
    }

    fun getClass(id: Int): ClassModel? {
        var classInfo: ClassModel? = null
        val query = "SELECT * FROM $CLASS_TABLE WHERE $CLASS_ID = $id"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                val class_id = curser.getString(0)
                val class_name = curser.getString(1)

                classInfo = ClassModel(class_id.toInt(), class_name)

            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return classInfo
    }

    fun getClasses() : ArrayList<String> {
        val retList = ArrayList<String>()

        val query = "SELECT * FROM $CLASS_TABLE"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                val class_id = curser.getString(0)
                val class_name = curser.getString(1)

                val classInfo = ClassModel(class_id.toInt(), class_name)
                retList.add(classInfo.toString())

            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }

    fun addStudentToClass(student: StudentModel, classModel: ClassModel) : Boolean{
        val db = this.writableDatabase

        val content = ContentValues()
        content.put(STUDENT_CLASS_F_CLASS_ID, classModel.classId)
        content.put(STUDENT_CLASS_F_SUS_ID, student.studentID)

        val success = db.insert(STUDENT_CLASS_TABLE, null, content)

        if(success.equals(-1)) {
            return false
        }
        return true
    }

    fun getStudentsOfClass(classModel: ClassModel) : List<String> {
        val retList = ArrayList<String>()

        val query = "SELECT * FROM $STUDENT_CLASS_TABLE WHERE $STUDENT_CLASS_F_CLASS_ID = "+classModel.classId.toString()

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                val student_id = curser.getString(2)
                var studentInfo = getStudent(student_id)
                val studentMitarbeitsplus: String = " " + curser.getInt(3).toString() + " Plus"
                studentInfo = studentInfo + studentMitarbeitsplus

                retList.add(studentInfo)
            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }

    fun getDatum() : String {
        var retString = ""

        val query = "SELECT $DATUM_DATUM FROM $DATUM_TABLE"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

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

        val query = "SELECT $DATUM_DATUM FROM $DATUM_TABLE"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

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

    fun updateDatum(newDatum: String): Boolean{
        val db = this.writableDatabase

        val success = db.execSQL("update $DATUM_TABLE SET $DATUM_DATUM = '$newDatum'")

        if (success.equals(-1)){
            return false
        }
        return true
    }

    fun addLehrstoff(lehrstoff: LehrstoffModel) : Boolean {
        val db = this.writableDatabase

        val content = ContentValues()
        content.put(LEHRSTOFF_TITEL, lehrstoff.lehrstoffTitel)
        content.put(LEHRSTOFF_LANGTEXT, lehrstoff.lehrstoffLangtext)
        content.put(LEHRSTOFF_DATUM,lehrstoff.lehrstoffDatum)
        content.put(LEHRSTOFF_ERSTELLDATUM, lehrstoff.erstellDatum)
        content.put(LEHRSTOFF_BEARBEITUNGSDATUM, lehrstoff.bearbeitungsDatum)
        content.put(LEHRSTOFF_F_KLASSE, lehrstoff.lehrstoff_f_Klasse)

        val sucess = db.insert(LEHRSTOFF_TABLE, null, content)

        if(sucess.equals(-1)) {
            return false
        }
        return true;
    }

    fun getLehrstoffe(context: Context) : ArrayList<String> {
        val retList = ArrayList<String>()

        val query = "SELECT * FROM $LEHRSTOFF_TABLE"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                val lehrstoff_id = curser.getString(0)
                val lehrstoff_title = curser.getString(1)
                val lehrstoff_description = curser.getString(2)
                val lehrstoff_datum = curser.getString(3)
                val lehrstoff_date_create = curser.getString(4)
                val lehrstoff_date_edit = curser.getString(5)
                val lehrstoff_k_klasse = curser.getString(6)
                var klasse = ""

                if(lehrstoff_k_klasse.toInt() > 0) {
                    val classModel = this.getClass(lehrstoff_k_klasse.toInt())
                    klasse = context.getString(R.string.classString) + classModel?.className
                }

                val id = context.getString(R.string.lehrstoff_id)
                val titel = context.getString(R.string.lehrstoff_titel)
                val beschreibung = context.getString(R.string.lehrstoff_beschreibung)
                val datum = context.getString(R.string.lehrstoff_datum)
                val createDatum = context.getString(R.string.lehrstoff_erstelldatum)
                val bearbeitungsDatum = context.getString(R.string.lehrstoff_bearbeitungsdatum)

                val lehrstoffInfo = "$id $lehrstoff_id \n$titel $lehrstoff_title \n" +
                        "$beschreibung $lehrstoff_description \n$datum $lehrstoff_datum \n" +
                        "$createDatum $lehrstoff_date_create \n$bearbeitungsDatum $lehrstoff_date_edit \n$klasse"

                retList.add(lehrstoffInfo)

            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }

    fun getLehrstoffOnPos(pos: Int) : ArrayList<String> {
        val retList = ArrayList<String>()

        val query = "SELECT * FROM $LEHRSTOFF_TABLE WHERE $LEHRSTOFF_ID = $pos"
        val db = this.readableDatabase
        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                val lehrstoff_id = curser.getString(0)
                val lehrstoff_title = curser.getString(1)
                val lehrstoff_description = curser.getString(2)
                val lehrstoff_datum = curser.getString(3)
                val lehrstoff_date_create = curser.getString(4)
                val lehrstoff_date_edit = curser.getString(5)
                val lehrstoff_k_klasse = curser.getString(6)

                retList.add(lehrstoff_id)
                retList.add(lehrstoff_title)
                retList.add(lehrstoff_description)
                retList.add(lehrstoff_datum)
                retList.add(lehrstoff_date_create)
                retList.add(lehrstoff_date_edit)

                if(lehrstoff_k_klasse.toInt() > 0) {
                    retList.add(lehrstoff_k_klasse)
                }
            }while (curser.moveToNext())
        }

        return retList
    }

    fun getLehrstoffeForKlasse(Klasse_id: Int, context: Context) : ArrayList<String> {
        val retList = ArrayList<String>()

        val query = "SELECT * FROM $LEHRSTOFF_TABLE WHERE $LEHRSTOFF_F_KLASSE = $Klasse_id"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                val lehrstoff_id = curser.getString(0)
                val lehrstoff_title = curser.getString(1)
                val lehrstoff_description = curser.getString(2)
                val lehrstoff_datum = curser.getString(3)
                val lehrstoff_date_create = curser.getString(4)
                val lehrstoff_date_edit = curser.getString(5)
                val lehrstoff_k_klasse = curser.getString(6)
                var klasse = ""

                if(lehrstoff_k_klasse.toInt() > 0) {
                    val classModel = this.getClass(lehrstoff_k_klasse.toInt())
                    klasse = context.getString(R.string.classString) + classModel?.className
                }

                val id = context.getString(R.string.lehrstoff_id)
                val titel = context.getString(R.string.lehrstoff_titel)
                val beschreibung = context.getString(R.string.lehrstoff_beschreibung)
                val datum = context.getString(R.string.lehrstoff_datum)
                val createDatum = context.getString(R.string.lehrstoff_erstelldatum)
                val bearbeitungsDatum = context.getString(R.string.lehrstoff_bearbeitungsdatum)

                val lehrstoffInfo = "$id $lehrstoff_id \n$titel $lehrstoff_title \n" +
                        "$beschreibung $lehrstoff_description \n$datum $lehrstoff_datum \n" +
                        "$createDatum $lehrstoff_date_create \n$bearbeitungsDatum $lehrstoff_date_edit \n$klasse"

                retList.add(lehrstoffInfo)
            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }

    fun editLehrstoff(lehrstoff: LehrstoffModel): Boolean{
        val db = this.writableDatabase

        val success = db.execSQL("update $LEHRSTOFF_TABLE SET " +
                "$LEHRSTOFF_TITEL = '" + lehrstoff.lehrstoffTitel + "'"+
                ", $LEHRSTOFF_LANGTEXT = '" + lehrstoff.lehrstoffLangtext + "'"+
                ", $LEHRSTOFF_DATUM = '" + lehrstoff.lehrstoffDatum + "'"+
                ", $LEHRSTOFF_ERSTELLDATUM = '" + lehrstoff.erstellDatum + "'"+
                ", $LEHRSTOFF_BEARBEITUNGSDATUM = '" + lehrstoff.bearbeitungsDatum + "'"+
                ", $LEHRSTOFF_F_KLASSE = '" + lehrstoff.lehrstoff_f_Klasse + "'" +
                " WHERE $LEHRSTOFF_ID = " + lehrstoff.lehrstoffID.toString())

        if (success.equals(-1)){
            return false
        }
        return true
    }

    fun updateLanguage(newLanguage: String): Boolean{
        val db = this.writableDatabase

        val success = db.execSQL("update $LANGUAGE_TABLE SET $LANGUAGE_CODE = '$newLanguage'")

        if (success.equals(-1)){
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getLanguage() : String {
        var retString = ""

        val query = "SELECT $LANGUAGE_CODE FROM $LANGUAGE_TABLE"

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

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

        val content = ContentValues()
        content.put(PRUEFUNGKLASSEID, pruefung.pruefungKlasseID)
        content.put(PRUEFUNG_LANGTEXT, pruefung.pruefungLangtext)
        content.put(PRUEFUNG_DATUM,pruefung.pruefungDatum)
        content.put(PRUEFUNG_ART, pruefung.pruefungArt)

        val sucess = db.insert(PRUEFUNG_TABLE, null, content)

        if(sucess.equals(-1)) {
            return false
        }
        return true;
    }
    fun updateMitarbeitsPlus(student: StudentModel, classModel: ClassModel): Boolean{
        val db = this.writableDatabase

        val success = db.execSQL("update ${STUDENT_CLASS_TABLE} SET $STUDENT_CLASS_MITARBEITSPLUS = COALESCE( $STUDENT_CLASS_MITARBEITSPLUS, 0) + 1 WHERE $STUDENT_CLASS_F_CLASS_ID = " +
                classModel.classId.toString() + " AND $STUDENT_CLASS_F_SUS_ID = "+ student.studentID.toString())

        if (success.equals(-1)){
            return false
        }
        return true
    }

    fun getPruefung(class_id: Int, context: Context) : ArrayList<String> {
        val retList = ArrayList<String>()

        val query = "SELECT * FROM $PRUEFUNG_TABLE WHERE $PRUEFUNGKLASSEID = $class_id "

        val db = this.readableDatabase

        val curser = db.rawQuery(query, null)

        if(curser.moveToFirst()) {
            do{
                val pruefung_id = curser.getString(0)
                val pruefungklasseid = curser.getInt(1)
                val pruefung_datum = curser.getString(2)
                val pruefung_langtext = curser.getString(3)
                val pruefung_art = curser.getString(4)
                var klasse = ""

                if(pruefungklasseid > 0) {
                    val classModel = this.getClass(pruefungklasseid)
                    klasse = context.getString(R.string.classString) + classModel?.className
                }

                val id = context.getString(R.string.prüfung_id)
                val datum = context.getString(R.string.prüfung_datum)
                val art = context.getString(R.string.prüfung_art)
                val stoff = context.getString(R.string.prüfung_stoff)

                val pruefungInfo = "$id $pruefung_id \n" +
                        "$datum $pruefung_datum \n" +
                        "$art $pruefung_art \n" +
                        "$stoff $pruefung_langtext \n" + klasse

                retList.add(pruefungInfo)
            }while (curser.moveToNext())
        }

        curser.close()
        db.close()

        return retList
    }
}