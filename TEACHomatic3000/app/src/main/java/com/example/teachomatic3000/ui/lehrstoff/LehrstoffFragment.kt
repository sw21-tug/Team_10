package com.example.teachomatic3000.ui.lehrstoff

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.LehrstoffModel
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class LehrstoffFragment : androidx.fragment.app.Fragment() {

    private lateinit var db: DataBaseHelper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_lehrstoff, container, false)
        val lehrstoff_title: EditText = root.findViewById(R.id.lehrstoff_title)
        val lehrstoff_description: EditText = root.findViewById(R.id.lehrstoff_description)
        val lehrstoff_date_creation: TextView = root.findViewById(R.id.lehrstoff_date_creation)
        val lehrstoff_date_edit: TextView = root.findViewById(R.id.lehrstoff_date_edit)
        val lehrstoff_date_choice: TextView = root.findViewById(R.id.lehrstoff_date_choice)
        val lehrstoff_date_button: Button = root.findViewById(R.id.lehrstoff_date_button)
        val lehrstoff_save_button: Button = root.findViewById(R.id.lehrstoff_save_button)
        db = DataBaseHelper(root.context)

        lehrstoff_date_creation.text = db.getDatumHuman()
        lehrstoff_date_edit.text = db.getDatumHuman()

        val extras = activity?.intent?.extras
        var klasse_id = 0;
        if(null != extras){
            val value = extras.getInt("class_id")
            klasse_id = value;
        }

        if(null != extras){
            val check_edit = extras?.getBoolean("check_edit")
            if(check_edit == true)
            {
                lehrstoff_title.setText(extras.getString("title"))
                lehrstoff_description.setText(extras.getString("description"))
                lehrstoff_date_choice.text = extras.getString("date")
                lehrstoff_date_creation.text = extras.getString("date_create")
                val currentDateTime = LocalDateTime.now()
                lehrstoff_date_edit.text = currentDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                val c_id = extras.getString("class")?.toInt()
                if (c_id != null) {
                    klasse_id = c_id
                }
            }
        }

        lehrstoff_date_button.setOnClickListener {

            val c = Calendar.getInstance()
            var year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            var day = c.get(Calendar.DAY_OF_MONTH)

            if(!lehrstoff_date_choice.text.isEmpty()){
                val oldDate = lehrstoff_date_choice.text.split(".")
                day = oldDate[0].toInt()
                month = oldDate[1].toInt() - 1
                year = oldDate[2].toInt()
            }

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

                lehrstoff_date_choice.text = stringBuilder.toString()

            }, year, month, day)
            datePicker.show()
        }

        lehrstoff_save_button.setOnClickListener {
            if(lehrstoff_title.text.isEmpty() || lehrstoff_description.text.isEmpty() || lehrstoff_date_choice.text.isEmpty() ){
                Toast.makeText(this.requireContext(),R.string.error_wrong_input, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val lehrstoff: LehrstoffModel

            if ((lehrstoff_title.text.length < 256) && (lehrstoff_description.text.length < 5001)){
                try {
                    val titel = String(lehrstoff_title.text.toString().toByteArray(), charset("UTF-8"))
                    val long = String(lehrstoff_description.text.toString().toByteArray(), charset("UTF-8"))

                    if(null != extras) {
                        val check_edit = extras?.getBoolean("check_edit")

                        if(check_edit == true) {
                            val lehrtoff_id = extras!!.getInt("lehrstoff_id")
                            lehrstoff = LehrstoffModel(lehrtoff_id, titel, long, lehrstoff_date_choice.text.toString(), lehrstoff_date_creation.text.toString(), lehrstoff_date_edit.text.toString(), klasse_id)
                            db.editLehrstoff(lehrstoff)
                        } else {
                            lehrstoff = LehrstoffModel(0, titel, long, lehrstoff_date_choice.text.toString(), lehrstoff_date_creation.text.toString(), lehrstoff_date_edit.text.toString(), klasse_id)
                            db.addLehrstoff(lehrstoff)
                        }
                    } else {
                        lehrstoff = LehrstoffModel(0, titel, long, lehrstoff_date_choice.text.toString(), lehrstoff_date_creation.text.toString(), lehrstoff_date_edit.text.toString(), klasse_id)
                        db.addLehrstoff(lehrstoff)
                    }

                } catch (exception: Exception){
                    Toast.makeText(root.context,R.string.error_add_lehrstoff, Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(root.context,R.string.error_long_input, Toast.LENGTH_SHORT).show()
            }

            lehrstoff_title.text.clear()
            lehrstoff_description.text.clear()

            if(null != extras){
                activity?.finish()
            }
        }
        return root
    }
}