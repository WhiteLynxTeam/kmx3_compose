package com.kmx3.compose.data.mappers

import com.kmx3.compose.data.remote.model.user.response.InfoUserResponse
import com.kmx3.compose.domain.models.User
import javax.inject.Inject

class InfoUserResponseToUserMapper @Inject constructor() {
    fun map(infoUserResponse: InfoUserResponse): User {
        return User(
            fullName = infoUserResponse.full_name,
            email = infoUserResponse.email,
            phone = infoUserResponse.phone,
            photo = infoUserResponse.photo,
            officeName = infoUserResponse.office_name,
            officeLocation = infoUserResponse.office_location,
            department = infoUserResponse.department,
            officeQuota = infoUserResponse.office_quota,
            officeUsedQuota = infoUserResponse.office_used_quota,
            role = infoUserResponse.role
        )
    }
}