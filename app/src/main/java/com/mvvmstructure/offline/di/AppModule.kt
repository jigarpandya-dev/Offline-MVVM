package com.mvvmstructure.offline.di

import android.content.Context
import androidx.room.Room
import com.mvvmstructure.offline.BuildConfig
import com.mvvmstructure.offline.data.local.database.Database
import com.mvvmstructure.offline.data.local.database.DatabaseManager
import com.mvvmstructure.offline.data.local.database.RoomDatabase
import com.mvvmstructure.offline.data.remote.Api
import com.mvvmstructure.offline.data.remote.ApiManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {


    @Singleton
    @Provides
    fun provideAppRoomDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            RoomDatabase::class.java,
            "app.db"
        ).build()
    }


    @Singleton
    @Provides
    fun provideAppDatabase(roomDatabase: RoomDatabase): Database {
        return DatabaseManager(roomDatabase)
    }

    @Singleton
    @Provides
    fun provideAppApi(): Api {
        return ApiManager()
    }
}