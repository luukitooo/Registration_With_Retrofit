package com.example.registration_with_retrofit.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.registration_with_retrofit.R
import com.example.registration_with_retrofit.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListeners()

    }

    private fun onClickListeners() = with(binding) {
        btnRegister.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.toRegisterFragment())
        }
        btnLogin.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.toLoginFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}