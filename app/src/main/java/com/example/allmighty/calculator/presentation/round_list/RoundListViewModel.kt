package com.example.allmighty.calculator.presentation.round_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RoundListViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(RoundListState())
    val state = _state.value

    fun onAction(action: RoundListAction) {
        when (action) {
            is RoundListAction.onRoundClick -> {

            }

        }
    }
}