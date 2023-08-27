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
    private val username = "hassan"
    private val password = "1234"

    // Metode onCreate dipanggil ketika aktivitas (activity) dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan layout dengan kelas menggunakan View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Membuat event listener untuk tombol Login
        with (binding) {
            btnLogin.setOnClickListener {
                var getUsername = usernameForm.text.toString()
                var getpass = passForm.text.toString()

                // Memeriksa apakah username dan password benar
                if (getUsername.equals(username) && getpass.equals(password)) {
                    // Menampilkan pesan
                    Toast.makeText(this@MainActivity,
                        "Login Sukses!",
                        Toast.LENGTH_SHORT).show()
                }
                else {
                    // Menampilkan pesan
                    Toast.makeText(this@MainActivity,
                        "Username atau password salah",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}