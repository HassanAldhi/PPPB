package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val firestore = FirebaseFirestore.getInstance()
    private val budgetCollectionRef = firestore.collection("budgets")
    private lateinit var binding : ActivityMainBinding
    private var updateId = ""
    private val budgetListLiveData : MutableLiveData<List<Budget>> by lazy {
        MutableLiveData<List<Budget>>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        FirebaseApp.initializeApp(this)


        setContentView(binding.root)

        with(binding){
            btnAdd.setOnClickListener {
                val nominal = edtNominal.text.toString()
                val desc = edtDesc.text.toString()
                val date = edtDate.text.toString()

                val newBudget = Budget(nominal = nominal, description = desc, date = date)
                addBudget(newBudget)
            }
            btnUpdate.setOnClickListener {
                val nominal = edtNominal.text.toString()
                val desc = edtDesc.text.toString()
                val date = edtDate.text.toString()
                val updateBudget = Budget(nominal = nominal, description = desc, date = date)

                updateBudget(updateBudget)
                updateId = ""
                setEmptyField()
            }
            listView.setOnItemClickListener { adapterView, view, i, l ->
                val item = adapterView.adapter.getItem(i) as Budget
                updateId = item.id
                edtDesc.setText(item.description)
                edtDate.setText(item.date)
                edtNominal.setText(item.nominal)
            }
        }

        observeBudgets()
        getAllBudget()
    }
    private fun getAllBudget(){
        observeBudgetChanges();
    }
    private fun observeBudgetChanges(){
        budgetCollectionRef.addSnapshotListener{ snapshots, error ->
            if (error != null){
                Log.d("MainActivity",
                    "Error listening for budget changes:", error)
            }
            val budgets = snapshots?.toObjects(Budget::class.java)
            if (budgets != null ){
                budgetListLiveData.postValue((budgets))
            }
        }

    }
    private fun observeBudgets(){
        budgetListLiveData.observe(this){
            budgets ->
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, budgets.toMutableList())
            binding.listView.adapter = adapter
        }
    }

    private fun addBudget(budget: Budget){
        budgetCollectionRef.add(budget).addOnSuccessListener {
            documentReference ->
            val createBudgetId = documentReference.id
            budget.id = createBudgetId
            documentReference.set(budget).addOnFailureListener{
                Log.d("Main activity", "Error updating budget id : ", it)
            }
        }.addOnFailureListener{
            Log.d("Main activity", "Error adding budget id : ", it)
        }
    }
    private fun updateBudget(budget: Budget){
        budget.id = updateId
        budgetCollectionRef.document(updateId).set(budget).
                addOnFailureListener {
                    Log.d("Main activity", "error updating budget", it)
                }
    }
    private fun delete(budget: Budget){
        if (budget.id.isEmpty()){
            Log.d("Main activity","error delete item: budget Id is empty!")
            return
        }
        budgetCollectionRef.document(budget.id).delete().addOnFailureListener {
            Log.d("Main activity", "Error deleting budget")
        }
    }
    private fun setEmptyField(){
        with(binding){
            edtNominal.setText("")
            edtDate.setText("")
            edtDesc.setText("")
        }
    }
}