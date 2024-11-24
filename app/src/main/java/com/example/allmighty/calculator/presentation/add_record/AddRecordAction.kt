package com.example.allmighty.calculator.presentation.add_record

sealed interface AddRecordAction {
    data class OnPlayerNameChange(val index: Int, val playerName: String) : AddRecordAction
    data class OnRecordTitleChange(val recordTitle: String) : AddRecordAction
    data object OnAddRecord : AddRecordAction
}