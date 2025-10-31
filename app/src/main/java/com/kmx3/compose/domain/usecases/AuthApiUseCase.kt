package com.kmx3.compose.domain.usecases

import com.kmx3.compose.data.local.model.Token
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.irepositories.ITokensRepository
import com.kmx3.compose.domain.models.User


class AuthApiUseCase(
    private val userRepository: IUserRepository,
    private val tokensRepository: ITokensRepository,
) {
    suspend operator fun invoke(user: User): Boolean {

        val result = userRepository.auth(user)

        if (result.isSuccess) {
            val token = result.getOrNull()
            if (token != null) {
                // Сохраняем токен в хранилище
                tokensRepository.saveToken(Token(token.token))
                return true
            }
        }
        return false
    }
}