package com.kmx3.compose.data.repositories

import com.kmx3.compose.data.mappers.InfoUserResponseToUserMapper
import com.kmx3.compose.data.mappers.UserDomainToAuthRequestMapper
import com.kmx3.compose.data.remote.api.SecureUserApi
import com.kmx3.compose.data.remote.api.UserApi
import com.kmx3.compose.domain.DomainResult
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject constructor(
    @Named("auth") private val userApi: UserApi,
    @Named("api") private val secureUserApi: SecureUserApi,
    private val userDomainToAuthRequestMapper: UserDomainToAuthRequestMapper,
    private val infoUserResponseToUserMapper: InfoUserResponseToUserMapper,
) : IUserRepository {

    /*[red]  Исправить на возврат Токена модели домен слоя*/
    override suspend fun auth(user: User): DomainResult<String> {
        val userAuthRequest = userDomainToAuthRequestMapper.map(user)
            ?: return DomainResult.ValidationError(
                when {
                    user.username.isNullOrBlank() -> "Login is empty"
                    user.password.isNullOrBlank() -> "Password is empty"
                    else -> "Invalid credentials"
                }
            )

        val response = userApi.auth(userAuthRequest)

        return when {
            !response.isSuccessful -> mapResponseError(response)
            else -> {
                val token = response.body()?.token ?: return DomainResult.ValidationError("Token not found")
                DomainResult.Success(token)
            }
        }
    }

    override suspend fun getUserInfo(): DomainResult<User> {
        val response = secureUserApi.info()
        return if (response.isSuccessful) {
            val userInfoList = response.body()
            if (!userInfoList.isNullOrEmpty()) {
                val userInfo = userInfoList[0] // Предполагаем, что возвращается список, берем первого
                val user = infoUserResponseToUserMapper.map(userInfo)
                DomainResult.Success(user)
            } else {
                DomainResult.ValidationError("User info not found in response")
            }
        } else {
            when (response.code()) {
                401 -> DomainResult.UnauthorizedError
                500 -> DomainResult.ServerError(500)
                else -> DomainResult.NetworkError("Error code: ${response.code()}")
            }
        }
    }

    private fun mapResponseError(response: Response<*>): DomainResult<String> =
        when (response.code()) {
            in 400..499 -> DomainResult.UnauthorizedError
            500 -> DomainResult.ServerError(500)
            else -> DomainResult.NetworkError(response.message() ?: "Unknown error")
        }
}