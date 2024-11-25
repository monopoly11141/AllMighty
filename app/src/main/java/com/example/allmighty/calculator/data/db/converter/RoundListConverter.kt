package com.example.allmighty.calculator.data.db.converter

import androidx.room.TypeConverter
import com.example.allmighty.calculator.data.model.Round
import com.google.gson.Gson

class RoundListConverter {

    @TypeConverter
    fun listToJson(value: List<Round>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Round>? {
        return Gson().fromJson(value, Array<Round>::class.java)?.toList()
    }
}