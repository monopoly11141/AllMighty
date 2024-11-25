package com.example.allmighty.calculator.presentation.add_round

data class AddRoundState(
    val playerNameList: List<String> = emptyList(),
    val mightyPlayerIndex: Int = -1,
    val friendPlayerIndex: Int = -1,
    val trumpSuit: String = "",
    val pledgeNumber: Int = -1,
    val actualNumber: Int = -1,
)