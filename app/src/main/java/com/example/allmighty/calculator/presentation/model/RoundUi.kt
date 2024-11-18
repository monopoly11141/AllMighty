package com.example.allmighty.calculator.presentation.model

import java.text.NumberFormat
import java.util.Locale

data class RoundUi(
    val players: List<Player>,
    val mightyPlayerIndex: Int,
    val friendPlayerIndex: Int,
    val trumpSuit: String,
    val pledgeNumber: Int,
    val actualNumber: Int,
    val scoreChange: List<DisplayableNumber>,
)

data class Player(
    val name: String,
    val score: Int
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