package com.example.allmighty.calculator.presentation.round

data class RoundState(
    val playerNameList: List<String> = emptyList(),
    val mightyPlayerIndex: Int = -1,
    val friendPlayerIndex: Int = -1,
    val trumpSuitIndex: Int = -1,
    val pledgeNumber: Int = 0,
    val actualNumber: Int = 0,
)