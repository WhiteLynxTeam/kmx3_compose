package com.kmx3.compose.di

import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.usecases.CreateUserUseCase
import com.kmx3.compose.domain.usecases.DeleteUserUseCase
import com.kmx3.compose.domain.usecases.GetUserUseCase
import com.kmx3.compose.domain.usecases.UpdateUserUseCase
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
    fun provideGetUserUseCase(repository: IUserRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideCreateUserUseCase(repository: IUserRepository): CreateUserUseCase {
        return CreateUserUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideUpdateUserUseCase(repository: IUserRepository): UpdateUserUseCase {
        return UpdateUserUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideDeleteUserUseCase(repository: IUserRepository): DeleteUserUseCase {
        return DeleteUserUseCase(repository)
    }
}