package com.example.allmighty.calculator.presentation.record

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.presentation.model.RecordUi
import com.example.allmighty.calculator.presentation.model.RoundUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(RecordState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RecordState()
        )

    fun onAction(action: RecordAction) {
        when (action) {
            is RecordAction.OnRoundClick -> {

            }
            is RecordAction.OnAddRoundClick -> {
                onAddRoundClick()
            }
        }
    }

    private fun onAddRoundClick() {
        val mutableRoundUiList = _state.value.recordUi.roundUiList.toMutableList()
        mutableRoundUiList.add(
            RoundUi(
                playerNameList = _state.value.recordUi.playerUiList.map { player -> player.name }
            )
        )

        val playerList = _state.value.recordUi.playerUiList.toMutableList()

        val recordUi = RecordUi(
            playerUiList = playerList,
            roundUiList = mutableRoundUiList
        )

        _state.update{
            it.copy(
                recordUi = recordUi
            )
        }
    }
}