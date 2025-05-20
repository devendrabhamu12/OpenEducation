package com.customizeitlater.openeducation.data.network

import com.customizeitlater.openeducation.data.network.requestmodel.LoginUser
import com.customizeitlater.openeducation.data.network.requestmodel.RegisterUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/register")
    suspend fun register(@Body registerRequest: RegisterUser)

    @GET("api/auth/login")
    suspend fun login(@Body loginUser: LoginUser)

}