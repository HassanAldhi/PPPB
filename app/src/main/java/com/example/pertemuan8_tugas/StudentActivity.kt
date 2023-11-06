package com.example.pertemuan8_tugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan8_tugas.databinding.ActivityMainBinding
import com.example.pertemuan8_tugas.databinding.ActivityStudentBinding

class StudentActivity : AppCompatActivity() {
    private lateinit var binding:ActivityStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentImg = intent.getStringExtra(Student.EXTRA_STUDENT_IMG)
        val studentName = intent.getStringExtra(Student.EXTRA_STUDENT_NAME)
        val studentNim = intent.getStringExtra(Student.EXTRA_STUDENT_NIM)
        val studentGpa = intent.getDoubleExtra(Student.EXTRA_STUDENT_GPA, 0.0)

        with(binding){
            val resourceId = resources.getIdentifier(studentImg, "drawable", packageName)
            binding.studentImg.setImageResource(resourceId)
            nameTxt.text = studentName
            nimTxt.text = studentNim
            gpaTxt.text = studentGpa.toString()

            if (studentGpa < 3.0) {
                gpaTxt.setTextColor(resources.getColor(R.color.colorOrangeDark))
                gpaTxt.setBackgroundColor(resources.getColor(R.color.colorOrangeLight))
            } else if (studentGpa < 3.5) {
                gpaTxt.setTextColor(resources.getColor(R.color.colorBlue))
                gpaTxt.setBackgroundColor(resources.getColor(R.color.colorBlueLight))
            }

            btnBack.setOnClickListener(){
                finish()
            }
        }

    }
}