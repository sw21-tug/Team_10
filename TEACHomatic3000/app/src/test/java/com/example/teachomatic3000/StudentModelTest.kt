package com.example.teachomatic3000

import com.example.teachomatic3000.models.StudentModel
import org.junit.Test
import org.junit.Assert.*

class StudentModelTest {

    @Test
    /**
     * Tests if StudentModelClass is created
     */
    fun testStudenModelExisting() {
        val studentID = 0
        val first_name = "Max"
        val last_name = "Mustermann"
        val student = StudentModel(studentID, first_name, last_name)
        assertEquals(student::class, StudentModel::class)
    }

    @Test
    /**
     * Tests if StudentModel members are set correctly
     */
    fun testStudenModelMembers() {
        val studentID = 0
        val first_name = "Max"
        val last_name = "Mustermann"
        val student = StudentModel(studentID, first_name, last_name)

        assertEquals(studentID, student.studentID)
        assertEquals(first_name, student.firstName)
        assertEquals(last_name, student.lastName)
    }
}