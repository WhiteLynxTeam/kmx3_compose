package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.DomainResult
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.irepositories.ITokensRepository
import com.kmx3.compose.domain.irepositories.IUserProfileRepository
import com.kmx3.compose.domain.models.User
import javax.inject.Inject

class LoginWithProfileUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val tokensRepository: ITokensRepository,
    private val userProfileRepository: IUserProfileRepository,
    private val authApiUseCase: AuthApiUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) {
    suspend operator fun invoke(user: User): DomainResult<Unit> {
        // Сначала аутентифицируем пользователя
        val authResult = authApiUseCase(user)
        
        return when (authResult) {
            is DomainResult.Success -> {
                // Если аутентификация успешна, получаем информацию о пользователе
                val userInfoResult = getUserInfoUseCase()
                
                when (userInfoResult) {
                    is DomainResult.Success -> {
                        // Сохраняем профиль пользователя в локальное хранилище
                        userProfileRepository.saveUserProfile(userInfoResult.data)
                        DomainResult.Success(Unit)
                    }
                    else -> userInfoResult as DomainResult<Unit>
                }
            }
            else -> {
                // Если аутентификация не удалась, возвращаем ошибку
                authResult as DomainResult<Unit>
            }
        }
    }
}