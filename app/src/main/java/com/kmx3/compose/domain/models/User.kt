package com.kmx3.compose.domain.models

import java.util.*

/**
 * Domain entity representing a User
 * This is a pure Kotlin data class that contains business logic and validation
 */
data class User(
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    
    /**
     * Get the full name of the user
     */
    val fullName: String
        get() = "$firstName $lastName"
    
    /**
     * Check if the user is an adult
     */
    val isAdult: Boolean
        get() = age != null && age >= 18
    
    /**
     * Validate user data
     */
    fun validate(): Boolean {
        return firstName.isNotBlank() && 
               lastName.isNotBlank() && 
               email.isNotBlank() && 
               email.isValidEmail()
    }
    
    /**
     * Validate email format
     */
    private fun String.isValidEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
    
    /**
     * Update the user with new values
     */
    fun copyWithUpdates(
        firstName: String = this.firstName,
        lastName: String = this.lastName,
        email: String = this.email,
        age: Int? = this.age,
        isActive: Boolean = this.isActive
    ): User {
        return this.copy(
            firstName = firstName,
            lastName = lastName,
            email = email,
            age = age,
            isActive = isActive,
            updatedAt = System.currentTimeMillis()
        )
    }
}