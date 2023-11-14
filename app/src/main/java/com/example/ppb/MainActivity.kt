package com.example.ppb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppb.databinding.ActivityMainBinding
import com.example.ppb.model.User
import com.example.ppb.model.UserData
import com.example.ppb.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy { // dijalankan oleh sistem ketika dipanggil
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val client = ApiClient.getInstance()
        val response = client.getAllUsers(50)

        response.enqueue(object: retrofit2.Callback<UserData> {

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                val userList = ArrayList<User>()
                for (user in response.body()?.data ?: arrayListOf()) {
                    userList.add(user)
                }
                val listAdapter = UserAdapter(userList) {
                        user ->
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("USER_DATA", user)
                    startActivity(intent)
                }
                binding.rvContent.apply {
                    adapter = listAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }
}