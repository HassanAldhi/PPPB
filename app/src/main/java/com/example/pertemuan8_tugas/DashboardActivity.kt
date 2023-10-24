package com.example.pertemuan8_tugas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.pertemuan8_tugas.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Dashboard"
        supportActionBar?.show()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                val intentToMainActivity = Intent(
                    this@DashboardActivity,
                    MainActivity::class.java
                )
                startActivity(intentToMainActivity)
                finish()  // Mengakhiri DashboardActivity
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
