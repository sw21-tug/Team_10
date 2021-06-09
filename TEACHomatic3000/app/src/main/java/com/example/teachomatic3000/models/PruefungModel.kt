package com.example.teachomatic3000.models

class PruefungModel(
    var pruefungID: Int,
    var pruefungKlasseID: Int,
    var pruefungDatum: String,
    var pruefungLangtext: String,
    var pruefungArt: String)
{
    override fun toString(): String {
        return "PruefungModel(PruefungID='$pruefungID', PruefungKlasseID='$pruefungKlasseID', PruefungDatum='$pruefungDatum', " +
                "PruefungLangtext='$pruefungLangtext', PruefungArt='$pruefungArt')"
    }
}