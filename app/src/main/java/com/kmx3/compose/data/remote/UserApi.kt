package com.kmx3.compose.data.remote

import com.kmx3.compose.data.remote.model.request.AuthUserRequest
import com.kmx3.compose.data.remote.model.response.AuthUserResponse
import com.kmx3.compose.data.remote.model.response.InfoUserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("/api/login/")
    suspend fun auth(
        @Body authUserRequest: AuthUserRequest
    ): Result<AuthUserResponse>

    @GET("/api/info/")
    suspend fun info(
        @Header ("Authorization") token: String
    ): Result<List<InfoUserResponse>>
}
