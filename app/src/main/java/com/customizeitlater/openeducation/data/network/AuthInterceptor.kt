package com.customizeitlater.openeducation.data.network

import com.customizeitlater.openeducation.di.TokenProvider
import com.customizeitlater.openeducation.ui.theme.md_theme_dark_scrim
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton




@Singleton
class AuthInterceptor @Inject constructor(tokenProvider: TokenProvider): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
      val request=chain.request()
        if(!request.url.toString().contains("login")||!request.url.toString().contains("register")) {
        val requsetBuilder=chain.request().newBuilder()
      requsetBuilder.apply{
          this.addHeader("Authorization","Bearer $")
      }
    return chain.proceed(requsetBuilder.build())}
        else{
            return chain.proceed(request)
        }

}

}