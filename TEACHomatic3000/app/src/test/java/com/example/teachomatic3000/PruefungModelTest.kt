package com.example.teachomatic3000
import com.example.teachomatic3000.models.PruefungModel
import org.junit.Test
import org.junit.Assert.*


class PruefungModelTest {

    @Test
    fun testPruefungModelExisting() {
        val pruefung_id = 0
        val pruefung_datum = "03012021"
        val pruefung_langtext = "testtext"
        val pruefung_art = "prüfung"
        var pruefungKlasseID = 0

        val pruefung = PruefungModel(pruefung_id, pruefungKlasseID, pruefung_datum, pruefung_langtext, pruefung_art)
        assertEquals(pruefung::class, PruefungModel::class)
    }


    @Test
    fun testCheckClassParameters() {
        val pruefung_langtext = "testpruefung"
        val pruefung_id = 0
        val pruefung_datum = "03012021"
        val pruefung_art = "Prüfung"
        val pruefungKlasseID = 0
        val testPruefung = PruefungModel(pruefung_id, pruefungKlasseID, pruefung_datum, pruefung_langtext, pruefung_art)
        assertEquals(testPruefung.PruefungID, 0)
        assertEquals(testPruefung.PruefungLangtext, "testpruefung")
    }


}