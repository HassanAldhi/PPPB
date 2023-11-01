package com.example.pertemuan8_tugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan8_tugas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterRestaurant = RestaurantAdapter()
        with(binding){
            rvRestaurants.apply{
                adapter = adapterRestaurant
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }
}
