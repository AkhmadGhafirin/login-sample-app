package com.cascer.loginsampleapp.data.remote.network

import com.cascer.loginsampleapp.data.remote.request.LoginRequest
import com.cascer.loginsampleapp.data.remote.response.ListUserResponse
import com.cascer.loginsampleapp.data.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): ListUserResponse
}