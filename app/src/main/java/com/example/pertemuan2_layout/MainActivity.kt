package com.example.pertemuan2_layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.pertemuan2_layout.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private var selectedDate = ""
    private var selectedTime = ""

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val status = arrayOf("Hadir Tepat Waktu", "Sakit",
            "Terlambat", "Izin")

        with (binding) {
//          Menghubungkan data dengan tampilan Spinner
            val adapterStatus = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                status
            )
//          Menghubungkan adapter ke Spinner
            spinnerStatus.adapter = adapterStatus

            // get selected item
//          Mengatur tindakan saat memilih salah satu item dari Spinner
            spinnerStatus.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        if (status[position] != "Hadir Tepat Waktu") {
                            edtKeterangan.visibility = View.VISIBLE
                        }
                        else {
                            edtKeterangan.visibility = View.INVISIBLE
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            // mengambil tanggal (date) dan waktu (time)
            datePicker.init(
                // Menyesuaikan tanggal
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) { _, year, month, dayOfMonth ->
//              formating tanggal
                selectedDate = "$dayOfMonth/${month + 1}/$year"
            }

            // setontime punya 3 respond yaitu view, hourofDay, dan minute
            // biasanya view diganti dengan _ karena tidak diubah atau tidak dipakai
            timePickerView.setOnTimeChangedListener { _, hourOfDay, minute ->
                selectedTime = "$hourOfDay:$minute"
            }
            btnSubmit.setOnClickListener {
                val message = "Presensi Berhasil $selectedDate jam $selectedTime"
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}