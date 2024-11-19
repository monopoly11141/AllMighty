package com.example.allmighty.calculator.presentation.model

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

data class RecordUi(
    val id: String = "",
    val title: String = "",
    val createdTime: DisplayableTime = DisplayableTime(),
    val playerUiList: List<PlayerUi> = emptyList(),
    val roundUiList: List<RoundUi> = emptyList()
)

data class DisplayableTime(
    val value: Long = LocalDateTime.now()
        .atZone(ZoneId.systemDefault())
        .toInstant()?.toEpochMilli() ?: 0,
    val formatted: String = ""
)

fun Long.toDisplayableTime(): DisplayableTime {
    val dateTime = java.util.Date(this)
    val format = SimpleDateFormat("yy/MM/dd HH:mm", Locale.getDefault())
    return DisplayableTime(
        value = this,
        formatted = format.format(dateTime)
    )
}