package com.example.pertemuan2_layout


import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pertemuan2_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provinsi = arrayOf(
            "jawa timur",
            "jawa tengah",
            "DIY"
        )

        with(binding) {

            btnShowCalender.setOnClickListener{
                val datePicker = DatePicker()
                datePicker.show(supportFragmentManager, "date")
            }

            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                val selectedTime = "$hourOfDay:$minute"
                Toast.makeText(
                    this@MainActivity,
                    selectedTime, Toast.LENGTH_SHORT
                ).show()
            }

//            tanggal
            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ){_, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                Toast.makeText(
                    this@MainActivity,
                    selectedDate, Toast.LENGTH_SHORT
                ).show()
            }

//            dropdown
            val adapterProvinsi = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_spinner_item, provinsi)
            spinnerProvinsi.adapter = adapterProvinsi
            val countries = resources.getStringArray(R.array.countries)
            val adapterCountry = ArrayAdapter<String>(this@MainActivity,
                android.R.layout.simple_spinner_item, countries)
            spinnerCountry.adapter=adapterCountry

            spinnerCountry.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id:Long
                    ) {
                        Toast.makeText(
                            this@MainActivity,
                            countries[position], Toast.LENGTH_SHORT).show()
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
    //                    TODO("Not yet implemented")
                    }
                }
            }
    }

    override fun onDateSet(p0: android.widget.DatePicker?, p1: Int, p2: Int, p3: Int) {
        val selectedDate = "$p3/${p2 + 1}/$p1"
        Toast.makeText(
            this@MainActivity,
            selectedDate, Toast.LENGTH_SHORT
        ).show()
    }
}
