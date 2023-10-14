package com.example.pertemuan2_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pertemuan2_layout.databinding.ActivityDashboardBinding
import com.example.pertemuan2_layout.databinding.ActivityLoginBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private var content = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedTanggal = intent.getStringExtra(PlanActivity.EXTRA_TANGGAL)
        val receivedKotaAsal = intent.getStringExtra(PlanActivity.EXTRA_KOTA_ASAL)
        val receivedKotaTujuan = intent.getStringExtra(PlanActivity.EXTRA_KOTA_TUJUAN)
        val receivedStasiunAsal = intent.getStringExtra(PlanActivity.EXTRA_STASIUN_ASAL)
        val receivedStasiunTujuan = intent.getStringExtra(PlanActivity.EXTRA_STASIUN_TUJUAN)
        val receivedKelas = intent.getStringExtra(PlanActivity.EXTRA_KELAS)
        val receivedHarga = intent.getStringExtra(PlanActivity.EXTRA_HARGA)
        val receivedPaket = intent.getStringExtra(PlanActivity.EXTRA_PAKET)
        val receivedContent = intent.getBooleanExtra(PlanActivity.EXTRA_CONTENT, false)
        val receivedTanggalRencana = intent.getStringArrayListExtra("TANGGAL_RENCANA")

        content = receivedContent

        with(binding){

            if(content){
                // ubah visibilitas
                bgNoData.visibility= View.INVISIBLE
                ketJadwal.visibility= View.VISIBLE
                icJadwal.visibility= View.VISIBLE
                harga.visibility= View.VISIBLE
                kotaAsal.visibility= View.VISIBLE
                kotaTujuan.visibility= View.VISIBLE
                stasiunAsal.visibility= View.VISIBLE
                stasiunTujuan.visibility= View.VISIBLE
                ruteBar.visibility= View.VISIBLE
                kelas.visibility= View.VISIBLE
                headerPaket.visibility= View.VISIBLE
                paket.visibility= View.VISIBLE

                //mengubah nilai text
                ketJadwal.text = receivedTanggal
                harga.text = receivedHarga
                kotaAsal.text = receivedKotaAsal
                kotaTujuan.text = receivedKotaTujuan
                stasiunAsal.text = receivedStasiunAsal
                stasiunTujuan.text = receivedStasiunTujuan
                kelas.text = receivedKelas
                paket.text = receivedPaket
            }

            btnTambahRencana.setOnClickListener {
                val intentToPlandActivity =
                    Intent(this@DashboardActivity,
                        PlanActivity::class.java)
                startActivity(intentToPlandActivity)
            }

            kalenderPerjalanan.setOnDateChangeListener { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)

                if (receivedTanggalRencana != null && receivedTanggalRencana.contains(formattedDate)) {
                    Toast.makeText(
                        this@DashboardActivity,
                        "Pada tanggal $formattedDate terdapat rencana perjalanan",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}