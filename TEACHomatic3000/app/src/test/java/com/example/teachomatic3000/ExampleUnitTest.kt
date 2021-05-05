package com.example.teachomatic3000

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /*
    @Test
    fun student_Test() {
        // hier kann ich auf alle klassen zugreifen
        val student = StudentModel("Vorname", "Nachname")
            assertEquals(student.vorname, "vorname")
    }
    */
    // F0: Wenn "Settings" geklickt -> Zeige leeren view (mit button "Datum einstellen")
    // F1: man kann auswählen welcher Tag ist
    // F2: Button zum auswählen des Tags. Wenn Eingabe = 0, system Uhrzeit

    // Wir brauchen "Einstellungen", in denen wir Datum einstellen können
    // In der "ui" ein Fragment... oder einen view, für das Datum
    // im mainactivity.kt einen eventlistener für calendar

    //Navigation Drawer zeigt "Datum"

}
