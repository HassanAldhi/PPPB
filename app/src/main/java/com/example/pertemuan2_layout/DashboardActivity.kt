package com.example.pertemuan2_layout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pertemuan2_layout.databinding.ActivityDashboardBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private var tanggalRencana = mutableSetOf<String>()
    private var content = false


    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            var receivedTanggal = data?.getStringExtra(PlanActivity.EXTRA_TANGGAL) ?: ""
            var receivedKotaAsal = data?.getStringExtra(PlanActivity.EXTRA_KOTA_ASAL) ?: ""
            var receivedKotaTujuan = data?.getStringExtra(PlanActivity.EXTRA_KOTA_TUJUAN) ?: ""
            var receivedStasiunAsal = data?.getStringExtra(PlanActivity.EXTRA_STASIUN_ASAL) ?: ""
            var receivedStasiunTujuan = data?.getStringExtra(PlanActivity.EXTRA_STASIUN_TUJUAN) ?: ""
            var receivedKelas = data?.getStringExtra(PlanActivity.EXTRA_KELAS) ?: ""
            var receivedHarga = data?.getStringExtra(PlanActivity.EXTRA_HARGA) ?: ""
            var receivedPaket = data?.getStringExtra(PlanActivity.EXTRA_PAKET) ?: ""
            tanggalRencana.add(receivedTanggal.toString())

            binding.bgNoData.visibility = View.INVISIBLE
            binding.ketJadwal.visibility = View.VISIBLE
            binding.icJadwal.visibility = View.VISIBLE
            binding.harga.visibility = View.VISIBLE
            binding.kotaAsal.visibility = View.VISIBLE
            binding.kotaTujuan.visibility = View.VISIBLE
            binding.stasiunAsal.visibility = View.VISIBLE
            binding.stasiunTujuan.visibility = View.VISIBLE
            binding.ruteBar.visibility = View.VISIBLE
            binding.kelas.visibility = View.VISIBLE
            binding.headerPaket.visibility = View.VISIBLE
            binding.paket.visibility = View.VISIBLE

            //mengubah nilai text
            binding.ketJadwal.text = "Berangkat Pada " + receivedTanggal
            binding.harga.text = receivedHarga
            binding.kotaAsal.text = receivedKotaAsal
            binding.kotaTujuan.text = receivedKotaTujuan
            binding.stasiunAsal.text = receivedStasiunAsal
            binding.stasiunTujuan.text = receivedStasiunTujuan
            binding.kelas.text = receivedKelas
            binding.paket.text = receivedPaket

            binding.kalenderPerjalanan.setOnDateChangeListener { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val formattedDate =
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)

                if (tanggalRencana != null && tanggalRencana.contains(formattedDate)) {
                    Toast.makeText(
                        this@DashboardActivity,
                        "Pada tanggal $formattedDate terdapat rencana perjalanan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namaUser = intent.getStringExtra(LoginActivity.EXTRA_NAME)?: ""

        with(binding) {
            username.text = "Hi, $namaUser"
            btnTambahRencana.setOnClickListener {
                val intent = Intent(this@DashboardActivity, PlanActivity::class.java)
                launcher.launch(intent)
            }
        }
    }
}