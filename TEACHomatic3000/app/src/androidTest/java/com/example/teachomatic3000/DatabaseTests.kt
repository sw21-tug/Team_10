package com.example.teachomatic3000

import android.util.Log
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
    fun testAddStudentSucess() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student = StudentModel(0,"Peter","Hans")
        val success = db.addStudent(student)
        assertEquals(true, success)
    }

    @Test
    fun testAddStudent() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        // check current students
        val current_students = db.getStudents().size
        // add one more student to db
        val student1 = StudentModel(0,"Peter","Hans")
        db.addStudent(student1)
        val students_plus_one = db.getStudents()
        assertEquals(current_students + 1, students_plus_one.size)
    }

    @Test
    fun testAddStudents() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        // check current students
        val current_students = db.getStudents().size
        // add a few more student to db
        val student1 = StudentModel(0,"Peter","Hans")
        val student2 = StudentModel(0,"Maria","Franz")
        val student3 = StudentModel(0,"Susi","Mayer")
        val student4 = StudentModel(0,"Michi","Mayer")
        val student5 = StudentModel(0,"Heinrich","Maier")

        db.addStudent(student1)
        db.addStudent(student2)
        db.addStudent(student3)
        db.addStudent(student4)
        db.addStudent(student5)

        val students_plus_five = db.getStudents()
        assertEquals(current_students + 5, students_plus_five.size)
    }

    @Test
    fun testEditStudent() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val studentModel = StudentModel(0, "Hans", "Peter")

        db.addStudent(studentModel)
        Log.d("PLS", studentModel.studentID.toString())
        val success = db.editStudent(studentModel.studentID.toString(), "Hansi", "Peterli")

        val edited_student = db.getStudent(studentModel.studentID.toString())

        assertEquals(true, success)
        assertEquals(true, edited_student.contains("Hansi Peterli"))
    }

    @Test
    fun testAddClassSuccess() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val classModel = ClassModel(0, "4a")
        val success = db.addClass(classModel)
        assertEquals(true, success)
    }

    @Test
    fun testAddClass() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        // check current classes
        val current_classes = db.getClasses().size
        // add one more classes to db
        val class1 = ClassModel(0,"4b")
        db.addClass(class1)
        val classes_plus_one = db.getClasses()
        assertEquals(current_classes + 1, classes_plus_one.size)
    }

    @Test
    fun testAddClasses() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        // check current classes
        val current_classes = db.getClasses().size
        // add a few more classes to db
        val class1 = ClassModel(0,"4b")
        val class2 = ClassModel(0,"5a")
        val class3 = ClassModel(0,"8b")
        val class4 = ClassModel(0,"BestClassEver")
        val class5 = ClassModel(0,"wenigerCooleKlasse")

        db.addClass(class1)
        db.addClass(class2)
        db.addClass(class3)
        db.addClass(class4)
        db.addClass(class5)

        val classes_plus_five = db.getClasses()
        assertEquals(current_classes + 5, classes_plus_five.size)
    }

    @Test
    fun testUpdateDatumSucess() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val date = "2021-01-01"
        val sucess = db.updateDatum(date)
        assertEquals(true, sucess)
    }

    @Test
    fun testGetDatum() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val date = "2021-01-01"
        val sucess = db.updateDatum(date)
        val retDate = db.getDatum()
        assertEquals(true, sucess)
        assertEquals(date, retDate)
    }

    @Test
    fun testGetDatumHuman() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val date = "2000-12-12"
        db.updateDatum(date)
        val retDate = db.getDatumHuman()
        assertEquals("2000-12-12", retDate)
    }

    @Test
    fun testAddLehrstoffSucess() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val lehrstoffModel = LehrstoffModel(0,"Programmieren", "Small Basic Grundlagen werden erarbeitet",
            "2021-04-28", "2021-04-28", "2021-04-29", 9)
        val success = db.addLehrstoff(lehrstoffModel)
        assertEquals(true, success)
    }

    @Test
    fun testAddLehrstoff() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val current_lehrstoffe = db.getLehrstoffe(InstrumentationRegistry.getInstrumentation().targetContext)
        val lehrstoffModel = LehrstoffModel(0,"Programmieren", "Small Basic Grundlagen werden erarbeitet",
            "2021-04-28", "2021-04-28", "2021-04-29", 9)
        val success = db.addLehrstoff(lehrstoffModel)
        assertEquals(true, success)
        val lehrstoff_plus_one = db.getLehrstoffe(InstrumentationRegistry.getInstrumentation().targetContext)
        assertEquals(current_lehrstoffe.size + 1, lehrstoff_plus_one.size)
    }

    @Test
    fun testAddLehrstoffe() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val current_lehrstoffe = db.getLehrstoffe(InstrumentationRegistry.getInstrumentation().targetContext)

        val lehrstoffModel1 = LehrstoffModel(0, "Programmieren", "Small Basic Grundlagen werden erarbeitet",
            "2021-04-28", "2021-04-28", "2021-04-29", 9)
        val lehrstoffModel2 = LehrstoffModel(0,"Mathematik", "1 mal 1",
            "2020-04-28", "2021-05-28", "2021-04-10", 2)
        val lehrstoffModel3 = LehrstoffModel(0,"Bio", "Pflanzen benennen",
            "2021-04-20", "2021-01-28", "2021-04-11", 5)
        val lehrstoffModel4 = LehrstoffModel(0,"Turner", "Fußball",
            "2021-04-22", "2021-02-28", "2021-04-12", 7)
        val lehrstoffModel5 = LehrstoffModel(0,"Schreiben", "Grammatik",
            "2021-04-21", "2021-03-28", "2021-04-13", 8)

        db.addLehrstoff(lehrstoffModel1)
        db.addLehrstoff(lehrstoffModel2)
        db.addLehrstoff(lehrstoffModel3)
        db.addLehrstoff(lehrstoffModel4)
        db.addLehrstoff(lehrstoffModel5)

        val lehrstoff_plus_five = db.getLehrstoffe(InstrumentationRegistry.getInstrumentation().targetContext)
        assertEquals(current_lehrstoffe.size + 5, lehrstoff_plus_five.size)
    }

    @Test
    fun testAnonymizeStudentSucess() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student = StudentModel(0,"Markus","Müller")
        db.addStudent(student)
        val sucess = db.anonymizeCurrentStudents()
        assertEquals(true, sucess)
    }

    @Test
    fun testAnonymizeStudent() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student = StudentModel(0,"Markus","Müller")
        db.addStudent(student)
        val student_before = db.getStudents()[db.getStudents().size-1]

        assertEquals(true, student_before.contains("Markus Müller"))
        db.anonymizeCurrentStudents()
        val normalstudent = db.getStudents()[db.getStudents().size-1]
        assertEquals(false, normalstudent.contains("Markus Müller"))
    }

    @Test
    fun testAnonymizeStudents() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)

        val student1 = StudentModel(0,"Markus","Müller")
        val student2 = StudentModel(0,"Reini","Mayer")
        val student3 = StudentModel(0,"Susanne","Fritz")
        val student4 = StudentModel(0,"Michael","Fritzmüller")
        val student5 = StudentModel(0,"Maria","Müller")

        db.addStudent(student1)
        db.addStudent(student2)
        db.addStudent(student3)
        db.addStudent(student4)
        db.addStudent(student5)

        val student_before1 = db.getStudents()[db.getStudents().size-1]
        val student_before2 = db.getStudents()[db.getStudents().size-2]
        val student_before3 = db.getStudents()[db.getStudents().size-3]
        val student_before4 = db.getStudents()[db.getStudents().size-4]
        val student_before5 = db.getStudents()[db.getStudents().size-5]

        assertEquals(true, student_before5.contains("Markus Müller"))
        assertEquals(true, student_before4.contains("Reini Mayer"))
        assertEquals(true, student_before3.contains("Susanne Fritz"))
        assertEquals(true, student_before2.contains("Michael Fritzmüller"))
        assertEquals(true, student_before1.contains("Maria Müller"))

        db.anonymizeCurrentStudents()
        val student_after1 = db.getStudents()[db.getStudents().size-1]
        val student_after2 = db.getStudents()[db.getStudents().size-2]
        val student_after3 = db.getStudents()[db.getStudents().size-3]
        val student_after4 = db.getStudents()[db.getStudents().size-4]
        val student_after5 = db.getStudents()[db.getStudents().size-5]

        assertEquals(true, student_after1.contains("Anonymisiert"))
        assertEquals(true, student_after2.contains("Anonymisiert"))
        assertEquals(true, student_after3.contains("Anonymisiert"))
        assertEquals(true, student_after4.contains("Anonymisiert"))
        assertEquals(true, student_after5.contains("Anonymisiert"))
    }

    @Test
    fun testAnonymizationClass() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)

        //Erstelle Student und Class und ordne ihn der Class zu
        val student = StudentModel(0,"Marvin","lol")
        db.addStudent(student)
        val class1 = ClassModel(0,"4a")
        db.addClass(class1)
        db.addStudentToClass(student,class1)

        val student_of_class1 = db.getStudentsOfClass(class1)[0]
        assertEquals(true, student_of_class1.contains("Marvin lol"))

        db.anonymizeClass(class1.classId)
        val student_anonymized = db.getStudentsOfClass(class1)[0]
        assertEquals(false, student_anonymized.contains("Marvin lol"))
    }

    @Test
    fun testSCTableSize() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student = StudentModel(0,"Markus","Müller")
        val classModel = ClassModel(0, "2a")
        db.addStudent(student)
        db.addClass(classModel)

        val current_students = db.getStudentsOfClass(classModel).size
        db.addStudentToClass(student, classModel)

        val students_plus_one = db.getStudentsOfClass(classModel).size
        assertEquals(current_students + 1, students_plus_one)
    }

    @Test
    fun testSCTableContent() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student1 = StudentModel(0,"Max","Müller")
        val student2 = StudentModel(0,"Peter","Heinz")
        val student3 = StudentModel(0,"Susanne","Peters")
        val classModel = ClassModel(0, "3a")

        db.addStudent(student1)
        db.addStudent(student2)
        db.addStudent(student3)
        db.addClass(classModel)

        val current_students = db.getStudentsOfClass(classModel).size
        db.addStudentToClass(student1, classModel)
        db.addStudentToClass(student2, classModel)
        db.addStudentToClass(student3, classModel)

        val students_plus_three = db.getStudentsOfClass(classModel)
        assertEquals(current_students + 3, students_plus_three.size)

        assertEquals("Max", students_plus_three.get(students_plus_three.size-3).split(" ").get(1))

        assertEquals("Peter", students_plus_three.get(students_plus_three.size-2).split(" ").get(1))
        assertEquals("Susanne", students_plus_three.get(students_plus_three.size-1).split(" ").get(1))
    }

    @Test
    fun testaddMitarbeitsplus() {
        val db = DataBaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
        val student1 = StudentModel(0,"Markus","Müller")
        val student2 = StudentModel (0,"Julia", "Muster")
        val classModel = ClassModel(0, "3a")
        db.addStudent(student1)
        db.addStudent(student2)
        db.addClass(classModel)

        val current_students = db.getStudentsOfClass(classModel).size
        db.addStudentToClass(student1, classModel)
        db.addStudentToClass(student2, classModel)

        db.updateMitarbeitsPlus(student1, classModel)
        db.updateMitarbeitsPlus(student1, classModel)

        val afteraddMitarbeitsplus = db.getStudentsOfClass(classModel).size
        assertEquals(current_students + 2, afteraddMitarbeitsplus)

        val student1_after = db.getStudentsOfClass(classModel)[db.getStudentsOfClass(classModel).size -1]
        val student1_plus = student1_after.split(" ")[3].toInt()

        val student2_after = db.getStudentsOfClass(classModel)[db.getStudentsOfClass(classModel).size -2]
        val student2_plus = student2_after.split(" ")[3].toInt()

        assertEquals(0, student1_plus)
        assertEquals(2, student2_plus)
    }
}