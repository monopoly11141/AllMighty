package com.example.allmighty.calculator.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.allmighty.calculator.presentation.model.PlayerUi
import com.example.allmighty.calculator.presentation.model.RecordUi
import com.example.allmighty.calculator.presentation.model.RoundUi
import com.example.allmighty.calculator.presentation.model.toDisplayableTime

@Entity(tableName = "record_database")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val createdTime: Long,
    val playerList: List<String>,
    val playerScoreList: List<Int>,
    val roundList: List<Round>
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
                trumpSuit = round.trumpSuit,
                pledgeNumber = round.pledgeNumber,
                actualNumber = round.actualNumber,
                scoreChange = round.getScoreChange()
            )
        }
    )
}