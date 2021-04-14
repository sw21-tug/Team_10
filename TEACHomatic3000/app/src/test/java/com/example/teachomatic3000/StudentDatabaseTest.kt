package com.example.teachomatic3000

import com.example.teachomatic3000.database.DataBaseHelper

import org.junit.Test
import org.junit.Assert.*

class StudentDatabaseTest {

    @Test
    fun testDatabaseExisting() {
        val db = DataBaseHelper(null)
        assertEquals(db::class, DataBaseHelper::class)
    }
}