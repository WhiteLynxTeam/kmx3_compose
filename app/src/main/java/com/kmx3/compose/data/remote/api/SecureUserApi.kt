package com.kmx3.compose.data.remote.api

import com.kmx3.compose.data.remote.model.user.response.InfoUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface SecureUserApi {
    @GET("/api/info/")
    suspend fun info(): Response<List<InfoUserResponse>>
}