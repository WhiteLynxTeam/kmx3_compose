package com.kmx3.compose.domain.usecases

import com.kmx3.compose.domain.DomainResult
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(): DomainResult<User> {
        return userRepository.getUserInfo()
    }
}