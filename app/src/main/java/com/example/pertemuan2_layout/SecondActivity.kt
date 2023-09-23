package com.example.pertemuan2_layout

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pertemuan2_layout.MainActivity.Companion.EXTRA_MAIL
import com.example.pertemuan2_layout.MainActivity.Companion.EXTRA_NAME
import com.example.pertemuan2_layout.MainActivity.Companion.EXTRA_PHONE
import com.example.pertemuan2_layout.databinding.ActivityMainBinding
import com.example.pertemuan2_layout.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)?: ""
        val mail = intent.getStringExtra(EXTRA_MAIL)?: ""
        val phone = intent.getStringExtra(EXTRA_PHONE)?: ""

        val spannableName = SpannableString(name)
        spannableName.setSpan(ForegroundColorSpan(Color.BLUE), 0, name.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val spannableMail = SpannableString(mail)
        spannableMail.setSpan(ForegroundColorSpan(Color.BLUE), 0, mail.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val spannablePhone = SpannableString(phone)
        spannablePhone.setSpan(ForegroundColorSpan(Color.BLUE), 0, phone.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


        with (binding) {
            txtUsername.text = SpannableStringBuilder().append("Welcome ").append(spannableName)
            txtEmail.text = SpannableStringBuilder().append("Your email ").append(spannableMail).append(" has been activated")
            txtPhone.text = SpannableStringBuilder().append("Your phone ").append(spannablePhone).append(" has been registered")

            btnLogout.setOnClickListener {
                val intentToFirstActivity =
                    Intent(
                        this@SecondActivity,
                        MainActivity::class.java
                    )
                startActivity(intentToFirstActivity)
            }
        }
    }
}