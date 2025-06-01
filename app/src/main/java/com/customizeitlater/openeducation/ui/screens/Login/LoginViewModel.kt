package com.customizeitlater.openeducation.ui.screens.Login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.customizeitlater.openeducation.data.datastore.PrefsDataManager
import com.customizeitlater.openeducation.data.network.AuthApi
import com.customizeitlater.openeducation.data.network.requestmodel.LoginUser
import com.customizeitlater.openeducation.di.TokenCryptoHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthApi,
 private val  prefsDataManager: PrefsDataManager,
    private val tokenCryptoHelper: TokenCryptoHelper
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val loginState: StateFlow<LoginState> = _loginState

    private val _loginUser = MutableStateFlow<LoginUser>(LoginUser("", ""))
    val loginUser: StateFlow<LoginUser> = _loginUser


    fun updateLoginDetails(
        name: String = loginUser.value.identity, password: String = loginUser.value.password

    ) {
        _loginUser.value = LoginUser(
            name, password
        )

    }

    fun loginUser() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val loginResponse = authApi.login(_loginUser.value)

                if (loginResponse.isSuccessful) {
                    val token = loginResponse.body()?.jwt
                    if(token!=null)
                    prefsDataManager.saveJwt(tokenCryptoHelper.encrypt(token))
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error("Invalid credentials")
                }

            } catch (e: Exception) {
                Log.d("Login Helper", e.toString())
                _loginState.value = LoginState.Error("Network error: ${e.localizedMessage}")
            }
        }
    }

}

    sealed class LoginState {
    object Success : LoginState()
    object Loading : LoginState()
    data class Error(val error: String) : LoginState()

}