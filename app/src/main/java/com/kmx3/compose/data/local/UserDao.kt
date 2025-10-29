package com.kmx3.compose.data.local

import androidx.room.*
import com.kmx3.compose.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsersFromDao(): Flow<List<UserEntity>>
    
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserByIdFromDao(id: String): Flow<UserEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserToDao(userEntity: UserEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersToDao(users: List<UserEntity>)
    
    @Update
    suspend fun updateUserToDao(userEntity: UserEntity)
    
    @Delete
    suspend fun deleteUserFromDao(userEntity: UserEntity)
    
    @Query("DELETE FROM users")
    suspend fun deleteAllUsersFromDao()
}