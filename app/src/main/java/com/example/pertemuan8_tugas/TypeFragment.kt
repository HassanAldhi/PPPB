package com.example.pertemuan8_tugas

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.pertemuan8_tugas.databinding.FragmentTypeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TypeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TypeFragment : Fragment() {
    private lateinit var binding : FragmentTypeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jenisTiket = arrayOf("First Class Ticket", "Business Class Ticket", "Economy Class Ticket")
        var selectedTicket = ""

        with(binding){

            val adapterJenisTiket = ArrayAdapter<String>(
                requireContext(),
                R.layout.simple_spinner_item,
                jenisTiket
            )

            spinnerJenisTicket.adapter = adapterJenisTiket
            spinnerJenisTicket.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long) {
                        selectedTicket = jenisTiket[position]
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            btnBuy.setOnClickListener{
                findNavController().apply{
                    previousBackStackEntry?.
                            savedStateHandle?.
                            set("ticket", selectedTicket)
                }.navigateUp()
            }
        }
    }
}