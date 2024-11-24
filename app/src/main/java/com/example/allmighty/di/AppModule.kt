package com.example.allmighty.di

import android.content.Context
import androidx.room.Room
import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.data.db.RecordDb
import com.example.allmighty.calculator.data.repository_impl.RecordRepositoryImpl
import com.example.allmighty.calculator.domain.repository.RecordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesRecordDao(recordDb: RecordDb): RecordDao = recordDb.recordDao

    @Provides
    @Singleton
    fun providesRecordDb(@ApplicationContext context: Context): RecordDb =
        Room.databaseBuilder(context, RecordDb::class.java, "record_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesRecordRepository(recordDao: RecordDao): RecordRepository = RecordRepositoryImpl(recordDao)
}