package com.example.teachomatic3000.models

class DatumModel {


        var datumID: Int
        var Datum: String

        constructor(datumID: Int, Datum: String) {
            this.datumID = datumID
            this.Datum = Datum

        }

        override fun toString(): String {
            return "DatumModel(datumID='$datumID', Datum='$Datum')"
        }

}