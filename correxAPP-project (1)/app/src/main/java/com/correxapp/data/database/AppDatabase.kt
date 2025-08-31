package com.correxapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.correxapp.data.database.dao.ExamDao
import com.correxapp.data.database.dao.GradedSheetDao
import com.correxapp.data.database.entity.ExamEntity
import com.correxapp.data.database.entity.GradedSheetEntity

@Database(
    entities = [ExamEntity::class, GradedSheetEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun examDao(): ExamDao
    abstract fun gradedSheetDao(): GradedSheetDao
}
