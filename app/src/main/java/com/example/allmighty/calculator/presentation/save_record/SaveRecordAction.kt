package com.example.allmighty.calculator.presentation.save_record

sealed interface SaveRecordAction {
    data class OnPlayerNameChange(val index: Int, val playerName: String) : SaveRecordAction
    data class OnRecordTitleChange(val recordTitle: String) : SaveRecordAction
    data object OnSaveRecord : SaveRecordAction
}