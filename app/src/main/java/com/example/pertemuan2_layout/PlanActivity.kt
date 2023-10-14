package com.example.pertemuan2_layout

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.pertemuan2_layout.databinding.ActivityLoginBinding
import com.example.pertemuan2_layout.databinding.ActivityPlanBinding
import java.util.Calendar

class PlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanBinding
    private var selectedKotaAsal = ""
    private var selectedStasiunAsal = ""
    private var selectedKotaTujuan = ""
    private var selectedStasiunTujuan = ""
    private var selectedKelas = ""
    private var selectedTanggal = ""
    private var selectedPaket = ""
    private var totalHarga = 0
    private var paket = ""
    private val tanggalRencana = mutableSetOf<String>()
    companion object {
        const val EXTRA_KOTA_ASAL= "extra_kotaAsal"
        const val EXTRA_STASIUN_ASAL= "extra_stasiunAsal"
        const val EXTRA_KOTA_TUJUAN= "extra_kotaTujuan"
        const val EXTRA_STASIUN_TUJUAN= "extra_stasiunTujuan"
        const val EXTRA_KELAS= "extra_kelas"
        const val EXTRA_TANGGAL= "extra_tanggal"
        const val EXTRA_PAKET= "extra_paket"
        const val EXTRA_HARGA= "extra_harga"
        const val EXTRA_CONTENT = "extra_Boolean"
    }
    private val hargaKotaAsal = mapOf(
        "Yogyakarta" to 2,
        "Solo" to 3,
        "Magelang" to 2
    )

    private val hargaStasiunAsal = mapOf(
        "Tugu" to 2,
        "Lempuyangan" to 1,
        "Patukan" to 3
    )

    private val hargaKotaTujuan = mapOf(
        "Bandung" to 2,
        "Jakarta" to 3,
        "Surabaya" to 1
    )

    private val hargaStasiunTujuan = mapOf(
        "Gambir" to 1,
        "Gubeng" to 2,
        "Juanda" to 3
    )

    private val hargaKelas = mapOf(
        "VVIP" to 3,
        "VIP" to 2,
        "Economy" to 1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kotaAsal = arrayOf("Yogyakarta", "Solo", "Magelang")
        val stasiunAsal = arrayOf("Tugu", "Lempuyangan", "Patukan")
        val kotaTujuan = arrayOf("Bandung", "Jakarta", "Surabaya")
        val stasiunTujuan = arrayOf("Gambir", "Gubeng", "Juanda")
        val kelas = arrayOf("VVIP", "VIP", "Economy")

        with (binding)
        {
            btnBack.setOnClickListener {
                val intentToDashboardActivity =
                    Intent(this@PlanActivity,
                        DashboardActivity::class.java)
                startActivity(intentToDashboardActivity)
            }

            // Spinner Kota Asal
            val adapterKotaAsal = ArrayAdapter<String>(this@PlanActivity,
                android.R.layout.simple_spinner_item, kotaAsal)
            spinnerKotaAsal.adapter = adapterKotaAsal
            spinnerKotaAsal.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long) {
                        selectedKotaAsal = kotaAsal[position]
                        updateTotalHarga()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            // Spinner Stasiun Asal
            val adapterStasiunAsal = ArrayAdapter<String>(this@PlanActivity,
                android.R.layout.simple_spinner_item, stasiunAsal)
            spinnerStasiunAsal.adapter = adapterStasiunAsal
            spinnerStasiunAsal.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long) {
                        selectedStasiunAsal = stasiunAsal[position]
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            // Spinner Kota Tujuan
            val adapterKotaTujuan = ArrayAdapter<String>(this@PlanActivity,
                android.R.layout.simple_spinner_item, kotaTujuan)
            spinnerKotaTujuan.adapter = adapterKotaTujuan
            spinnerKotaTujuan.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long) {
                        selectedKotaTujuan = kotaTujuan[position]
                        updateTotalHarga()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            // Spinner Stasiun Tujuan
            val adapterStasiunTujuan = ArrayAdapter<String>(this@PlanActivity,
                android.R.layout.simple_spinner_item, stasiunTujuan)
            spinnerStasiunAsal.adapter = adapterStasiunAsal
            spinnerStasiunAsal.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long) {
                        selectedStasiunTujuan = stasiunTujuan[position]
                        updateTotalHarga()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            // Spinner Stasiun Kelas
            val adapterKelas = ArrayAdapter<String>(this@PlanActivity,
                android.R.layout.simple_spinner_item, kelas)
            spinnerKelas.adapter = adapterKelas
            spinnerKelas.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long) {
                        selectedKelas = kelas[position]
                        updateTotalHarga()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            edtTanggalBerangkat.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    this@PlanActivity,
                    { _, year, monthOfYear, dayOfMonth ->
                        val date = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        edtTanggalBerangkat.setText(date)
                        selectedTanggal = date
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()
            }

            paketGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
                if (isChecked) {
                    val selectedToggle = when (checkedId) {
                        toggleSnack.id -> toggleSnack
                        toggleMinuman.id -> toggleMinuman
                        toggleMakan.id -> toggleMakan
                        toggleKursi.id -> toggleKursi
                        toggleKasur.id -> toggleKasur
                        toggleDudukDepan.id -> toggleDudukDepan
                        toggleJendela.id -> toggleJendela
                        else -> null
                    }

                    selectedToggle?.let {
                        if (paket.isNotEmpty()) {
                            paket += ", "
                        }
                        paket += it.text
                    }
                }
            }

            if (selectedStasiunTujuan == null || selectedKotaTujuan == null || selectedKotaAsal == null
                || selectedStasiunAsal == null || selectedKelas == null || selectedTanggal == null ){
                Toast.makeText(this@PlanActivity,
                    "Informasi anda belum terisi lengkap",
                    Toast.LENGTH_SHORT).show()
            } else {
                // Pindah ke aktivitas dashboard
                val content = true
                tanggalRencana.add(selectedTanggal)
                val totalHargaString = totalHarga.toString()
                val intentToDashboardActivity = Intent(this@PlanActivity, DashboardActivity::class.java)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_TANGGAL, selectedTanggal)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_KOTA_ASAL, selectedKotaAsal)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_KOTA_TUJUAN, selectedKotaTujuan)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_STASIUN_ASAL, selectedStasiunAsal)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_STASIUN_TUJUAN, selectedStasiunTujuan)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_KELAS, selectedKelas)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_TANGGAL, selectedTanggal)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_HARGA, totalHargaString)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_PAKET, paket)
                intentToDashboardActivity.putExtra(PlanActivity.EXTRA_CONTENT, content)
                intentToDashboardActivity.putStringArrayListExtra("TANGGAL_RENCANA", ArrayList(tanggalRencana))
                startActivity(intentToDashboardActivity)
            }
        }
    }
    private fun updateTotalHarga() {
        // Reset total harga menjadi 0
        totalHarga = 0

        // Tambahkan harga setiap item yang dipilih
        totalHarga += hargaKotaAsal[selectedKotaAsal] ?: 0
        totalHarga += hargaStasiunAsal[selectedStasiunAsal] ?: 0
        totalHarga += hargaKotaTujuan[selectedKotaTujuan] ?: 0
        totalHarga += hargaStasiunTujuan[selectedStasiunTujuan] ?: 0
        totalHarga += hargaKelas[selectedKelas] ?: 0

        // Tampilkan total harga
        binding.totalHarga.text = "$$totalHarga"
    }
}