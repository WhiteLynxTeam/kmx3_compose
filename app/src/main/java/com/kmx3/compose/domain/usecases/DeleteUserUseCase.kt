package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User

/**
 * Use case to delete a user
 */
class DeleteUserUseCase(private val userRepository: IUserRepository) {
    
    /**
     * Delete a specific user
     */
    suspend operator fun invoke(user: User) {
        userRepository.deleteUser(user)
    }
    
    /**
     * Delete all users
     */
    suspend fun deleteAll() {
        userRepository.deleteAllUsers()
    }
}