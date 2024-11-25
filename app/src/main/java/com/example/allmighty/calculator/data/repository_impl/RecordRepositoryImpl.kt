package com.example.allmighty.calculator.data.repository_impl

import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.data.model.Record
import com.example.allmighty.calculator.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl(
    private val recordDao: RecordDao
): RecordRepository {
    override fun getAllRecord(): Flow<List<Record>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecordById(id: String): Record? {
        TODO("Not yet implemented")
    }

    override suspend fun insertRecord(record: Record) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllRecord() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecord(record: Record) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecord(record: Record) {
        TODO("Not yet implemented")
    }
}