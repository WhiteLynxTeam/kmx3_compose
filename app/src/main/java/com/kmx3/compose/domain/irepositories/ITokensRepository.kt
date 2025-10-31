package com.kmx3.compose.domain.irepositories

import com.kmx3.compose.data.local.model.Token
import kotlinx.coroutines.flow.Flow

interface ITokensRepository {
    val token: Flow<Token?>
    suspend fun saveToken(token: Token)
    suspend fun clearToken()
}