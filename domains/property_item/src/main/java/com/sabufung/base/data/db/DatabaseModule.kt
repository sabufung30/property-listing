package com.sabufung.base.data.db

import android.app.Application
import androidx.room.Room
import com.sabufung.base.data.db.daos.PropertyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideHomegateDatabase(context: Application): HomegateDatabase = Room
        .databaseBuilder(context, HomegateDatabase::class.java, HomegateDatabase.DATABASE_NAME)
        .build()


    @Provides
    fun providePropertyDao(
        homegateDatabase: HomegateDatabase
    ): PropertyDao = homegateDatabase.propertyDao()

}