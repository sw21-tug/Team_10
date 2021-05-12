package com.example.teachomatic3000
import com.example.teachomatic3000.models.PruefungModel
import org.junit.Test
import org.junit.Assert.*


class PruefungModelTest {

    @Test
    fun testPruefungModelExisting() {
        val pruefung_id = 0
        val pruefung_datum = "testdatum"
        val pruefung_langtext = "testtext"
        val pruefung_art = "prüfung"
        val pruefung = PruefungModel(pruefung_id, pruefung_datum, pruefung_langtext, pruefung_art)
        assertEquals(pruefung::class, PruefungModel::class)
    }


    @Test
    fun testCheckClassParameters() {
        val pruefung_langtext = "testpruefung"
        val pruefung_id = 0
        val pruefung_datum = "testdatum"
        val pruefung_art = "Prüfung"
        val testPruefung = PruefungModel(pruefung_id, pruefung_datum, pruefung_langtext, pruefung_art)
        assertEquals(testPruefung.PruefungID, 0)
        assertEquals(testPruefung.PruefungLangtext, "testpruefung")
    }


}