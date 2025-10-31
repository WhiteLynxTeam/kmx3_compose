package com.kmx3.compose.data.repositories

import com.kmx3.compose.data.local.datastores.TokenDataStore
import com.kmx3.compose.data.local.model.Token
import com.kmx3.compose.domain.irepositories.ITokensRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDataStore: TokenDataStore
) : ITokensRepository {
    
    override val token: Flow<Token?> = tokenDataStore.token.map { tokenString ->
        tokenString?.let { Token(it) }
    }

    override suspend fun saveToken(token: Token) {
        tokenDataStore.saveToken(token.value)
    }

    override suspend fun clearToken() {
        tokenDataStore.clearToken()
    }
}