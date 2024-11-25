package com.example.allmighty.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.allmighty.calculator.presentation.model.PlayerUi
import com.example.allmighty.calculator.presentation.model.RecordUi
import com.example.allmighty.calculator.presentation.model.RoundUi
import com.example.allmighty.calculator.presentation.model.TrumpSuit
import com.example.allmighty.calculator.presentation.model.findTrumpSuit
import com.example.allmighty.calculator.presentation.model.toDisplayableTime
import java.time.LocalDateTime
import java.time.ZoneId

@Entity(tableName = "record_database")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val createdTime: Long = LocalDateTime.now()
        .atZone(ZoneId.systemDefault())
        .toInstant()?.toEpochMilli() ?: 0,
    val playerList: List<String> = emptyList(),
    val playerScoreList: List<Int> = emptyList(),
    val roundList: List<Round> = emptyList()
)

fun Record.toRecordUi(): RecordUi {
    return RecordUi(
        id = id,
        title = title,
        createdTime = createdTime.toDisplayableTime(),
        playerUiList = playerList.mapIndexed { index, player ->
            PlayerUi(player, playerScoreList[index])
        },
        roundUiList = roundList.map { round ->
            RoundUi(
                playerNameList = playerList,
                mightyPlayerIndex = round.mightyPlayerIndex,
                friendPlayerIndex = round.friendPlayerIndex,
                trumpSuit = round.trumpSuit.findTrumpSuit() ?: TrumpSuit.스페이드,
                pledgeNumber = round.pledgeNumber,
                actualNumber = round.actualNumber,
                scoreChange = round.getScoreChange()
            )
        }
    )
}