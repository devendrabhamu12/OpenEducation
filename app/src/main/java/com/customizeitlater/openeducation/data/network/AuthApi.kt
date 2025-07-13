package com.customizeitlater.openeducation.data.network

import com.customizeitlater.openeducation.data.network.requestmodel.LoginUser
import com.customizeitlater.openeducation.data.network.requestmodel.RefreshTokenRequest
import com.customizeitlater.openeducation.data.network.requestmodel.RegisterUser
import com.customizeitlater.openeducation.data.network.responsemodel.LoginResponse
import com.customizeitlater.openeducation.data.network.responsemodel.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/register")
    suspend fun register(@Body registerRequest: RegisterUser):Response<RegisterResponse>

    @POST("api/auth/login")
    suspend fun login(@Body loginUser: LoginUser): Response<LoginResponse>

    @POST("api/auth/refresh-token")
    suspend fun refreshToken(@Body refrestReq: RefreshTokenRequest ): Response<LoginResponse>
}