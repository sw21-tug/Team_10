package com.example.teachomatic3000.ui.classes

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
//import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
//import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.LehrstoffModel
import com.example.teachomatic3000.models.PruefungModel
import java.lang.Exception
import java.util.*
import kotlin.properties.Delegates

class PruefungErstellen : AppCompatActivity() {

    private lateinit var class_info: TextView
    private lateinit var db: DataBaseHelper
    private lateinit var pruefung_erstellen: Button
    private var class_id = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pruefungerstellen)
        val pruefungsstoff: EditText = findViewById(R.id.pruefungsstoff)
        val pruefungsdate_choice: TextView = findViewById(R.id.pruefungsdate_choice)
        val pruefungsdate_button: Button = findViewById(R.id.pruefungsdate_button)
        val pruefung_save_button: Button = findViewById(R.id.pruefung_save_button)

        // INTENT:
        val extras = this.intent?.extras

        if(null != extras){
            val value = extras.getInt("class_id")
            class_id = value;
        }
        //EOF INTENT
        db = DataBaseHelper(this.applicationContext)

        pruefungsdate_button.setOnClickListener {
            // Show DatePicker
            val c = Calendar.getInstance()
            var year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            var day = c.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog (this, DatePickerDialog.OnDateSetListener
            {view, manualYear, manualMonth, manualDay ->

                // create string for the manual date
                val stringBuilder = StringBuilder()
                if (manualDay < 10)
                {
                    stringBuilder.append("0").append(manualDay).append(".")
                }
                else
                {
                    stringBuilder.append(manualDay).append(".")
                }

                if (manualMonth+1 < 10)
                {
                    stringBuilder.append("0").append(manualMonth+1).append(".")
                }
                else
                {
                    stringBuilder.append(manualMonth+1).append(".")
                }
                stringBuilder.append(manualYear)
                pruefungsdate_choice.text = stringBuilder.toString()

            }, year, month, day)

            datePicker.show()

        }

        pruefung_save_button.setOnClickListener {
            if(pruefungsstoff.text.isEmpty() || pruefungsdate_choice.text.isEmpty()){
                Toast.makeText(this.baseContext,R.string.error_wrong_input, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var pruefung: PruefungModel

            val radioGroup: RadioGroup = findViewById(R.id.radioGroup1)
            val activeRadioButton: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
            var pruefungsart = activeRadioButton.text.toString()
            if (pruefungsstoff.text.length < 5001){

                try {
                    val pruefungdescription = String(pruefungsstoff.text.toString().toByteArray(), charset("UTF-8"))

                    pruefung = PruefungModel (0, class_id, pruefungsstoff.text.toString(), pruefungsdate_choice.text.toString(), pruefungsart)

                    var success = db.addPruefung(pruefung)


                } catch (exception: Exception){
                    Toast.makeText(this.baseContext,"Prüfung kann nicht hinzugefügt werden", Toast.LENGTH_SHORT).show()
                }

            } else (
                    Toast.makeText(this.baseContext,R.string.error_long_input, Toast.LENGTH_SHORT).show()
                    )

            pruefungsstoff.text.clear()
        }
    }

}