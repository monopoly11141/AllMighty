package com.example.allmighty.calculator.presentation.record_list

sealed interface RecordListAction {
    data object OnCreateRecord: RecordListAction
}