package com.kmx3.compose.data.mappers

import com.kmx3.compose.domain.models.User
import javax.inject.Inject

// This mapper would be used for mapping UserEntity to User domain model
// We already have UserEntityToDomainMapper, so this is for completeness
class UserDtoToDomainMapper @Inject constructor() {
    // In a real implementation, this would map from a DTO to domain User
    // Since we're using UserEntity for requests in the current implementation,
    // this mapper is primarily for future use cases
    fun map(user: User): User {
        return user // Identity mapping for now
    }
}