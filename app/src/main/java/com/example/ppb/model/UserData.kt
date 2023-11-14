package com.example.ppb.model

import com.google.gson.annotations.SerializedName
//mewakili struktur JSON yang memiliki kunci "users"
// yang menunjukkan array objek pengguna (User)
data class UserData(
    @SerializedName("users")
    val data: List<User>
)
