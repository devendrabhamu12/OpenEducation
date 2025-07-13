package com.customizeitlater.openeducation.ui.screens.Splash

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.customizeitlater.openeducation.data.datastore.PrefsDataManager
import com.customizeitlater.openeducation.data.network.AuthApi
import com.customizeitlater.openeducation.data.network.requestmodel.RefreshTokenRequest
import com.customizeitlater.openeducation.di.TokenCryptoHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val dataManager: PrefsDataManager,
    val authApi: AuthApi,
    val tokenCryptoHelper: TokenCryptoHelper
) : ViewModel()

{

    private val _splashState = mutableStateOf<SplashState>(SplashState.Loading)
    val splashState: State<SplashState> = _splashState

    init {
        Log.d("viewmodel",this.toString())
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            val isFirstLogin = dataManager.getFirstLogin()
            Log.d("viewmodel",isFirstLogin.toString())
            if (isFirstLogin) {
                _splashState.value = SplashState.FirstLogin

            }else{

            // Try refreshing JWT
            val refreshToken = dataManager.getRefreshJwt()
            try {
                val response = authApi.refreshToken(RefreshTokenRequest(tokenCryptoHelper.decrypt(refreshToken)))
                Log.d("viewmodel1",response.body().toString())
                if (response.isSuccessful && response.body() != null) {
                    val jwts = response.body()!!
                    dataManager.saveJwt(tokenCryptoHelper.encrypt(jwts.accessToken))
                    dataManager.saveRefreshJwt(tokenCryptoHelper.encrypt(jwts.refreshToken))
                    _splashState.value = SplashState.JwtRefreshed
                    Log.d("helper",response.body().toString())
                    Log.d("viewmodelfinal",_splashState.value.toString())
                } else {
                    _splashState.value = SplashState.JwtFailed
                    Log.d("helper",response.body().toString())
                    Log.d("viewmodelfinal",_splashState.value.toString())
                }

            } catch (e: Exception) {
                _splashState.value = SplashState.JwtFailed
                Log.d("helper",e.toString() + e.printStackTrace())
                Log.d("viewmodelfinalexception",_splashState.value.toString())
            }}

            Log.d("viewmodelfinal",_splashState.value.toString())
        }
    }
}

sealed class SplashState {
    object Loading : SplashState()
    object FirstLogin : SplashState()
    object JwtRefreshed : SplashState()
    object JwtFailed : SplashState()
}
