package com.example.registration_with_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.registration_with_retrofit.model.RegisterResponse
import com.example.registration_with_retrofit.model.User
import com.example.registration_with_retrofit.model.network.RetrofitInstance
import com.example.registration_with_retrofit.utils.ResponseHandler
import kotlinx.coroutines.flow.flow

class RegisterViewModel : ViewModel() {

    fun registerUser(user: User) = flow<ResponseHandler> {
        emit(ResponseHandler.Loader(isLoading = true))
        val response = RetrofitInstance.getAuthApi().registerUser(user)
        if (response.isSuccessful && response.body() != null) {
            emit(ResponseHandler.Success<RegisterResponse>(response.body()!!))
        } else {
            emit(ResponseHandler.Error(response.errorBody().toString()))
        }
        emit(ResponseHandler.Loader(isLoading = false))
    }

}