package com.kmx3.compose.data.repositories

import com.kmx3.compose.data.local.PreferencesDataStore
import com.kmx3.compose.domain.irepositories.IUserProfileRepository
import com.kmx3.compose.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) : IUserProfileRepository {
    
    override val userProfile: Flow<User?> = preferencesDataStore.userProfile

    override suspend fun saveUserProfile(user: User) {
        preferencesDataStore.saveUserProfile(user)
    }

    override suspend fun clearUserProfile() {
        preferencesDataStore.clearUserProfile()
    }
}