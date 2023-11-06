package com.example.pertemuan8_tugas

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan8_tugas.databinding.ItemStudentBinding

typealias OnClickStudent = (Student) -> Unit
class StudentAdapter(private val listStudent : List<Student>,
                     private val onClickStudent: OnClickStudent)
    : RecyclerView.Adapter<StudentAdapter.itemStudentViewHolder>() {

    inner class itemStudentViewHolder(private val
                                         binding: ItemStudentBinding ):
        RecyclerView.ViewHolder(binding.root){
            fun bind(data : Student){
                with(binding){
                    // Menggunakan context dari root view untuk mendapatkan resource ID gambar
                    val resourceId = itemView.context.resources.getIdentifier(data.img, "drawable", itemView.context.packageName)
                    studentPhoto.setImageResource(resourceId)
                    studentNameTxt.text = data.name
                    studentNimTxt.text = data.nim
                    gpaTxt.text = data.gpa.toString()

                    if (data.gpa < 3.0) {
                        gpaTxt.setTextColor(itemView.context.getColor(R.color.colorOrangeDark))
                        gpaTxt.setBackgroundColor(itemView.context.getColor(R.color.colorOrangeLight))
                    }else if (data.gpa < 3.5) {
                        gpaTxt.setTextColor(itemView.context.getColor(R.color.colorBlue))
                        gpaTxt.setBackgroundColor(itemView.context.getColor(R.color.colorBlueLight))
                    }

                    itemView.setOnClickListener{
                        onClickStudent(data)
                        val intent = Intent(itemView.context, StudentActivity::class.java).apply {
                            putExtra(Student.EXTRA_STUDENT_IMG, data.img)
                            putExtra(Student.EXTRA_STUDENT_NAME, data.name)
                            putExtra(Student.EXTRA_STUDENT_NIM, data.nim)
                            putExtra(Student.EXTRA_STUDENT_GPA, data.gpa)
                        }
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemStudentViewHolder {
       val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context),
           parent, false)
        return itemStudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }

    override fun onBindViewHolder(holder: itemStudentViewHolder, position: Int) {
        holder.bind(listStudent[position])
    }
}