package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.DomainResult
import com.kmx3.compose.domain.irepositories.ITokensRepository
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User

class AuthApiUseCase(
    private val userRepository: IUserRepository,
    private val tokensRepository: ITokensRepository,
) {
    suspend operator fun invoke(user: User): DomainResult<Unit> {
        val result = userRepository.auth(user)
        return when (result) {
            is DomainResult.Success -> {
                tokensRepository.saveToken(result.data)
                DomainResult.Success(Unit)
            }
            else -> result as DomainResult<Unit>
        }
    }
}