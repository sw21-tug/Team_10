package com.example.teachomatic3000

import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.StudentModel

import org.junit.Test
import org.junit.Assert.*

class DatabaseTest {

    @Test
    fun testDatabaseExisting() {
        val db = DataBaseHelper(null)
        assertEquals(db::class, DataBaseHelper::class)
    }

    @Test
    fun testAddStudent() {
        var db = DataBaseHelper (null)
        val first_name = "Max"
        val last_name = "Mustermann"
        val student = StudentModel(first_name, last_name)
        var success = db.addStudent(student)
        assertEquals(0, success)
    }

}