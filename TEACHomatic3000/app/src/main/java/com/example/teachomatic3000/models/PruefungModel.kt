package com.example.teachomatic3000.models

class PruefungModel {

    var PruefungID: Int
    var PruefungDatum: String
    var PruefungLangtext: String
    var PruefungArt: String
    var PruefungKlasseID: Int


    constructor(PruefungID: Int, PruefungKlasseID: Int, PruefungDatum: String, PruefungLangtext: String, PruefungArt: String) {

        this.PruefungID = PruefungID
        this.PruefungKlasseID = PruefungKlasseID
        this.PruefungDatum = PruefungDatum
        this.PruefungLangtext = PruefungLangtext
        this.PruefungArt = PruefungArt
    }

    override fun toString(): String {

        return "PruefungModel(PruefungID='$PruefungID', PruefungKlasseID='$PruefungKlasseID', PruefungDatum='$PruefungDatum', " +
                "PruefungLangtext='$PruefungLangtext', PruefungArt='$PruefungArt')"
    }
}