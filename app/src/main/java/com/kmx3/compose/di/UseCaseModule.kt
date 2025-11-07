package com.kmx3.compose.di

import com.kmx3.compose.domain.irepositories.ITokensRepository
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.usecases.AuthApiUseCase
import com.kmx3.compose.domain.usecases.GetUserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthApiUseCase(
        userRepository: IUserRepository,
        tokensRepository: ITokensRepository
    ): AuthApiUseCase {
        return AuthApiUseCase(userRepository, tokensRepository)
    }
    
    @Provides
    @Singleton
    fun provideGetUserInfoUseCase(
//        secureUserApi: SecureUserApi,
        tokensRepository: ITokensRepository
    ): GetUserInfoUseCase {
        return GetUserInfoUseCase(tokensRepository)
    }
}