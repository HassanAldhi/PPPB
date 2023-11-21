package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ppb.databinding.ActivityAddBinding
import com.example.ppb.databinding.ActivityEditBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EditActivity : AppCompatActivity() {
    lateinit var mContactDao : ContactDao
    lateinit var executorService: ExecutorService
    private var updateId : Int = 0
    private lateinit var binding : ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()

        updateId = intent.getIntExtra("contact_id", 0)
        val name = intent.getStringExtra("contact_name")
        val number = intent.getStringExtra("contact_number")
        val db = ContactRoomDatabase.getDatabase(this)
        mContactDao = db!!.contactDao()!!

        with(binding){
            edtName.setText(name)
            edtNumber.setText(number)
            btnUpdate.setOnClickListener{
                update(Contact(
                    id = updateId,
                    name = edtName.text.toString(),
                    number = edtNumber.text.toString()))

                updateId = 0
                finish()
            }
            btnCancel.setOnClickListener{
                finish()
            }
        }


    }
    private fun update(contact : Contact){
        executorService.execute{ mContactDao.update(contact) }
    }
}