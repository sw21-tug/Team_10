package com.example.teachomatic3000.ui.lehrstoff

import android.app.DatePickerDialog
import android.os.Build
//import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
//import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.LehrstoffModel
import java.lang.Exception
import java.util.*

class LehrstoffFragment : androidx.fragment.app.Fragment() {

    private lateinit var lehrstoffViewModel: LehrstoffViewModel
    private lateinit var Database: DataBaseHelper


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lehrstoffViewModel =
                ViewModelProvider(this).get(LehrstoffViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lehrstoff, container, false)
        val lehrstoff_title: EditText = root.findViewById(R.id.lehrstoff_title)
        val lehrstoff_description: EditText = root.findViewById(R.id.lehrstoff_description)
        val lehrstoff_date_creation: TextView = root.findViewById(R.id.lehrstoff_date_creation)
        val lehrstoff_date_edit: TextView = root.findViewById(R.id.lehrstoff_date_edit)
        val lehrstoff_date_choice: TextView = root.findViewById(R.id.lehrstoff_date_choice)
        val lehrstoff_date_button: Button = root.findViewById(R.id.lehrstoff_date_button)
        val lehrstoff_save_button: Button = root.findViewById(R.id.lehrstoff_save_button)
        Database = DataBaseHelper(root.context)

       lehrstoff_date_creation.text = Database.getDatumHuman()
       lehrstoff_date_edit.text = Database.getDatumHuman() //im Moment noch aktuelles Systemdatum
        val test = activity?.intent?.getStringExtra("class_id")


        lehrstoff_date_button.setOnClickListener {
            // Show DatePicker
            val c = Calendar.getInstance()
            var year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            var day = c.get(Calendar.DAY_OF_MONTH)

            if(!lehrstoff_date_choice.text.isEmpty()){
                var oldDate = lehrstoff_date_choice.text.split(".")
                day = oldDate[0].toInt()
                month = oldDate[1].toInt() - 1
                year = oldDate[2].toInt()
            }


            val datePicker = DatePickerDialog(this.requireContext(), DatePickerDialog.OnDateSetListener
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
                lehrstoff_date_choice.text = stringBuilder.toString()

            }, year, month, day)
            datePicker.show()

        }

        lehrstoff_save_button.setOnClickListener {
            if(lehrstoff_title.text.isEmpty() || lehrstoff_description.text.isEmpty() || lehrstoff_date_choice.text.isEmpty() ){
                Toast.makeText(this.requireContext(),R.string.error_wrong_input, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // T-029

            var lehrstoff: LehrstoffModel

            if ((lehrstoff_title.text.length < 256) &&
                    (lehrstoff_description.text.length < 5001)){

                try {
                    val titel = String(lehrstoff_title.text.toString().toByteArray(), charset("UTF-8"))
                    val long = String(lehrstoff_description.text.toString().toByteArray(), charset("UTF-8"))

                   lehrstoff = LehrstoffModel(0, titel, long, lehrstoff_date_choice.text.toString(), lehrstoff_date_creation.text.toString(), lehrstoff_date_edit.text.toString())

                    var success = Database.addLehrstoff(lehrstoff)


                } catch (exception: Exception){
                    Toast.makeText(root.context,R.string.error_add_lehrstoff, Toast.LENGTH_SHORT).show()
                }

                } else (
                    Toast.makeText(root.context,R.string.error_long_input, Toast.LENGTH_SHORT).show()
                    )

            lehrstoff_title.text.clear()
            lehrstoff_description.text.clear()
        }


        return root
    }



}