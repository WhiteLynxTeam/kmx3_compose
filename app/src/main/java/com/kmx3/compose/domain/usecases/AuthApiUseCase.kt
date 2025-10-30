package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User


class AuthApiUseCase(
    private val repository: IUserRepository,
//    private val saveTokenPrefUseCase: SaveTokenPrefUseCase,
//    private val checkRoleApiUseCase: CheckRoleApiUseCase,
) {
    suspend operator fun invoke(user: User): Boolean {

        val result = repository.auth(user)

        if (result.isSuccess) {
            val token = result.getOrNull()
            if (token != null) {
//                saveTokenPrefUseCase(token.token)
//
//                val checkFlag = checkRoleApiUseCase(token)
                return true
            }
        }
        return false
    }
}