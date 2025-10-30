package com.kmx3.compose.data.repositories

import com.kmx3.compose.data.local.UserDao
import com.kmx3.compose.data.mappers.UserDomainToEntityMapper
import com.kmx3.compose.data.mappers.UserEntityToDomainMapper
import com.kmx3.compose.data.mappers.UserResponseDtoToDomainMapper
import com.kmx3.compose.data.mappers.UserDomainToRequestMapper
import com.kmx3.compose.data.remote.UserApi
import com.kmx3.compose.data.remote.UserApiService
import com.kmx3.compose.domain.irepositories.IUserRepository
import com.kmx3.compose.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @Named("auth") private val userApi: UserApi,
    private val userApiService: UserApiService,
    private val userDao: UserDao,
    private val userResponseDtoToDomainMapper: UserResponseDtoToDomainMapper,
    private val userDomainToEntityMapper: UserDomainToEntityMapper,
    private val userEntityToDomainMapper: UserEntityToDomainMapper,
    private val userDomainToRequestMapper: UserDomainToRequestMapper
) : IUserRepository {
    
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsersFromDao()
            .map { entities -> entities.map { userEntityToDomainMapper.map(it) } }
    }
    
    override fun getUserById(id: String): Flow<User?> {
        return userDao.getUserByIdFromDao(id)
            .map { entity -> entity?.let { userEntityToDomainMapper.map(it) } }
    }
    
    override suspend fun insertUser(user: User): Long {
        val userEntity = userDomainToEntityMapper.map(user)
        userDao.insertUserToDao(userEntity)
        return 1L
    }
    
    override suspend fun insertUsers(users: List<User>) {
        val userEntities = users.map { userDomainToEntityMapper.map(it) }
        userDao.insertUsersToDao(userEntities)
    }
    
    override suspend fun updateUser(user: User) {
        val userEntity = userDomainToEntityMapper.map(user)
        userDao.updateUserToDao(userEntity)
    }
    
    override suspend fun deleteUser(user: User) {
        val userEntity = userDomainToEntityMapper.map(user)
        userDao.deleteUserFromDao(userEntity)
    }
    
    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsersFromDao()
    }
    
    suspend fun syncUsersFromApi() {
        try {
            val usersResponse = userApiService.getAllUsersFromApi()
            val users = usersResponse.map { userResponseDtoToDomainMapper.map(it) }
            val userEntities = users.map { userDomainToEntityMapper.map(it) }
            userDao.insertUsersToDao(userEntities)
        } catch (e: Exception) {
            // Обработка ошибки
            e.printStackTrace()
        }
    }
    
    suspend fun createUserOnApi(user: User): User {
        val userRequest = userDomainToRequestMapper.mapToCreateRequest(user)
        val responseDto = userApiService.createUserToApi(userRequest)
        return userResponseDtoToDomainMapper.map(responseDto)
    }
    
    suspend fun updateUserOnApi(user: User): User {
        val userRequest = userDomainToRequestMapper.mapToUpdateRequest(user)
        val responseDto = userApiService.updateUserToApi(userRequest.id, userRequest)
        return userResponseDtoToDomainMapper.map(responseDto)
    }
    
    suspend fun deleteUserFromApi(id: String) {
        userApiService.deleteUserFromApi(id)
    }
    
    override suspend fun auth(user: User) = userApi.auth(
        com.kmx3.compose.data.remote.model.user.request.AuthUserRequest(
            user.username ?: "", 
            user.password ?: ""
        )
    )
}