package com.kmx3.compose.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kmx3.compose.domain.models.User
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
        // Ключи для токена
        val TOKEN_KEY = stringPreferencesKey("token")
        
        // Ключи для профиля пользователя
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val USER_FULL_NAME_KEY = stringPreferencesKey("user_full_name")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_PHONE_KEY = stringPreferencesKey("user_phone")
        val USER_PHOTO_KEY = stringPreferencesKey("user_photo")
        val USER_OFFICE_NAME_KEY = stringPreferencesKey("user_office_name")
        val USER_OFFICE_LOCATION_KEY = stringPreferencesKey("user_office_location")
        val USER_DEPARTMENT_KEY = stringPreferencesKey("user_department")
    }

    // Методы для работы с токеном
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

    // Методы для работы с профилем пользователя
    val userProfile: Flow<User?> = context.dataStore.data
        .map { preferences ->
            val username = preferences[PreferencesKeys.USER_NAME_KEY]
            val fullName = preferences[PreferencesKeys.USER_FULL_NAME_KEY]
            val email = preferences[PreferencesKeys.USER_EMAIL_KEY]
            val phone = preferences[PreferencesKeys.USER_PHONE_KEY]
            val photo = preferences[PreferencesKeys.USER_PHOTO_KEY]
            val officeName = preferences[PreferencesKeys.USER_OFFICE_NAME_KEY]
            val officeLocation = preferences[PreferencesKeys.USER_OFFICE_LOCATION_KEY]
            val department = preferences[PreferencesKeys.USER_DEPARTMENT_KEY]

            if (username != null) {
                User(
                    username = username,
                    fullName = fullName,
                    email = email,
                    phone = phone,
                    photo = photo,
                    officeName = officeName,
                    officeLocation = officeLocation,
                    department = department
                )
            } else {
                null
            }
        }

    suspend fun saveUserProfile(user: User) {
        context.dataStore.edit { preferences ->
            user.username?.let { preferences[PreferencesKeys.USER_NAME_KEY] = it }
            user.fullName?.let { preferences[PreferencesKeys.USER_FULL_NAME_KEY] = it }
            user.email?.let { preferences[PreferencesKeys.USER_EMAIL_KEY] = it }
            user.phone?.let { preferences[PreferencesKeys.USER_PHONE_KEY] = it }
            user.photo?.let { preferences[PreferencesKeys.USER_PHOTO_KEY] = it }
            user.officeName?.let { preferences[PreferencesKeys.USER_OFFICE_NAME_KEY] = it }
            user.officeLocation?.let { preferences[PreferencesKeys.USER_OFFICE_LOCATION_KEY] = it }
            user.department?.let { preferences[PreferencesKeys.USER_DEPARTMENT_KEY] = it }
        }
    }

    suspend fun clearUserProfile() {
        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.USER_NAME_KEY)
            preferences.remove(PreferencesKeys.USER_FULL_NAME_KEY)
            preferences.remove(PreferencesKeys.USER_EMAIL_KEY)
            preferences.remove(PreferencesKeys.USER_PHONE_KEY)
            preferences.remove(PreferencesKeys.USER_PHOTO_KEY)
            preferences.remove(PreferencesKeys.USER_OFFICE_NAME_KEY)
            preferences.remove(PreferencesKeys.USER_OFFICE_LOCATION_KEY)
            preferences.remove(PreferencesKeys.USER_DEPARTMENT_KEY)
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
