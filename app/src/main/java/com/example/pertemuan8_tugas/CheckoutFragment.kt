package com.example.pertemuan8_tugas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pertemuan8_tugas.databinding.FragmentCheckoutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckoutFragment : Fragment() {
    private lateinit var binding : FragmentCheckoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
//            val args : CheckoutFragmentArgs by navArgs()

            edtTicket.setOnClickListener{
                val action =  CheckoutFragmentDirections.actionCheckoutFragmentToTypeFragment()
                findNavController().navigate(action)
            }

            findNavController().currentBackStackEntry?.
                    savedStateHandle?.let{
                        handle -> handle.getLiveData<String>("ticket")
                .observe(viewLifecycleOwner) {
                    res ->
                    edtTicket.setText(res)
                }
            }

            btnBuy.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}