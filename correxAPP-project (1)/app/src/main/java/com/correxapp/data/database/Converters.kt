package com.correxapp.data.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun mapToString(map: Map<Int, Int>?): String? = map?.entries?.joinToString(";"){ "${it.key}:${it.value}" }

    @TypeConverter
    fun stringToMap(data: String?): Map<Int, Int>? =
        data?.split(";")?.mapNotNull {
            val parts = it.split(":")
            if (parts.size == 2) parts[0].toIntOrNull()?.let { k -> parts[1].toIntOrNull()?.let { v -> k to v } } else null
        }?.toMap()
}
