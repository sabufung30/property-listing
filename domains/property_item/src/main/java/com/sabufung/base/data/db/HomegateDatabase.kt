package com.sabufung.base.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sabufung.base.data.db.daos.PropertyDao
import com.sabufung.base.data.db.model.PropertyEntity


@Database(
    entities = [
        PropertyEntity::class
    ],
    version = 1
)
@TypeConverters(StringArrayTypeConverter::class)
abstract class HomegateDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao

    companion object {
        const val DATABASE_NAME = "homegate_database.db"
    }
}