package com.example.pertemuan2_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pertemuan2_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var isNewNum = true
    private var oldNumber = ""
    private var newNumber = ""
    private var opr = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            // Fungsi untuk mengatur OnClickListener pada tombol angka
            fun inputNumber(view: View) {
                if (isNewNum) {
                    txtMain.text = ""
                }
                isNewNum = false
                val btnClick = txtMain.text.toString() + (view as Button).text.toString()
                txtMain.text = btnClick
            }

            // Fungsi untuk mengatur OnClickListener pada tombol operator
            fun inputOpr(view: View) {
                isNewNum = true
                oldNumber = txtMain.text.toString()
                opr = (view as Button).text.toString()
                txtOpr.text = oldNumber + opr
            }

            // Fungsi untuk menghitung hasil
            fun calResult(view: View) {
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

            fun clearCal(view: View) {
                isNewNum = true
                txtMain.text = "0"
                txtOpr.text = ""
            }

            // Set OnClickListener untuk tombol angka
            num1.setOnClickListener { inputNumber(it) }
            num2.setOnClickListener { inputNumber(it) }
            num3.setOnClickListener { inputNumber(it) }
            num4.setOnClickListener { inputNumber(it) }
            num5.setOnClickListener { inputNumber(it) }
            num6.setOnClickListener { inputNumber(it) }
            num7.setOnClickListener { inputNumber(it) }
            num8.setOnClickListener { inputNumber(it) }
            num9.setOnClickListener { inputNumber(it) }
            num0.setOnClickListener { inputNumber(it) }

            // Set OnClickListener untuk tombol operator
            oprDivide.setOnClickListener { inputOpr(it) }
            oprMinus.setOnClickListener { inputOpr(it) }
            oprSum.setOnClickListener { inputOpr(it) }
            oprMultiply.setOnClickListener { inputOpr(it) }
            oprClear.setOnClickListener { clearCal(it) }

            // Set OnClickListener untuk tombol hasil
            result.setOnClickListener { calResult(it) }
        }
    }
}
