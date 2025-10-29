package com.kmx3.compose.data.remote.model.request

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int? = null
)