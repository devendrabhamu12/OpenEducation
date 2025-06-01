package com.customizeitlater.openeducation.ui.screens.Register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.customizeitlater.openeducation.data.network.AuthApi
import com.customizeitlater.openeducation.data.network.requestmodel.RegisterUser
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authApi: AuthApi
) : ViewModel() {


     private val _registerUserState: MutableStateFlow<RegisterUser> = MutableStateFlow(RegisterUser("","",false,"","","","",""))
    val registerUserState: StateFlow<RegisterUser> = _registerUserState


   private val _registerationState: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> =_registerationState


    fun registerUser(){
        viewModelScope.launch {
            _registerationState.value= RegisterState.Loading


          try {
              val response=authApi.register(registerUserState.value)
              if(response.isSuccessful){
                  Log.d("helper registeration" ,response.toString())
                  _registerationState.value= RegisterState.Success
              }
              else{
                  Log.d("helper registeration" ,response.toString())
                  _registerationState.value= RegisterState.Error(response.message().toString())
              }

          }catch (e: Exception){
              _registerationState.value= RegisterState.Error(e.toString())
          }

        }
    }

    fun updateUser(
        identity: String = registerUserState.value.identity,
        profileName: String = registerUserState.value.profileName,
        password: String = registerUserState.value.password,
        email: String = registerUserState.value.email,
        country: String = registerUserState.value.country,
        phoneNumber: String = registerUserState.value.phoneNumber,
        bio: String = registerUserState.value.bio
    ) {
        _registerUserState.value = RegisterUser(
            identity = identity,
            profileName = profileName,
            password = password,
            email = email,
            isDeleted = false,
            country = country,
            phoneNumber = phoneNumber,
            bio = bio
        )
    }


}


sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}
