package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ppb.databinding.ActivityAddBinding
import com.example.ppb.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AddActivity : AppCompatActivity() {
    lateinit var mContactDao : ContactDao
    lateinit var executorService: ExecutorService
    private lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()

        val db = ContactRoomDatabase.getDatabase(this)
        mContactDao = db!!.contactDao()!!

        with(binding){
            btnSave.setOnClickListener{
                if(edtName.text.isNotEmpty() || edtNumber.text.isNotEmpty()){
                    insert(Contact(name = edtName.text.toString(),
                        number = edtNumber.text.toString()))
                    finish()
                }else{
                    Toast.makeText(this@AddActivity, "Hey Isi Data Dahulu !!",
                        Toast.LENGTH_SHORT).show()
                }
            }
            btnCancel.setOnClickListener{
                finish()
            }
        }
    }
    private fun insert(contact : Contact){
        executorService.execute{ mContactDao.insert(contact) }
    }

}