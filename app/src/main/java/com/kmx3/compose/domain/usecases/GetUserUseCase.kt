package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User
import kotlinx.coroutines.flow.Flow

/**
 * Use case to get a user by ID
 */
class GetUserUseCase(private val userRepository: IUserRepository) {
    
    /**
     * Get user by ID as a Flow
     */
    operator fun invoke(id: String): Flow<User?> {
        return userRepository.getUserById(id)
    }
    
    /**
     * Get all users as a Flow
     */
    fun getAllUsers(): Flow<List<User>> {
        return userRepository.getAllUsers()
    }
}