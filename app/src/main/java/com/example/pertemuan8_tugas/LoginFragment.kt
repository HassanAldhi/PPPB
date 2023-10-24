package com.example.pertemuan8_tugas
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pertemuan8_tugas.databinding.FragmentLoginBinding
import com.example.pertemuan8_tugas.databinding.FragmentRegisterBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnLogin.setOnClickListener {
            val sharedPreferences =
                activity?.getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val savedUsername = sharedPreferences?.getString("username", "")
            val savedPassword = sharedPreferences?.getString("password", "")

            if (binding.edtUsername.text.toString() == savedUsername &&
                binding.edtPassword.text.toString() == savedPassword
            ) {
                // Login successful, start dashboard activity
                val intent = Intent(requireContext(), DashboardActivity::class.java)
                showToast("Login successful!")
                startActivity(intent)
            } else {
                showToast("Wrong username or password")
            }
        }

        binding.txtRegister.setOnClickListener{
            (activity as MainActivity).viewPager2.setCurrentItem(0)
        }

        return view
    }
    private fun showToast(message: String) {
        val context: Context? = activity
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}
