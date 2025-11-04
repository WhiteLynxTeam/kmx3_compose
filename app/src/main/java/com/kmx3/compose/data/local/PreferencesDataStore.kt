package com.kmx3.compose.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "miel_preferences")

@Singleton
class PreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    /*** Типобезопасный с явными методами для каждой сущности */
    private object PreferencesKeys {
        val TOKEN_KEY = stringPreferencesKey("token")
    }

    val token: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY]
        }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY] = token
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.TOKEN_KEY)
        }
    }


    /***  Универсальный алгоритм с кэшированием ключей
     * при создании ключей по строкам динамически и много
     * для простых случаев не применяем. Но пусть будет на будущее
     * */
/*    private val keysCache = mutableMapOf<String, Preferences.Key<String>>()

    private fun getStringKey(key: String): Preferences.Key<String> {
        return keysCache.getOrPut(key) { stringPreferencesKey(key) }
    }

    fun getString(key: String): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[getStringKey(key)]
        }

    suspend fun saveString(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[getStringKey(key)] = value
        }
    }

    suspend fun clearKey(key: String) {
        context.dataStore.edit { preferences ->
            preferences.remove(getStringKey(key))
        }
    }

    *//*** token *//*
    val token: Flow<String?> = getString("token")

    suspend fun saveToken(token: String) = saveString("token", token)

    suspend fun clearToken() = clearKey("token")

    *//*** refresh token *//*

    val refreshToken: Flow<String?> = getString("refresh_token")

    suspend fun saveRefreshToken(token: String) = saveString("refresh_token", token)

    suspend fun clearRefreshToken() = clearKey("refresh_token")*/
}
