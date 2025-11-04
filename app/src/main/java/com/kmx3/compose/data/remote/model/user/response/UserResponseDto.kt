package com.kmx3.compose.data.remote.model.user.response

data class UserResponseDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)