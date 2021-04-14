package com.example.teachomatic3000.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "teachomatic.db", null, 1) {

    val STUDENT_TABLE = "STUDENT_TABLE"
    val STUDENT_FIRSTNAME = "STUDENT_FIRSTNAME"
    val STUDENT_LASTNAME = "STUDENT_LASTNAME"
    val STUDENT_ID = "STUDENT_ID"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE $STUDENT_TABLE($STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STUDENT_FIRSTNAME TEXT, $STUDENT_LASTNAME TEXT)"

        db!!.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
