package eu.tutorials.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView? = null
    private var ageInMin: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPicker: Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.textSelectedDate)
        ageInMin = findViewById(R.id.ageInMins)

        btnPicker.setOnClickListener {
            launchDatePicker()
        }
    }

    private fun launchDatePicker() {
        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "selectedYear: $selectedYear, selectedMonth: ${selectedMonth + 1}, selectedDayOfMonth: $selectedDayOfMonth",
                    Toast.LENGTH_SHORT
                ).show()

                selectedDate?.text = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val myDate = sdf.parse("$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear")
                myDate.let {
                    val dateInMins = myDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate.let {
                        val currentDateInMins = currentDate.time / 60000
                        val diff = currentDateInMins - dateInMins
                        ageInMin?.text = diff.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}