package com.example.allmighty.calculator.domain.repository

import com.example.allmighty.calculator.data.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    fun getAllRecord(): Flow<List<Record>>

    suspend fun getRecordById(id: String) : Record?

    suspend fun insertRecord(record: Record)

    suspend fun deleteAllRecord()

    suspend fun deleteRecord(record: Record)

    suspend fun updateRecord(record: Record)
}