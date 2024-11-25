package com.example.allmighty.calculator.presentation.model

import com.example.allmighty.calculator.data.model.Round

data class RoundUi(
    val playerNameList: List<String> = emptyList(),
    val mightyPlayerIndex: Int = -1,
    val friendPlayerIndex: Int = -1,
    val trumpSuit: TrumpSuit = TrumpSuit.스페이드,
    val pledgeNumber: Int = -1,
    val actualNumber: Int = -1,
    val scoreChange: List<DisplayableNumber> = emptyList(),
)

data class DisplayableNumber(
    val value: Int,
    val formatted: String
)

fun Int.toDisplayableNumber(): DisplayableNumber {
    val formattedString = if(this >= 0) "+$this" else "$this"
    return DisplayableNumber(
        value = this,
        formatted = formattedString
    )
}

fun RoundUi.toRound() : Round {
    return Round(
        mightyPlayerIndex = this.mightyPlayerIndex,
        friendPlayerIndex = this.friendPlayerIndex,
        trumpSuit = this.trumpSuit.name,
        pledgeNumber = this.pledgeNumber,
        actualNumber = this.actualNumber
    )
}