package com.example.pertemuan2_layout

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.example.pertemuan2_layout.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_MAIL = "extra_mail"
        const val EXTRA_PASSWORD = "extra_password"
    }
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with (binding) {

            edtTanggalLahir.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    this@MainActivity,
                    { _, year, monthOfYear, dayOfMonth ->
                        val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        edtTanggalLahir.setText(dat)
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()
            }

            btnRegister.setOnClickListener {
                val name = edtUsername.text.toString()
                val mail = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                // Ambil tanggal lahir dari EditText dan konversi ke Calendar
                val birth = edtTanggalLahir.text.toString()
                val birthCalendar = Calendar.getInstance()
                val birthDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(birth)
                birthCalendar.time = birthDate

                // Hitung selisih umur dalam hari
                val currentDate = Calendar.getInstance()
                val ageInMillis = currentDate.timeInMillis - birthCalendar.timeInMillis
                val ageInDays = TimeUnit.MILLISECONDS.toDays(ageInMillis).toInt()

                // Cek umur
                val minimumAgeInDays = 15 * 365 // Minimum umur 15 tahun dalam hari
                if (ageInDays < minimumAgeInDays) {
                    Toast.makeText(this@MainActivity,
                        "Umur tidak mencukupi (kurang dari 15 tahun)",
                        Toast.LENGTH_SHORT).show()
                } else if (name == null || mail == null || password == null || birth == null ){
                    Toast.makeText(this@MainActivity,
                        "Informasi anda belum terisi lengkap",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // Pindah ke aktivitas login
                    val intentToLoginActivity = Intent(this@MainActivity, LoginActivity::class.java)
                    intentToLoginActivity.putExtra(EXTRA_NAME, name)
                    intentToLoginActivity.putExtra(EXTRA_MAIL, mail)
                    intentToLoginActivity.putExtra(EXTRA_PASSWORD, password)
                    startActivity(intentToLoginActivity)
                }
            }

        }
    }
}