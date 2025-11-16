package com.kmx3.compose.domain.irepositories

import com.kmx3.compose.domain.models.User
import kotlinx.coroutines.flow.Flow

interface IUserProfileRepository {
    val userProfile: Flow<User?>
    suspend fun saveUserProfile(user: User)
    suspend fun clearUserProfile()
}