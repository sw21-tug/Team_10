package com.example.teachomatic3000.ui.Einstellungen

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.teachomatic3000.MainActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EinstellungenFragment : Fragment() {

    private lateinit var db: DataBaseHelper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_einstellungen, container, false)

        db = DataBaseHelper(root.context)

        val helper: TextView = root.findViewById(R.id.helper)
        val automaticDate = LocalDateTime.now()
        val anonymizer: Button = root.findViewById(R.id.anonymizer)

        helper.text  = automaticDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()
        helper.text = db.getDatumHuman()

        // import switch
        val dateRegulator: Switch = root.findViewById(R.id.date_regulator)
        if(db.getDatum()!="-1"){
            dateRegulator.toggle()
        }

        dateRegulator.setOnCheckedChangeListener(){_,isChecked->

            // if switch is turned off, use automatic date from phone
            if (!isChecked) {
                db.updateDatum("-1")
                helper.text = db.getDatumHuman()
            } else { // if switch is turned on, user can choose date manually
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                // show a calendar, where you can pick the date manually
                val datePicker = DatePickerDialog(this.requireContext(), DatePickerDialog.OnDateSetListener
                {view, manualYear, manualMonth, manualDay ->

                    // create string for the manual date
                    val stringBuilder = StringBuilder()
                    if (manualDay < 10) {
                        stringBuilder.append("0").append(manualDay).append(".")
                    } else {
                        stringBuilder.append(manualDay).append(".")
                    }

                    if (manualMonth+1 < 10) {
                        stringBuilder.append("0").append(manualMonth+1).append(".")
                    } else {
                        stringBuilder.append(manualMonth+1).append(".")
                    }

                    stringBuilder.append(manualYear)

                    db.updateDatum(stringBuilder.toString())
                    helper.text = db.getDatumHuman()

                }, year, month, day)

                // when cancel is clicked, reset the switch to 0 and show automatic date
                datePicker.setOnCancelListener{
                    dateRegulator.toggle()

                    db.updateDatum("-1")
                    helper.text = db.getDatumHuman()
                }
                datePicker.show()
            }
        }

        anonymizer.setOnClickListener(){
            db.anonymizeCurrentStudents()
            Toast.makeText(root.context, R.string.settings_anonymize_students, Toast.LENGTH_LONG).show()
        }

        val languageRegulator: Switch = root.findViewById(R.id.language_regulator)

        if(db.getLanguage()!="en"){
            languageRegulator.toggle()
        }

        languageRegulator.setOnCheckedChangeListener(){_,isChecked->

            // if switch is turned off, use english language
            if (!isChecked) {
                db.updateLanguage("en")
            } else { // if switch is turned on, use chinese language
                db.updateLanguage("zh")
            }

            val intent = Intent(root.context, MainActivity::class.java).apply {}
            startActivity(intent)
        }

        return root
    }
}