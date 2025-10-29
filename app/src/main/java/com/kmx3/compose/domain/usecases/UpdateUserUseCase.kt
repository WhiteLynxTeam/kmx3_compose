package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User

/**
 * Use case to update an existing user
 */
class UpdateUserUseCase(private val userRepository: IUserRepository) {
    
    /**
     * Update an existing user
     */
    suspend operator fun invoke(user: User) {
        // Validate the user before updating
        if (!user.validate()) {
            throw IllegalArgumentException("Invalid user data")
        }
        
        userRepository.updateUser(user)
    }
}