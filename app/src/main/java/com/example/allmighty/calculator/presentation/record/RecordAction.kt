package com.example.allmighty.calculator.presentation.record

import com.example.allmighty.calculator.presentation.model.RoundUi

sealed interface RecordAction {
    data class OnDeleteRoundClick(val roundUi: RoundUi) : RecordAction
    data object GetRecord : RecordAction
}