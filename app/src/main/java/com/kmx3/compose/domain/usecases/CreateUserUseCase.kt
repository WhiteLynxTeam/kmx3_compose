package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User

/**
 * Use case to create a new user
 */
class CreateUserUseCase(private val userRepository: IUserRepository) {
    
    /**
     * Create a new user
     * Returns the ID of the created user
     */
    suspend operator fun invoke(user: User): Long {
        // Validate the user before creating
        if (!user.validate()) {
            throw IllegalArgumentException("Invalid user data")
        }
        
        return userRepository.insertUser(user)
    }
    
    /**
     * Create multiple users at once
     */
    suspend fun invoke(users: List<User>) {
        // Validate all users before creating
        users.forEach { user ->
            if (!user.validate()) {
                throw IllegalArgumentException("Invalid user data for user: ${user.id}")
            }
        }
        
        userRepository.insertUsers(users)
    }
}