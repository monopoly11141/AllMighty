package com.example.allmighty.calculator.presentation.model

data class RoundUi(
    val players: List<Player>,
    val mightyPlayerIndex: Int,
    val friendPlayerIndex: Int,
    val trumpSuit: String,
    val pledgeNumber: Int,
    val actualNumber: Int
)

data class Player(
    val name: String,
    val score: Int
)