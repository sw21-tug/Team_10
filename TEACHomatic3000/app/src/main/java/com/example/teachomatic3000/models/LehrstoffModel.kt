package com.example.teachomatic3000.models

class LehrstoffModel {


    var LehrstoffID: Int
    var LehrstoffTitel: String
    var LehrstoffLangtext: String
    var LehrstoffDatum: String
    var ErstellDatum: String
    var Bearbeitungsdatum: String


       constructor(LehrstoffID: Int, LehrstoffTitel: String, LehrstoffLangtext: String, LehrstoffDatum: String,
                ErstellDatum: String, Bearbeitungsdatum: String ) {

        this.LehrstoffID = LehrstoffID
        this.LehrstoffTitel = LehrstoffTitel
        this.LehrstoffLangtext = LehrstoffLangtext
        this.LehrstoffDatum = LehrstoffDatum
        this.ErstellDatum = ErstellDatum
        this.Bearbeitungsdatum = Bearbeitungsdatum


    }

    override fun toString(): String {

        return "LehrstoffModel(LehrstoffID='$LehrstoffID', LehrstoffTitel='$LehrstoffTitel', " +
                "LehrstoffLangtext='$LehrstoffLangtext', LehrstoffDatum='$LehrstoffDatum', " +
                "ErstellDatum='$ErstellDatum', Bearbeitungsdatum='$Bearbeitungsdatum')"
    }
}