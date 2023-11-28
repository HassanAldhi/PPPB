package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.databinding.ActivityEditBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class EditActivity : AppCompatActivity() {
    private val firestore = FirebaseFirestore.getInstance()
    private val budgetCollectionRef = firestore.collection("contacts")
    private var updateId : String = ""
    private lateinit var binding : ActivityEditBinding
    private val contactListLiveData : MutableLiveData<List<Contact>> by lazy {
        MutableLiveData<List<Contact>>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)

        updateId = intent.getStringExtra("contact_id") ?: ""
        val name = intent.getStringExtra("contact_name")
        val number = intent.getStringExtra("contact_number")

        with(binding){
            edtName.setText(name)
            edtNumber.setText(number)
            btnUpdate.setOnClickListener {
                val name = edtName.text.toString()
                val number = edtNumber.text.toString()
                val updateContact = Contact(name = name, number = number)

                updateContact(updateContact)
                updateId = ""
                finish()
            }
            btnCancel.setOnClickListener{
                finish()
            }
        }


    }
    private fun updateContact(contact: Contact){
        contact.id = updateId
        budgetCollectionRef.document(updateId).set(contact).
        addOnFailureListener {
            Log.d("Main activity", "error updating budget", it)
        }
    }
}