package com.example.teachomatic3000.ui.classes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.PruefungModel
import java.util.*

class PruefungErstellen : AppCompatActivity() {

    private lateinit var db: DataBaseHelper
    private var class_id = 0

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pruefungerstellen)
        val pruefungsstoff: EditText = findViewById(R.id.pruefungsstoff)
        val pruefungsdate_choice: TextView = findViewById(R.id.pruefungsdate_choice)
        val pruefungsdate_button: Button = findViewById(R.id.pruefungsdate_button)
        val pruefung_save_button: Button = findViewById(R.id.pruefung_save_button)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup1)

        val extras = this.intent?.extras

        if(null != extras){
            val value = extras.getInt("class_id")
            class_id = value;
        }

        db = DataBaseHelper(this.applicationContext)

        if(null != extras) {
            val check_edit = extras?.getBoolean("check_edit")
            if(check_edit == true)
            {
                pruefungsstoff.setText(extras.getString("pruefung_langtext"))
                pruefungsdate_choice.text = extras.getString("pruefung_datum")

                when(extras.getString("pruefung_art")) {
                    "Schriftlicher Test" -> {
                        radioGroup.check(2131231070)
                    }
                    "Schularbeit" -> {
                        radioGroup.check(2131231071)
                    }
                    "Portfolioabgabe" -> {
                        radioGroup.check(2131231072)
                    }
                    "笔试" -> {
                        radioGroup.check(2131231070)
                    }
                    "学校工作" -> {
                        radioGroup.check(2131231071)
                    }
                    "作品集提交" -> {
                        radioGroup.check(2131231072)
                    }
                }
            }
        }

        pruefungsdate_button.setOnClickListener {
            // Show DatePicker
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog (this, DatePickerDialog.OnDateSetListener
            {
                view, manualYear, manualMonth, manualDay ->

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
                pruefungsdate_choice.text = stringBuilder.toString()

            }, year, month, day)

            datePicker.show()
        }

        pruefung_save_button.setOnClickListener {
           // val radioGroup: RadioGroup = findViewById(R.id.radioGroup1)

            if(pruefungsstoff.text.isEmpty() || pruefungsdate_choice.text.isEmpty() || radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(this.baseContext, R.string.error_add_prüfung, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val pruefung: PruefungModel

            val activeRadioButton: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
            val pruefungsart = activeRadioButton.text.toString()

            if (pruefungsstoff.text.length < 5001){
                try {
                    if(null != extras) {
                        val check_edit = extras?.getBoolean("check_edit")
                        if(check_edit == true) {
                            val pruefung_id = extras.getInt("prüfungs_id")
                            pruefung = PruefungModel (pruefung_id, class_id, pruefungsdate_choice.text.toString(), pruefungsstoff.text.toString(), pruefungsart)
                            db.editPruefung(pruefung)
                        } else {
                            pruefung = PruefungModel (0, class_id, pruefungsdate_choice.text.toString(), pruefungsstoff.text.toString(), pruefungsart)
                            db.addPruefung(pruefung)
                        }
                    }
                    else {
                        pruefung = PruefungModel (0, class_id, pruefungsdate_choice.text.toString(), pruefungsstoff.text.toString(), pruefungsart)
                        db.addPruefung(pruefung)
                    }

                } catch (exception: Exception){
                    Toast.makeText(this.baseContext, R.string.error_add_prüfung, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this.baseContext,R.string.error_long_input, Toast.LENGTH_SHORT).show()
            }

            pruefungsstoff.text.clear()
            pruefungsdate_choice.setText("")
            radioGroup.clearCheck()
            finish()
        }
    }
}