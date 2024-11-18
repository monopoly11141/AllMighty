package com.example.allmighty.calculator.presentation.record

import com.example.allmighty.calculator.presentation.model.RoundUi

sealed interface RecordAction {
    data class onRoundClick(val roundUi: RoundUi) : RecordAction
}