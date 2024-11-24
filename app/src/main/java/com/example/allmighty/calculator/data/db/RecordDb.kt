package com.example.allmighty.calculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.allmighty.calculator.data.db.converter.IntListConverter
import com.example.allmighty.calculator.data.db.converter.RoundListConverter
import com.example.allmighty.calculator.data.db.converter.StringListConverter
import com.example.allmighty.calculator.domain.model.Record

@Database(
    entities = [Record::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    IntListConverter::class,
    StringListConverter::class,
    RoundListConverter::class
)
abstract class RecordDb : RoomDatabase() {
    abstract val recordDao: RecordDao
}