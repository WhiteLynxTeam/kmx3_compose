package com.kmx3.compose.domain.irepositories

import com.kmx3.compose.data.remote.model.user.response.AuthUserResponse
import com.kmx3.compose.domain.DomainResult
import com.kmx3.compose.domain.models.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface that defines the contract for user data operations
 */
interface IUserRepository {
    
    /**
     * Get all users
     */
    fun getAllUsers(): Flow<List<User>>
    
    /**
     * Get user by ID
     */
    fun getUserById(id: String): Flow<User?>
    
    /**
     * Insert a single user
     */
    suspend fun insertUser(user: User): Long
    
    /**
     * Insert multiple users
     */
    suspend fun insertUsers(users: List<User>)
    
    /**
     * Update a user
     */
    suspend fun updateUser(user: User)
    
    /**
     * Delete a user
     */
    suspend fun deleteUser(user: User)
    
    /**
     * Delete all users
     */
    suspend fun deleteAllUsers()
    
    /**
     * Authenticate user
     */
    suspend fun auth(user: User): DomainResult<String>
}