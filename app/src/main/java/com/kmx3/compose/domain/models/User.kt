package com.kmx3.compose.domain.models

import java.util.*

data class User(
//    val id: String = UUID.randomUUID().toString(),
    val username: String? = null,
    val password: String? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    
    /**
     * Validate user data
     */
    fun validate(): Boolean {
        return username?.isNotBlank() == true &&
                password?.isNotBlank() == true
    }
    
    /**
     * Validate email format
     * Надо запомнить эту валидацию email. Самая простая из всех.
     */
//    private fun String.isValidEmail(): Boolean {
//        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
//    }
    
    /**
     * Update the user with new values
     */
    fun copyWithUpdates(
        username: String? = this.username,
        password: String? = this.password,
        isActive: Boolean = this.isActive
    ): User {
        return this.copy(
            username = username,
            password = password,
            isActive = isActive,
            updatedAt = System.currentTimeMillis()
        )
    }
}