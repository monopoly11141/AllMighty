package com.example.allmighty.calculator.presentation.round_list

import com.example.allmighty.calculator.presentation.model.RoundUi

data class RoundListState(
    val roundList: List<RoundUi> = emptyList(),
    val selectedRound : RoundUi? = null
)