package com.example.registration_with_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.registration_with_retrofit.model.LoginResponse
import com.example.registration_with_retrofit.model.User
import com.example.registration_with_retrofit.model.network.RetrofitInstance
import com.example.registration_with_retrofit.utils.ResponseHandler
import kotlinx.coroutines.flow.flow

class LoginViewModel : ViewModel() {

    fun logUser(user: User) = flow<ResponseHandler> {
        emit(ResponseHandler.Loader(isLoading = true))
        val response = RetrofitInstance.getAuthApi().logUser(user)
        if (response.isSuccessful && response.body() != null) {
            emit(ResponseHandler.Success<LoginResponse>(response.body()!!))
        } else {
            emit(ResponseHandler.Error(response.errorBody().toString()))
        }
        emit(ResponseHandler.Loader(isLoading = false))
    }

}