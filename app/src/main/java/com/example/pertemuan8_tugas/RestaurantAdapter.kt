package com.example.pertemuan8_tugas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan8_tugas.databinding.ItemRestaurantBinding

class RestaurantAdapter() : RecyclerView.Adapter<RestaurantAdapter.itemRestaurantViewHolder>() {

    inner class itemRestaurantViewHolder(private val
                                         binding: ItemRestaurantBinding ):
        RecyclerView.ViewHolder(binding.root){
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemRestaurantViewHolder {
       val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context),
           parent, false)
        return itemRestaurantViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: itemRestaurantViewHolder, position: Int) {

    }
}