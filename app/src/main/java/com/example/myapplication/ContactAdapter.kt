package com.example.ppb

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Contact
import com.example.myapplication.EditActivity
import com.example.myapplication.databinding.ItemContactBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

typealias OnClickContact = (Contact) -> Unit
class ContactAdapter(
    private val listContact: List<Contact>,
    private val onClickContact: OnClickContact,
    private val firestore: FirebaseFirestore,
    private val budgetCollectionRef: CollectionReference
)
    : RecyclerView.Adapter<ContactAdapter.itemContactViewHolder>() {
    inner class itemContactViewHolder(private val binding: ItemContactBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data : Contact){
            val id = data.id
            val name = data.name
            val number = data.number
            with(binding){
                contactNameTxt.text = name
                contactNumberTxt.text = number

                itemView.setOnClickListener{
                    onClickContact(data)
                    val intent = Intent(itemView.context, EditActivity::class.java)
                    intent.putExtra("contact_id", id)
                    intent.putExtra("contact_name", name)
                    intent.putExtra("contact_number", number)
                    itemView.context.startActivity(intent)

                }

                itemView.setOnLongClickListener {
                    showDeleteConfirmationDialog(itemView.context, data, name)
                    true // Return true to indicate the event is consumed
                }
                }
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return itemContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listContact.size
    }
    private fun delete(contact: Contact){
        if (contact.id.isEmpty()){
            Log.d("Main activity","error delete item: budget Id is empty!")
            return
        }
        budgetCollectionRef.document(contact.id).delete().addOnFailureListener {
            Log.d("Main activity", "Error deleting budget")
        }
    }
    override fun onBindViewHolder(holder: itemContactViewHolder, position: Int) {
        holder.bind(listContact[position])
    }
    private fun showDeleteConfirmationDialog(context: Context, id: Contact, name: String) {
        AlertDialog.Builder(context)
            .setTitle("Hapus Kontak")
            .setMessage("Apa anda ingin menghapus kontak ${name}?")
            .setPositiveButton("Hapus") { _, _ ->
                delete(id)
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}

