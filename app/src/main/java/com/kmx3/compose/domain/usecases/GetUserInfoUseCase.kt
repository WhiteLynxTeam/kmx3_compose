package com.kmx3.compose.domain.usecases

import com.kmx3.compose.data.remote.SecureUserApi
import com.kmx3.compose.domain.irepositories.ITokensRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val secureUserApi: SecureUserApi,
    private val tokensRepository: ITokensRepository
) {
    suspend operator fun invoke() = 
        tokensRepository.token.firstOrNull()?.let { token ->
            secureUserApi.info("Bearer ${token.value}")
        }
}