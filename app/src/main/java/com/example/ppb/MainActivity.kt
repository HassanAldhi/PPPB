package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppb.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class  MainActivity : AppCompatActivity() {
    lateinit var mNotesDao : NoteDao
    lateinit var executorService: ExecutorService
    private var updateId : Int = 0
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()

        val db = NoteRoomDatabase.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

        with(binding){
            btnAdd.setOnClickListener{
                insert(Note(title = edtTitle.text.toString(),
                    description = edtDesc.text.toString()))
                setEmptyField()
            }

            listView.setOnItemClickListener{
                adapterView, _, i, _ ->
                val item = adapterView.adapter.getItem(i) as Note
                updateId = item.id
                edtTitle.setText(item.title)
                edtDesc.setText(item.description)
            }

            btnUpdate.setOnClickListener{
                update(Note(
                    id = updateId,
                    title = edtTitle.text.toString(),
                    description = edtDesc.text.toString()))

                updateId = 0
                setEmptyField()
            }
//
//            listView.onItemLongClickListener =
//                AdapterView.OnItemLongClickListener(){
//                    adapterView, view, i, l ->
//                    val item = adapterView.adapter.getItem(i) as Note
//                    delete(item)
//                    true
//                }

        }
    }

    override fun onResume(){
        super.onResume()
        getAllNotes()
    }

    private fun getAllNotes() {
        mNotesDao.allNotes.observe(this){
            notes ->
            val adapter : ArrayAdapter<Note> =
                ArrayAdapter<Note>(this,
                    android.R.layout.simple_list_item_1,
                    notes)
            binding.listView.adapter = adapter

            val adapterContact = ContactAdapter(
                notes,
                onClickContact = { note ->
                    Toast.makeText(this@MainActivity, "Hey you clicked ${note.title}", Toast.LENGTH_SHORT).show()
                    updateId = note.id
                    binding.edtTitle.setText(note.title)
                    binding.edtDesc.setText(note.description)
                },
                mNotesDao = mNotesDao,
                executorService = executorService
            )
            binding.rvContact.adapter = adapterContact
            binding.rvContact.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun insert(note : Note){
        executorService.execute{ mNotesDao.insert(note) }
    }

    private fun update(note : Note){
        executorService.execute{ mNotesDao.update(note) }
    }

    private fun delete(id: Int) {
        executorService.execute {
            mNotesDao.delete(id)
        }
    }

    private fun setEmptyField(){
        with(binding){
            edtTitle.setText("")
            edtDesc.setText("")
        }
    }
}