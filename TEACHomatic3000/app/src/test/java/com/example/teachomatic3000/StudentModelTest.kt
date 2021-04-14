package com.example.teachomatic3000

import org.junit.Test
import org.junit.Assert.*

class StudentModelTest {

    @Test
    /**
     * Tests if StudentModelClass is created
     */
    fun testStudenModelExisting() {
        val first_name = "Max"
        val last_name = "Mustermann"
        val student = StudentModel(first_name, last_name)
        assertEquals(student::class, StudenModel::class)
    }

    @Test
    /**
     * Tests if StudentModel members are set correctly
     */
    fun testStudenModelMembers() {
        val first_name = "Max"
        val last_name = "Mustermann"
        val student = StudentModel(first_name, last_name)

        assertEquals(first_name, student.firstName)
        assertEquals(last_name, student.lastName)
    }
}