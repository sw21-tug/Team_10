package com.example.teachomatic3000.models

class LehrstoffModel(
    var lehrstoffID: Int,
    var lehrstoffTitel: String,
    var lehrstoffLangtext: String,
    var lehrstoffDatum: String,
    var erstellDatum: String,
    var bearbeitungsDatum: String,// Foreign key for class_id
    var lehrstoff_f_Klasse: Int)
{
    override fun toString(): String {
        return "LehrstoffModel(LehrstoffID='$lehrstoffID', LehrstoffTitel='$lehrstoffTitel', " +
                "LehrstoffLangtext='$lehrstoffLangtext', LehrstoffDatum='$lehrstoffDatum', " +
                "ErstellDatum='$erstellDatum', Bearbeitungsdatum='$bearbeitungsDatum', Lehrstoff_f_Klasse='$lehrstoff_f_Klasse')"
    }
}