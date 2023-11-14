package com.example.ppb.network

import com.example.ppb.model.UserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getAllUsers(): Call<UserData>
    @GET("users")
    fun getAllUsers(@Query("limit") limit:Int): Call<UserData>
}