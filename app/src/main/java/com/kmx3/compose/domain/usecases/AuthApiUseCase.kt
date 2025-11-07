package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.DomainResult
import com.kmx3.compose.domain.irepositories.ITokensRepository
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User


class AuthApiUseCase(
    private val userRepository: IUserRepository,
    private val tokensRepository: ITokensRepository,
) {
    suspend operator fun invoke(user: User): DomainResult<*> {
        val result = userRepository.auth(user)
        if (result is DomainResult.Success) {
            tokensRepository.saveToken(result.data.token)
        }
        return result
    }
}
