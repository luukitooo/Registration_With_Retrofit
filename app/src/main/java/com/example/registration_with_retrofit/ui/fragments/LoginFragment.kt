package com.example.registration_with_retrofit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.registration_with_retrofit.databinding.FragmentLoginBinding
import com.example.registration_with_retrofit.extensions.areLinesEmpty
import com.example.registration_with_retrofit.extensions.isOnline
import com.example.registration_with_retrofit.model.User
import com.example.registration_with_retrofit.utils.ResponseHandler
import com.example.registration_with_retrofit.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListeners()

    }

    private fun onClickListeners() {
        binding.btnLogin.setOnClickListener {
            if (!binding.root.areLinesEmpty() && requireContext().isOnline()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.logUser(getUser()).collect {
                        handleResponse(it)
                    }
                }
            }
        }
    }

    private fun handleResponse(responseHandler: ResponseHandler) {
        when (responseHandler) {
            is ResponseHandler.Success<*> -> {
                Snackbar.make(binding.root, responseHandler.result.toString(), Snackbar.LENGTH_LONG).show()
            }
            is ResponseHandler.Error -> {
                Snackbar.make(binding.root, responseHandler.error, Snackbar.LENGTH_LONG).show()
            }
            is ResponseHandler.Loader -> {
                binding.progressBar.isVisible = responseHandler.isLoading
            }
        }
    }

    private fun getUser() = User(
        email = binding.etEmail.text.toString(),
        password = binding.etPassword.text.toString()
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}