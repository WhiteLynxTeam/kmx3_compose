package com.kmx3.compose.domain.irepositories

import com.kmx3.compose.domain.models.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ITokensRepository {
    val tokenCache: StateFlow<String?>
    val token: Flow<Token?>

    suspend fun saveToken(token: String)
    suspend fun clearToken()
}