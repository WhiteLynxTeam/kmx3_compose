package com.kmx3.compose.data.mappers

import com.kmx3.compose.data.remote.model.user.request.CreateUserRequest
import com.kmx3.compose.data.remote.model.user.request.UpdateUserRequest
import com.kmx3.compose.domain.models.User
import javax.inject.Inject

class UserDomainToRequestMapper @Inject constructor() {
    fun mapToCreateRequest(user: User): CreateUserRequest {
        return CreateUserRequest(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            age = user.age
        )
    }
    
    fun mapToUpdateRequest(user: User): UpdateUserRequest {
        return UpdateUserRequest(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            age = user.age,
            isActive = user.isActive
        )
    }
}