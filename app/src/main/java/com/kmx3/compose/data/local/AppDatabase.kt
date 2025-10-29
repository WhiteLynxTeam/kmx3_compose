package com.kmx3.compose.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kmx3.compose.data.local.model.UserEntity
import com.kmx3.compose.di.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}