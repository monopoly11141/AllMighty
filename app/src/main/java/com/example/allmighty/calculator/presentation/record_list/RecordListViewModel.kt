package com.example.allmighty.calculator.presentation.record_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.presentation.record.RecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecordListViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(RecordListState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RecordListState()
        )

}