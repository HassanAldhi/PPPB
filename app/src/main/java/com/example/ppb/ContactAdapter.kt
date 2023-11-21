package com.example.ppb

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ppb.databinding.ItemContactBinding
import java.util.concurrent.ExecutorService


typealias OnClickContact = (Note) -> Unit
class ContactAdapter(private val listContact : List<Note>,
                     private val onClickContact: OnClickContact,
                     private val mNotesDao: NoteDao,
                     private val executorService: ExecutorService
)
    : RecyclerView.Adapter<ContactAdapter.itemContactViewHolder>() {


    inner class itemContactViewHolder(private val binding: ItemContactBinding ):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data : Note){
            val id = data.id
                with(binding){
                contactNameTxt.text = data.title
                contactNumberTxt.text = data.description

                    itemView.setOnClickListener{
                        onClickContact(data)

                    }

                    itemView.setOnLongClickListener {
                        delete(id)
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
    private fun delete(id: Int) {
        executorService.execute {
            mNotesDao.delete(id)
        }
    }

    override fun onBindViewHolder(holder: itemContactViewHolder, position: Int) {
        holder.bind(listContact[position])
    }
    fun getList(): List<Note> {
        return listContact
    }
}

