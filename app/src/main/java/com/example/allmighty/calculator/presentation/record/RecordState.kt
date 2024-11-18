package com.example.allmighty.calculator.presentation.record

import com.example.allmighty.calculator.presentation.model.RecordUi
import com.example.allmighty.calculator.presentation.model.RoundUi

data class RecordState(
    val recordUi: RecordUi = RecordUi(),
    val selectedRound: RoundUi? = null
)