package com.example.allmighty.calculator.presentation.round_list

import com.example.allmighty.calculator.presentation.model.RoundUi

sealed interface RoundListAction {
    data class onRoundClick(val roundUi: RoundUi) : RoundListAction
}