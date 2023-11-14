package com.example.ppb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ppb.databinding.ItemUserBinding
import com.example.ppb.model.User

typealias OnClickUser = (User) -> Unit
class UserAdapter(private val listUser: List<User>, private val OnClickUser: OnClickUser):
    RecyclerView.Adapter<UserAdapter.ItemUserViewHolder>() {
        inner class ItemUserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(user: User) {
                with(binding) {
                    telpTxt.text = user.phone
                    userNameTxt.text = user.firstName
                    userGender.text = user.gender
                    userHeight.text = "${user.height.toString()} cm"
                    userImage.setBackgroundResource(0)
                    Glide.with(itemView).load(user.image).into(userImage)
                }
                itemView.setOnClickListener {
                    OnClickUser(user)
                }
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUserViewHolder{
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemUserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ItemUserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }
}