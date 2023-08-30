package com.example.pertemuan2_layout

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.example.pertemuan2_layout.databinding.ActivityMainBinding
// Mendefinisikan kelas utama bernama MainActivity yang diwarisi dari AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Mendeklarasikan beberapa variabel dan menginisialisasinya
    private lateinit var binding: ActivityMainBinding


    // Metode onCreate dipanggil ketika aktivitas (activity) dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan layout dengan kelas menggunakan View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}