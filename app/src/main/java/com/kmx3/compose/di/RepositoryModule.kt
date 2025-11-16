package com.kmx3.compose.di

import com.kmx3.compose.data.repositories.UserRepositoryImpl
import com.kmx3.compose.data.repositories.TokenRepositoryImpl
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.irepositories.ITokensRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): IUserRepository

    @Binds
    @Singleton
    abstract fun bindTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): ITokensRepository

    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(
        userProfileRepositoryImpl: UserProfileRepositoryImpl
    ): IUserProfileRepository
}