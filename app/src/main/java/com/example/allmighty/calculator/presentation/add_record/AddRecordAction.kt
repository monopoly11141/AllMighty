package com.example.allmighty.calculator.presentation.add_record

sealed interface AddRecordAction {
    data class onPlayerNameChange(val index: Int, val name: String) : AddRecordAction
    data class onRecordTitleChange(val recordTitle: String) : AddRecordAction
    data object onAddRecordAction : AddRecordAction
}