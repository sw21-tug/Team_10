package com.example.teachomatic3000.ui.Einstellungen

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.example.teachomatic3000.database.DataBaseHelper
import com.example.teachomatic3000.models.StudentModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EinstellungenFragment : Fragment() {

    private lateinit var einstellungenViewModel: EinstellungenViewModel
    private lateinit var einstellungenDatabase: DataBaseHelper
    //private lateinit var datumListAdapter: ArrayAdapter<String>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        einstellungenViewModel =
                ViewModelProvider(this).get(EinstellungenViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_einstellungen, container, false)
        //val textView: TextView = root.findViewById(R.id.text_datum)
        einstellungenViewModel.text.observe(viewLifecycleOwner, Observer {
          //  textView.text = it
                    })

        einstellungenDatabase = DataBaseHelper(root.context)




        // -------------------------------------
        // *** ADDED CODE BEGIN FOR T-012 ***
        // -------------------------------------

        // define text view to show current date
        val helper: TextView = root.findViewById(R.id.helper)
        val automaticDate = LocalDateTime.now()
        val anonymizer: Button = root.findViewById(R.id.anonymizer)

        //if - wenn in der Datenbank - Datenbankabfrage
        //if(wenn datenbank value 1 )
        // dann helper.text = Wert aus datenbank
        //else


        helper.text  = automaticDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()
        helper.text = einstellungenDatabase.getDatumHuman()
        // import switch
        val dateRegulator: Switch = root.findViewById(R.id.date_regulator)
        if(einstellungenDatabase.getDatum()!="-1"){
            dateRegulator.toggle()
        }

        dateRegulator.setOnCheckedChangeListener(){_,isChecked->

            // variable for storing the current data as a string
            val currentDate:String

            // if switch is turned off, use automatic date from phone
            if (!isChecked)
            {
                //val automaticDate = LocalDateTime.now()
                //currentDate = automaticDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()

                //helper.text = currentDate
                einstellungenDatabase.updateDatum("-1")
                helper.text = einstellungenDatabase.getDatumHuman()
            }

            // if switch is turned on, user can choose date manually
            else
            {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                // show a calendar, where you can pick the date manually
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

                    //helper.text = stringBuilder.toString()
                    einstellungenDatabase.updateDatum(stringBuilder.toString())
                    helper.text = einstellungenDatabase.getDatumHuman()



                }, year, month, day)

                // when cancel is clicked, reset the switch to 0 and show automatic date
                datePicker.setOnCancelListener{

                    dateRegulator.toggle()

                    //val automaticDate = LocalDateTime.now()
                    //helper.text = automaticDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()
                    einstellungenDatabase.updateDatum("-1")
                    helper.text = einstellungenDatabase.getDatumHuman()

                }
                datePicker.show()



                currentDate = helper.text.toString()

              //  val datum: DatumModel = DatumModel(0, currentDate)
                //datumDatabase.addDatum(datum)


            }
        }

        anonymizer.setOnClickListener(){
           // var student: StudentModel
           // student = StudentModel()
            einstellungenDatabase.anonymizeCurrentStudents()
            Toast.makeText(root.context, "Schülernamen wurden anonymisiert!",Toast.LENGTH_LONG).show()
        }

        // -----------------------------------
        // *** ADDED CODE END T-012 ***
        // ----------------------------------

        return root
    }
}