package com.example.teachomatic3000

import androidx.test.platform.app.InstrumentationRegistry
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.ClassModel
import com.example.teachomatic3000.models.LehrstoffModel
import com.example.teachomatic3000.models.StudentModel

import org.junit.Test
import org.junit.Assert.*

class DatabaseTests {

    @Test
    fun testDatabaseExisting() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        assertEquals(db::class, DataBaseHelper::class)
    }

    @Test
    fun testAddStudent() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student = StudentModel(0,"Peter","Hans")
        val success = db.addStudent(student)
        assertEquals(true, success)
    }

    @Test
    fun testStudentsTableSize() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        // check current students
        val current_students = db.getStudents().size
        // add one more student to db
        val student1 = StudentModel(0,"Peter","Hans")
        db.addStudent(student1)
        val students_plus_one = db.getStudents()
        assertEquals(current_students+1, students_plus_one.size)
    }


    @Test
    fun addClassSuccess() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val new_class = ClassModel(0, "4a")
        val success = db.addClass(new_class)
        assertEquals(true, success)
    }

    @Test
    fun testClassTableSize() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        // check current classes
        val current_classes = db.getClasses().size
        // add one more classes to db
        val class1 = ClassModel(0,"4b")
        db.addClass(class1)
        val classes_plus_one = db.getClasses()
        assertEquals(current_classes+1, classes_plus_one.size)
    }

    @Test
    fun testAddLehrstoffElements() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val Lehrstoff = LehrstoffModel(0,"Programmieren", "Small Basic Grundlagen werden erarbeitet",
            "2021-04-28", "2021-04-28", "2021-04-29")
        val success = db.addLehrstoff(Lehrstoff)
        assertEquals(true, success) }

    @Test
    fun testAnonymization() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student = StudentModel(0,"Markus","Müller")
        db.addStudent(student)
        val student_before = db.getStudents()[db.getStudents().size-1]

        assertEquals("" + db.getStudents().size + " Markus Müller", student_before)
        db.anonymizeCurrentStudents()
        val normalstudent = db.getStudents()[0]
        val anonymized = db.getStudents()[0]
        assertEquals(normalstudent, anonymized)
    }

    @Test
    fun testSCTableSize() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student = StudentModel(0,"Markus","Müller")
        val classModel = ClassModel(0, "2a")
        db.addStudent(student)
        db.addClass(classModel)

        val current_students = db.getStudentsOfClass().size
        db.addStudentToClass(student, classModel)

        val students_plus_one = db.getStudentsOfClass().size
        assertEquals(current_students + 1, students_plus_one)
    }
}