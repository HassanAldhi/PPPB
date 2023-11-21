package com.example.ppb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppb.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class  MainActivity : AppCompatActivity() {
    lateinit var mContactDao : ContactDao
    lateinit var executorService: ExecutorService
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()

        val db = ContactRoomDatabase.getDatabase(this)
        mContactDao = db!!.contactDao()!!

        with(binding){
            btnAdd.setOnClickListener{
                val intent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume(){
        super.onResume()
        getAllNotes()
    }

    private fun getAllNotes() {
        mContactDao.allContact.observe(this){
            contacts ->
            val adapterContact = ContactAdapter(
                contacts,
                onClickContact = { contacts ->
                    Toast.makeText(this@MainActivity, "Hey you chosee ${contacts.name}",
                        Toast.LENGTH_SHORT).show()
                },
                mContactDao = mContactDao,
                executorService = executorService
            )
            binding.rvContact.adapter = adapterContact
            binding.rvContact.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.subheaderTxt.text = "${contacts.size} kontak dengan nomor telepon"
        }
    }
}