package com.kmx3.compose.data.remote

import com.kmx3.compose.data.remote.model.request.CreateUserRequest
import com.kmx3.compose.data.remote.model.request.UpdateUserRequest
import com.kmx3.compose.data.remote.model.response.UserResponseDto
import retrofit2.http.*

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