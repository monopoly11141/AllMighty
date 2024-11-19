package com.example.allmighty.calculator.presentation.record

import androidx.lifecycle.ViewModel
import com.example.allmighty.calculator.presentation.model.DisplayableNumber
import com.example.allmighty.calculator.presentation.model.RecordUi
import com.example.allmighty.calculator.presentation.model.RoundUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
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
            is RecordAction.onAddRoundClick -> {
                val mutableRoundUiList = _state.value.recordUi.roundUiList.toMutableList()
                mutableRoundUiList.add(
                    RoundUi(
                        playerNameList = _state.value.recordUi.playerUiList.map {player -> player.name}
                    )
                )

                val playerList = _state.value.recordUi.playerUiList.toMutableList()

                val recordUi = RecordUi(
                    playerUiList = playerList,
                    roundUiList = mutableRoundUiList
                )

                _state.update {
                    it.copy(
                        recordUi = recordUi
                    )
                }
            }
        }
    }
}