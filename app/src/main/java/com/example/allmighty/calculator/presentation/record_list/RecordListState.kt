package com.example.allmighty.calculator.presentation.record_list

import com.example.allmighty.calculator.presentation.model.RecordUi

data class RecordListState(
    val recordUiList: List<RecordUi> = emptyList()
)