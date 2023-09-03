package com.example.pertemuan2_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pertemuan2_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var isNewValue = true
    private var oldNumber = ""
    private var newNumber = ""
    private var opr = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
    // Fungsi untuk mengatur OnClickListener pada tombol angka
    fun inputNumber(view: View) {
        with(binding) {
            if (isNewValue) {
                txtMain.text = ""
            }
            val btnClick = txtMain.text.toString() + (view as Button).text.toString()
            txtMain.text = btnClick
            isNewValue = false
        }
    }

    // Fungsi untuk mengatur OnClickListener pada tombol operator
    fun inputOpr(view: View) {
        with(binding) {
            isNewValue = true
            oldNumber = txtMain.text.toString()
            opr = (view as Button).text.toString()
            txtOpr.text = oldNumber + opr
        }
    }

    // Fungsi untuk menghitung hasil
    fun calResult(view: View) {
        with(binding) {
            newNumber = txtMain.text.toString()
            var result = 0.0

            when (opr) {
                "/" -> result = oldNumber.toDouble() / newNumber.toDouble()
                "*" -> result = oldNumber.toDouble() * newNumber.toDouble()
                "+" -> result = oldNumber.toDouble() + newNumber.toDouble()
                "-" -> result = oldNumber.toDouble() - newNumber.toDouble()
            }
            if (result % 1.0 == 0.0) {
                var res = result.toInt()
                txtMain.text = res.toString()
            } else {
                txtMain.text = result.toString()
            }
            txtOpr.text = txtOpr.text.toString() + newNumber.toString()
        }
    }

    fun clearCal(view: View) {
        with(binding) {
            isNewValue = true
            txtMain.text = "0"
            txtOpr.text = ""
        }
    }
}
