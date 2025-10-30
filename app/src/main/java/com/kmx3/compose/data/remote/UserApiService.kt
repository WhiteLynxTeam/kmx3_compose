package com.kmx3.compose.data.remote

import com.kmx3.compose.data.remote.model.user.request.CreateUserRequest
import com.kmx3.compose.data.remote.model.user.request.UpdateUserRequest
import com.kmx3.compose.data.remote.model.user.response.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiService {
    @GET("users")
    suspend fun getAllUsersFromApi(): List<UserResponseDto>
    
    @GET("users/{id}")
    suspend fun getUserByIdFromApi(@Path("id") id: String): UserResponseDto
    
    @POST("users")
    suspend fun createUserToApi(@Body user: CreateUserRequest): UserResponseDto
    
    @PUT("users/{id}")
    suspend fun updateUserToApi(@Path("id") id: String, @Body user: UpdateUserRequest): UserResponseDto
    
    @DELETE("users/{id}")
    suspend fun deleteUserFromApi(@Path("id") id: String)
}