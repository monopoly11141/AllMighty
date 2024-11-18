package com.example.allmighty.calculator.presentation.model

data class RoundUi(
    val playerNameList: List<String> = emptyList(),
    val mightyPlayerIndex: Int = -1,
    val friendPlayerIndex: Int = -1,
    val trumpSuit: String = "",
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