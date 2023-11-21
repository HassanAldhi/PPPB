package com.example.ppb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Query("DELETE FROM contact_table WHERE id = :id")
    fun delete(id: Int)

    @get:Query("SELECT * from contact_table ORDER BY id ASC")
    val allContact: LiveData<List<Contact>>
}