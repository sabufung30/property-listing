package com.sabufung.base.data.db

import androidx.room.TypeConverter

class StringArrayTypeConverter {

    @TypeConverter
    fun fromArray(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toArray(value: String): List<String> {
        return value.split(",")
    }

}