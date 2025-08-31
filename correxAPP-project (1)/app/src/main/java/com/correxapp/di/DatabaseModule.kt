package com.correxapp.di

import android.content.Context
import androidx.room.Room
import com.correxapp.data.database.AppDatabase
import com.correxapp.data.database.dao.ExamDao
import com.correxapp.data.database.dao.GradedSheetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides @Singleton
    fun provideDb(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "correx.db").build()

    @Provides fun provideExamDao(db: AppDatabase): ExamDao = db.examDao()
    @Provides fun provideGradedSheetDao(db: AppDatabase): GradedSheetDao = db.gradedSheetDao()
}
