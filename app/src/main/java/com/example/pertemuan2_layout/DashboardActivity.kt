package com.example.pertemuan2_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan2_layout.databinding.ActivityDashboardBinding
import com.example.pertemuan2_layout.databinding.ActivityLoginBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnTambahRencana.setOnClickListener {
                val intentToPlandActivity =
                    Intent(this@DashboardActivity,
                        PlanActivity::class.java)
                startActivity(intentToPlandActivity)
            }
        }
    }
}