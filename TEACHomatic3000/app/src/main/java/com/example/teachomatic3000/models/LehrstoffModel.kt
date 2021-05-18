package com.example.teachomatic3000.models

class LehrstoffModel {


    var LehrstoffID: Int
    var LehrstoffTitel: String
    var LehrstoffLangtext: String
    var LehrstoffDatum: String
    var ErstellDatum: String
    var Bearbeitungsdatum: String
    var Lehrstoff_f_Klasse: Int // Foreign key for class_id


       constructor(LehrstoffID: Int, LehrstoffTitel: String, LehrstoffLangtext: String, LehrstoffDatum: String,
                ErstellDatum: String, Bearbeitungsdatum: String, Lehrstoff_f_Klasse: Int) {

        this.LehrstoffID = LehrstoffID
        this.LehrstoffTitel = LehrstoffTitel
        this.LehrstoffLangtext = LehrstoffLangtext
        this.LehrstoffDatum = LehrstoffDatum
        this.ErstellDatum = ErstellDatum
        this.Bearbeitungsdatum = Bearbeitungsdatum
           this.Lehrstoff_f_Klasse = Lehrstoff_f_Klasse


    }

    override fun toString(): String {

        return "LehrstoffModel(LehrstoffID='$LehrstoffID', LehrstoffTitel='$LehrstoffTitel', " +
                "LehrstoffLangtext='$LehrstoffLangtext', LehrstoffDatum='$LehrstoffDatum', " +
                "ErstellDatum='$ErstellDatum', Bearbeitungsdatum='$Bearbeitungsdatum', Lehrstoff_f_Klasse='$Lehrstoff_f_Klasse')"
    }
}