package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.ppb.ContactAdapter
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val firestore = FirebaseFirestore.getInstance()
    private val budgetCollectionRef = firestore.collection("contacts")
    private lateinit var binding : ActivityMainBinding
    private val contactListLiveData : MutableLiveData<List<Contact>> by lazy {
        MutableLiveData<List<Contact>>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        FirebaseApp.initializeApp(this)


        setContentView(binding.root)

        with(binding){
            btnAdd.setOnClickListener{
                val intent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(intent)
            }
        }

        observeContacts()
        getAllContact()
    }
    private fun getAllContact(){
        observeContactChanges();
    }
    private fun observeContactChanges(){
        budgetCollectionRef.addSnapshotListener{ snapshots, error ->
            if (error != null){
                Log.d("MainActivity",
                    "Error listening for budget changes:", error)
            }
            val contacts = snapshots?.toObjects(Contact::class.java)
            if (contacts != null ){
                contactListLiveData.postValue((contacts))
            }
        }

    }
    private fun observeContacts() {
        contactListLiveData.observe(this) { contacts ->
            val adapter = ContactAdapter(contacts,
                onClickContact = { contacts ->
                    Toast.makeText(this@MainActivity, "Hey you chosee ${contacts.name}",
                        Toast.LENGTH_SHORT).show()
                }, firestore, budgetCollectionRef)
            binding.rvContact.adapter = adapter
            binding.rvContact.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.subheaderTxt.text = "${contacts.size} kontak dengan nomor telepon"
        }

    }
}