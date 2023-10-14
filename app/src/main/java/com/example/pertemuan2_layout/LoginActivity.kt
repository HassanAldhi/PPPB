package com.example.pertemuan2_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pertemuan2_layout.MainActivity.Companion.EXTRA_MAIL
import com.example.pertemuan2_layout.MainActivity.Companion.EXTRA_NAME
import com.example.pertemuan2_layout.MainActivity.Companion.EXTRA_PASSWORD
import com.example.pertemuan2_layout.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)?: ""
        val mail = intent.getStringExtra(EXTRA_MAIL)?: ""
        val password = intent.getStringExtra(EXTRA_PASSWORD)?: ""

        with(binding) {
            btnLogin.setOnClickListener {
                val getname = edtEmailorUsername.text.toString()
                val getpassword = edtPasswd.text.toString()

                if (getname == name || getname == mail){
                    val intentToDashboardActivity =
                        Intent(this@LoginActivity,
                        DashboardActivity::class.java)
                    startActivity(intentToDashboardActivity)
                }else{
                    Toast.makeText(this@LoginActivity, "Username atau password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}