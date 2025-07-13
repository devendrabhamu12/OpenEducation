package com.customizeitlater.openeducation.ui.screens.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.customizeitlater.openeducation.data.network.AuthApi
import com.customizeitlater.openeducation.data.network.requestmodel.RegisterUser
import com.customizeitlater.openeducation.data.network.responsemodel.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authApi: AuthApi
) : ViewModel() {

    // Kotlinx Serialization JSON instance
    private val json = Json { ignoreUnknownKeys = true }

    // User registration input state
    private val _registerUserState = MutableStateFlow(
        RegisterUser(
            identity = "",
            profileName = "",
            isDeleted = false,
            password = "",
            email = "",
            country = "",
            phoneNumber = "",
            bio = ""
        )
    )
    val registerUserState: StateFlow<RegisterUser> = _registerUserState

    // UI state for registration process
    private val _registerationState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerationState

    fun registerUser() {
        viewModelScope.launch {
            _registerationState.value = RegisterState.Loading

            try {
                val response = authApi.register(registerUserState.value)

                if (response.isSuccessful) {
                    _registerationState.value = RegisterState.Success
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        json.decodeFromString<ErrorResponse>(errorBody ?: "").error
                    } catch (e: Exception) {
                        "An unexpected error occurred"
                    }

                    _registerationState.value = RegisterState.Error(errorMessage)
                }

            } catch (e: Exception) {
                _registerationState.value = RegisterState.Error("Network error: ${e.localizedMessage}")
            }
        }
    }

    fun updateUser(
        identity: String = _registerUserState.value.identity,
        profileName: String = _registerUserState.value.profileName,
        password: String = _registerUserState.value.password,
        email: String = _registerUserState.value.email,
        country: String = _registerUserState.value.country,
        phoneNumber: String = _registerUserState.value.phoneNumber,
        bio: String = _registerUserState.value.bio
    ) {
        _registerUserState.value = RegisterUser(
            identity = identity,
            profileName = profileName,
            isDeleted = false,
            password = password,
            email = email,
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
