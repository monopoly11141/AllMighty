package com.example.allmighty.calculator.presentation.add_record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AddRecordViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(AddRecordState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AddRecordState()
        )

    fun onAction(action: AddRecordAction) {
        when (action) {
            is AddRecordAction.onAddRecordAction -> TODO()
            is AddRecordAction.onPlayerNameChange -> TODO()
            is AddRecordAction.onRecordTitleChange -> TODO()
        }
    }
}