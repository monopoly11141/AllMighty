package com.example.allmighty.calculator.presentation.model

import com.example.allmighty.calculator.domain.model.Record
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

data class RecordUi(
    val id: Int = 0,
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

fun RecordUi.toRecord(): Record {
    return Record(
        id = this.id,
        title = this.title,
        createdTime = this.createdTime.value,
        playerList = this.playerUiList.map { playerUi -> playerUi.name },
        playerScoreList = this.playerUiList.map { playerUi -> playerUi.score },
        roundList = this.roundUiList.map { roundUiList -> roundUiList.toRound() }
    )
}