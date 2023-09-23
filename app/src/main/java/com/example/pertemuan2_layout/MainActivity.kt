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
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.pertemuan2_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newSession = false
    companion object {
        const val EXTRA_NAME = "extra_name";
        const val EXTRA_MAIL = "extra_mail";
        const val EXTRA_PHONE = "extra_phone";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            btnRegister.setOnClickListener {
                val intentToSecondActivity =
                    Intent(
                        this@MainActivity,
                        SecondActivity::class.java
                    )
                val name = edtUsername.text.toString()
                val mail = edtEmail.text.toString()
                val phone = edtPhone.text.toString()
                intentToSecondActivity.putExtra(EXTRA_NAME, name)
                intentToSecondActivity.putExtra(EXTRA_MAIL, mail)
                intentToSecondActivity.putExtra(EXTRA_PHONE, phone)
                startActivity(intentToSecondActivity)
            }
        }
    }
}