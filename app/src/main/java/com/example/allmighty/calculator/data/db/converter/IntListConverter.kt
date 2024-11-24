package com.example.allmighty.calculator.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class IntListConverter {
    @TypeConverter
    fun listToJson(value: List<Int>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Int>? {
        return Gson().fromJson(value, Array<Int>::class.java)?.toList()
    }
}