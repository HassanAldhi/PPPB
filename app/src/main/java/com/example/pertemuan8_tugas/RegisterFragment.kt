package com.example.pertemuan8_tugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pertemuan8_tugas.databinding.ActivityMainBinding
import com.example.pertemuan8_tugas.databinding.FragmentLoginBinding
import com.example.pertemuan8_tugas.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        // Add your register logic here
        binding.btnRegister.setOnClickListener {
            // For example, you can save user data to SharedPreferences
            saveUserData(binding.edtUsername.text.toString(), binding.edtPassword.text.toString())
            // After registration, switch to the login tab
            (activity as MainActivity).viewPager2.currentItem = 1
            showToast("Registration Successful")
        }
        binding.txtLogin.setOnClickListener{
            (activity as MainActivity).viewPager2.setCurrentItem(1)
        }
        return view
    }

    private fun saveUserData(username: String, password: String) {
        val sharedPreferences =
            activity?.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("username", username)
        editor?.putString("password", password)
        editor?.apply()
    }
    private fun showToast(message: String) {
        val context: Context? = activity
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}

