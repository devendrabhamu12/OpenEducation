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

//    init {
//        viewModelScope.launch {
//            val jwt = prefsDataManager.getJwt()
//            if (jwt!=null) {
//                _loginState.value = LoginState.Success
//            }
//        }
//    }


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
                val response = authApi.login(_loginUser.value)

                if (response.isSuccessful) {
                    val body = response.body()
                    val accessToken = body?.accessToken
                    val refreshToken = body?.refreshToken

                    if (!accessToken.isNullOrEmpty() && !refreshToken.isNullOrEmpty()) {
                        prefsDataManager.saveJwt(tokenCryptoHelper.encrypt(accessToken))
                        prefsDataManager.saveRefreshJwt(tokenCryptoHelper.encrypt(refreshToken))
                        _loginState.value = LoginState.Success
                        prefsDataManager.saveFirstLogin(false)
                    } else {
                        _loginState.value = LoginState.Error("Empty token response from server")
                    }

                } else {
                    Log.d("LoginHelper", "Login failed: ${response.code()} ${response.errorBody()?.string()}")
                    _loginState.value = LoginState.Error("Invalid credentials")
                }

            } catch (e: Exception) {
                Log.e("LoginHelper", "Exception during login", e)
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