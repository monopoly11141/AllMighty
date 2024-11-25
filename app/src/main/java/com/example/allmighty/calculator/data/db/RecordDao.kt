package com.example.allmighty.calculator.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.allmighty.calculator.data.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM record_database")
    fun getAllRecord(): Flow<List<Record>>

    @Query("SELECT * FROM record_database WHERE id = :id LIMIT 1")
    suspend fun getRecordById(id: String): Record?

    @Insert
    suspend fun insertRecord(record: Record)

    @Query("DELETE FROM record_database")
    suspend fun deleteAllRecord()

    @Delete
    suspend fun deleteRecord(record: Record)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecord(record: Record)

}