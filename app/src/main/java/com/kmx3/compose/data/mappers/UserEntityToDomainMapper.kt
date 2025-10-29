package com.kmx3.compose.data.mappers

import com.kmx3.compose.data.local.model.UserEntity
import com.kmx3.compose.domain.models.User
import javax.inject.Inject

class UserEntityToDomainMapper @Inject constructor() {
    fun map(entity: UserEntity): User {
        return User(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            email = entity.email,
            age = entity.age,
            isActive = entity.isActive,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }
    
    fun map(entities: List<UserEntity>): List<User> {
        return entities.map { map(it) }
    }
}