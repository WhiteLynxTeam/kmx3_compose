package com.kmx3.compose.domain.irepositories

import com.kmx3.compose.domain.DomainResult
import com.kmx3.compose.domain.models.User

/**
 * Repository interface that defines the contract for user data operations
 */
interface IUserRepository {
    suspend fun auth(user: User): DomainResult<String>
    suspend fun getUserInfo(): DomainResult<User>
}