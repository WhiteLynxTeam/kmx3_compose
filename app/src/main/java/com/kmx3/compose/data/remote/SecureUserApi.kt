package com.kmx3.compose.data.remote

import com.kmx3.compose.data.remote.model.user.response.InfoUserResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface SecureUserApi {
    @GET("/api/info/")
    suspend fun info(
        @Header ("Authorization") token: String
    ): Result<List<InfoUserResponse>>
}