package com.kmx3.compose.data.mappers

import com.kmx3.compose.data.remote.model.user.request.AuthUserRequest
import com.kmx3.compose.data.remote.model.user.request.CreateUserRequest
import com.kmx3.compose.data.remote.model.user.request.UpdateUserRequest
import com.kmx3.compose.domain.models.User
import javax.inject.Inject

class UserDomainToAuthRequestMapper @Inject constructor() {
    fun map(user: User): AuthUserRequest? {
        val username = user.username?.takeIf { it.isNotBlank() } ?: return null
        val password = user.password?.takeIf { it.isNotBlank() } ?: return null
        return AuthUserRequest(username, password)
    }
}