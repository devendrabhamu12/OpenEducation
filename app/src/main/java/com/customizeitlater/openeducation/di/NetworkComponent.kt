package com.customizeitlater.openeducation.di

import com.customizeitlater.openeducation.PrivateVarsNotToUpload
import com.customizeitlater.openeducation.data.network.AuthApi
import com.customizeitlater.openeducation.data.network.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkComponent{

    @Provides
    fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
        val client= OkHttpClient.Builder()
            .addInterceptor (authInterceptor)
            .build()
        val json= Json{
            ignoreUnknownKeys=true
        }

        return Retrofit.Builder()
            .baseUrl(PrivateVarsNotToUpload.Url)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }


    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

//    @Provides
//    fun provideAuthInterceptor(tokenProvider: TokenProvider): AuthInterceptor {
//        return AuthInterceptor(tokenProvider)
//    }
}