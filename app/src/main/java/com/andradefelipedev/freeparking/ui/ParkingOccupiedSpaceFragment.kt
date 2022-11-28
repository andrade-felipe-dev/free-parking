package com.andradefelipedev.freeparking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andradefelipedev.freeparking.R
import com.andradefelipedev.freeparking.databinding.FragmentParkingOccupiedSpaceBinding
import com.andradefelipedev.freeparking.databinding.FragmentRecoverBinding

class ParkingOccupiedSpaceFragment : Fragment() {
    private var _binding: FragmentParkingOccupiedSpaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParkingOccupiedSpaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}