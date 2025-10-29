package com.kmx3.compose.data.mappers

import com.kmx3.compose.data.local.model.UserEntity
import com.kmx3.compose.domain.models.User
import javax.inject.Inject

class UserDomainToEntityMapper @Inject constructor() {
    fun map(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            age = user.age,
            isActive = user.isActive,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }
    
    fun map(users: List<User>): List<UserEntity> {
        return users.map { map(it) }
    }
}