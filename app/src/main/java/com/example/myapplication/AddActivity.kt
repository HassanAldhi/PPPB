package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAddBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class AddActivity : AppCompatActivity() {
    private val firestore = FirebaseFirestore.getInstance()
    private val budgetCollectionRef = firestore.collection("contacts")
    private lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        FirebaseApp.initializeApp(this)
        setContentView(binding.root)


        with(binding){
            btnSave.setOnClickListener {
                if(edtName.text.isNotEmpty() || edtNumber.text.isNotEmpty()){
                    val name = edtName.text.toString()
                    val number = edtNumber.text.toString()
                    val newContact = Contact(name = name, number = number)
                    addContact(newContact)
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
    private fun addContact(contact: Contact){
        budgetCollectionRef.add(contact).addOnSuccessListener {
                documentReference ->
            val createBudgetId = documentReference.id
            contact.id = createBudgetId
            documentReference.set(contact).addOnFailureListener{
                Log.d("Main activity", "Error updating budget id : ", it)
            }
        }.addOnFailureListener{
            Log.d("Main activity", "Error adding budget id : ", it)
        }
    }

}