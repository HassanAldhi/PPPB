package com.example.pertemuan8_tugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan8_tugas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterStudent = StudentAdapter(generateStudents()){
            student -> Toast.makeText(this@MainActivity, "You clicked on ${student.name}",
            Toast.LENGTH_SHORT).show()
        }
        with(binding){
            rvRestaurants.apply{
                adapter = adapterStudent
                layoutManager = LinearLayoutManager(this@MainActivity)
                    // GridLayoutManager(this@MainActivity, 2)
            }
        }
    }
    fun generateStudents(): List<Student> {
        return listOf(
            Student(img = "img_budi", name = "Budi Utomo", nim = "22/23122/SV/12353", gpa = 3.9),
            Student(img = "img_gara", name = "Gara Madagascar", nim = "22/12394/SV/23412", gpa = 3.5),
            Student(img = "img_steve", name = "Steve Jobless", nim = "22/55555/SV/97352", gpa = 2.2),
            Student(img = "img_john", name = "John Doe", nim = "22/54532/SV/67754", gpa = 3.1),
            Student(img = "img_liam", name = "Liam Malik", nim = "22/64521/SV/87532", gpa = 2.9),
            Student(img = "img_osas", name = "Osas Ufufefe", nim = "22/76321/SV/24442", gpa = 3.2)
        )
    }
}
