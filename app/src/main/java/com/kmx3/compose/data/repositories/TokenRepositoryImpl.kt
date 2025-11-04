package com.kmx3.compose.data.repositories

import com.kmx3.compose.data.local.PreferencesDataStore
import com.kmx3.compose.data.local.model.Token
import com.kmx3.compose.domain.irepositories.ITokensRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDataStore: PreferencesDataStore
) : ITokensRepository {
    private val _tokenCache = MutableStateFlow<String?>(null)
    override val tokenCache: StateFlow<String?> = _tokenCache.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            tokenDataStore.token.collect {
                _tokenCache.value = it
            }
        }
    }
    
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
