package com.example.pertemuan2_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.pertemuan2_layout.databinding.ActivityLoginBinding
import com.example.pertemuan2_layout.databinding.ActivityPlanBinding

class PlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanBinding
    private var selectedKotaAsal = ""
    private var selectedStasiunAsal = ""
    private var selectedKotaTujuan = ""
    private var selectedStasiunTujuan = ""
    private var totalHarga = 0
    companion object {
        const val EXTRA_KOTA_ASAL= "extra_kotaAsal";
        const val EXTRA_STASIUN_ASAL= "extra_stasiunAsal";
        const val EXTRA_KOTA_TUJUAN= "extra_kotaTujuan";
        const val EXTRA_STASIUN_TUJUAN= "extra_stasiunTujuan";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kotaAsal = arrayOf("Yogyakarta", "Solo",
        "Magelang")

        with (binding)
        {
            btnBack.setOnClickListener {
                val intentToDashboardActivity =
                    Intent(this@PlanActivity,
                        DashboardActivity::class.java)
                startActivity(intentToDashboardActivity)
            }
            // Spinner Kota Asal
            val adapterKotaAsal = ArrayAdapter<String>(
                this@PlanActivity,
                android.R.layout.simple_spinner_item,
                kotaAsal
            )
            spinnerKotaAsal.adapter = adapterKotaAsal

            spinnerKotaAsal.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long) {
                        selectedKotaAsal = kotaAsal[position]

                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
        }
    }
}