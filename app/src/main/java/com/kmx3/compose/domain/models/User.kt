package com.kmx3.compose.domain.models

import java.util.*

data class User(
//    val id: String = UUID.randomUUID().toString(),
    val username: String? = null,
    val password: String? = null,
    val fullName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val photo: String? = null,
    val officeName: String? = null,
    val officeLocation: String? = null,
    val department: String? = null,
    val officeQuota: String? = null,
    val officeUsedQuota: String? = null,
    val role: String? = null,
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
        fullName: String? = this.fullName,
        email: String? = this.email,
        phone: String? = this.phone,
        photo: String? = this.photo,
        officeName: String? = this.officeName,
        officeLocation: String? = this.officeLocation,
        department: String? = this.department,
        officeQuota: String? = this.officeQuota,
        officeUsedQuota: String? = this.officeUsedQuota,
        role: String? = this.role,
        isActive: Boolean = this.isActive
    ): User {
        return this.copy(
            username = username,
            password = password,
            fullName = fullName,
            email = email,
            phone = phone,
            photo = photo,
            officeName = officeName,
            officeLocation = officeLocation,
            department = department,
            officeQuota = officeQuota,
            officeUsedQuota = officeUsedQuota,
            role = role,
            isActive = isActive,
            updatedAt = System.currentTimeMillis()
        )
    }
}