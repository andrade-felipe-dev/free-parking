package com.andradefelipedev.freeparking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andradefelipedev.freeparking.databinding.FragmentFreeParkingSpaceBinding
import com.andradefelipedev.freeparking.helper.FirebaseHelper
import com.andradefelipedev.freeparking.ui.adapter.RegisterAdapter
import com.andradefelipedev.freeparking.ui.model.Register
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FreeParkingSpaceFragment : Fragment() {
    private var _binding: FragmentFreeParkingSpaceBinding? = null
    private val binding get() = _binding!!
    private val registerList = mutableListOf<Register>()
    private lateinit var registerAdapter: RegisterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFreeParkingSpaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRegister()
    }

    private fun getRegister() {
        FirebaseHelper
            .getDatabase()
            .child("Vagas")
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (snap in snapshot.children) {
//                                val register = snap.getValue(Register::class.java) as Register
                                println(snap)
//                                registerList.add(register)
                            }
                            binding.progressBar.isVisible = false
                            binding.textInfo.text = ""
                            initAdapter()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(requireContext(), "Erro ao Preencher a vaga", Toast.LENGTH_SHORT).show()
                    }

                }
            )
    }

    private fun optionSelect(register: Register, select: Int) {
        when (select) {
            RegisterAdapter.SELECT_CAR -> {
                updateRegister(register)
            }
        }
    }

    private fun updateRegister(register: Register) {
        FirebaseHelper
            .getDatabase()
            .child("Vagas")
            .child(register.id)
            .setValue(register)
            .addOnCompleteListener { r ->
                if (r.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Vaga Preenchidda",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    private fun initAdapter() {
        binding.rvFreeParkingSpace.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFreeParkingSpace.setHasFixedSize(true)
        registerAdapter = RegisterAdapter(requireContext(), registerList) {
                register, i -> optionSelect(register, i)
        }
        binding.rvFreeParkingSpace.adapter = registerAdapter
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}