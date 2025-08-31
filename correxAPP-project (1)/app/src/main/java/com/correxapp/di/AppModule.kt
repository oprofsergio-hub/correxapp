package com.correxapp.di

import com.correxapp.core.omr.OmrProcessor
import com.correxapp.data.database.dao.ExamDao
import com.correxapp.data.database.dao.GradedSheetDao
import com.correxapp.data.repository.ExamRepositoryImpl
import com.correxapp.data.repository.GradedSheetRepositoryImpl
import com.correxapp.domain.repository.ExamRepository
import com.correxapp.domain.repository.GradedSheetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton fun provideOmrProcessor(): OmrProcessor = OmrProcessor()

    @Provides @Singleton
    fun provideExamRepository(dao: ExamDao): ExamRepository = ExamRepositoryImpl(dao)

    @Provides @Singleton
    fun provideGradedSheetRepository(dao: GradedSheetDao): GradedSheetRepository = GradedSheetRepositoryImpl(dao)
}
