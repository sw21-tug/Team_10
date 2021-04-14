package com.example.teachomatic3000

import org.junit.Test

import org.junit.Assert.*

class StudentDatabaseTest {
    @Test
    fun DatabaseExisting() {
        val db = DatabaseHelper()
        assertEquals(db.getClass(),DatabaseHelper.class)
    }
}