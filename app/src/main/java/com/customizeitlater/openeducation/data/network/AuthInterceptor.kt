package com.customizeitlater.openeducation.data.network

import com.customizeitlater.openeducation.data.datastore.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(tokenProvider: TokenProvider): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
      val requsetBuilder=chain.request().newBuilder()
      requsetBuilder.apply{
          this.addHeader("Authorization","Bearer $")
      }
    return chain.proceed(requsetBuilder.build())

}

}