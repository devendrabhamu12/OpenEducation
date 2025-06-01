package com.customizeitlater.openeducation.data.network

import com.customizeitlater.openeducation.data.datastore.PrefsDataManager
import com.customizeitlater.openeducation.di.TokenCryptoHelper
import com.customizeitlater.openeducation.ui.theme.md_theme_dark_scrim
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton



class AuthInterceptor @Inject constructor(
    private val tokenCryptoHelper: TokenCryptoHelper,
    private val prefsDataManager: PrefsDataManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        // Don't add Authorization header for login/register
        if (!url.contains("login") ||  !url.contains("register")) {
            val tokenCipher = runBlocking { prefsDataManager.getJwt() }

            // Handle null case gracefully
            if (tokenCipher != null) {
                val token = tokenCryptoHelper.decrypt(tokenCipher)

                val newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()

                return chain.proceed(newRequest)
            }
        }

        return chain.proceed(request)
    }
}
