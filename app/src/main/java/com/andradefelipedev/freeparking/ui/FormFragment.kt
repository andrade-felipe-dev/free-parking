package com.andradefelipedev.freeparking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.andradefelipedev.freeparking.databinding.FragmentFormBinding
import com.andradefelipedev.freeparking.helper.FirebaseHelper
import com.andradefelipedev.freeparking.ui.model.Register

class FormFragment : Fragment() {
    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var register: Register

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.btnSalvar.setOnClickListener {
            validateRegister()
        }
    }

    private fun validateRegister() {
        val placa = binding.edtPlaca.text.toString()

        if (placa.isNotEmpty()) {
            binding.progressBar.isVisible = true
            saveOccupation()
        } else {
            Toast.makeText(requireContext(), "Preencha a placa", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveOccupation() {
        FirebaseHelper
            .getDatabase()
            .child("Vagas")
            .child(register.id)
            .setValue(register)
            .addOnCompleteListener{
                register -> if (register.isSuccessful) {
                    binding.progressBar.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "Vaga ocupada com sucesso",
                        Toast.LENGTH_SHORT
                    )
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}