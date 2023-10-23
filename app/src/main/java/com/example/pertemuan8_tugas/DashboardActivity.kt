package com.example.pertemuan8_tugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan8_tugas.databinding.ActivityDashboardBinding
import com.example.pertemuan8_tugas.databinding.ActivityMainBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            val intentToMainActivity = Intent(
                this@DashboardActivity,
                MainActivity::class.java
            )
            startActivity(intentToMainActivity)
            finish()  // Mengakhiri DashboardActivity
        }
    }
}