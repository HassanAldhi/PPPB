package com.example.pertemuan8_tugas

class Student (
    val img: String = "",
    val name: String = "",
    val nim: String = "",
    val gpa: Double = 0.0
) {
    companion object {
        const val EXTRA_STUDENT_IMG = "student_img"
        const val EXTRA_STUDENT_NAME = "student_name"
        const val EXTRA_STUDENT_NIM = "student_nim"
        const val EXTRA_STUDENT_GPA = "student_gpa"
    }
}