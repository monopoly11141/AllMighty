package com.example.allmighty.calculator.presentation.record

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(RecordState())
    val state = _state.value

    fun onAction(action: RecordAction) {
        when (action) {
            is RecordAction.onRoundClick -> {

            }

        }
    }
}