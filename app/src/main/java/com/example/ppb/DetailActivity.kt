package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ppb.databinding.ActivityDetailBinding
import com.example.ppb.model.User

class DetailActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            val data = intent.getParcelableExtra<User>("USER_DATA")
            if (data != null) {
                imgUser.setBackgroundResource(0)
                Glide.with(this@DetailActivity).load(data.image).into(imgUser)
                userName.text = data.firstName + " " + data.lastName
                genderTxt.text = data.gender
                heightTxt.text = "${data.height} cm"
                emailTxt.text = data.email
                birthdateTxt.text = data.birthDate
                eyeColorTxt.text = data.eyeColor
                universityTxt.text = data.university
                telpTxt.text = data.phone
            }
            btnBack.setOnClickListener(){
                finish()
            }
        }
    }
}