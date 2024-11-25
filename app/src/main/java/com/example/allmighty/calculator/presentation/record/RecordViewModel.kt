package com.example.allmighty.calculator.presentation.record

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.domain.model.toRecordUi
import com.example.allmighty.calculator.presentation.model.RecordUi
import com.example.allmighty.calculator.presentation.model.RoundUi
import com.example.allmighty.calculator.presentation.model.toDisplayableNumber
import com.example.allmighty.calculator.presentation.model.toRecord
import com.example.allmighty.calculator.presentation.util.PlayerUtil.PLAYER_COUNT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val recordDao: RecordDao,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(RecordState())
    val state = _state
        .onStart {
            initRecord()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RecordState()
        )

    private fun initRecord() {

        viewModelScope.launch {
            val recordUiId = savedStateHandle.get<String>("recordUiId")

            val record = recordUiId?.let {
                recordDao.getRecordById(it)
            }

            record?.let { thisRecord ->
                _state.update {
                    it.copy(
                        recordUi = thisRecord.toRecordUi()
                    )
                }
            }

        }

    }

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
                playerNameList = _state.value.recordUi.playerUiList.map { player -> player.name },
                scoreChange = List(PLAYER_COUNT) { 0.toDisplayableNumber() }
            )
        )

        val playerList = _state.value.recordUi.playerUiList.toMutableList()

        val recordUi = RecordUi(
            id = _state.value.recordUi.id,
            title = _state.value.recordUi.title,
            createdTime = _state.value.recordUi.createdTime,
            playerUiList = playerList,
            roundUiList = mutableRoundUiList
        )

        viewModelScope.launch {
            recordDao.updateRecord(recordUi.toRecord())

            _state.update {
                it.copy(
                    recordUi = recordUi
                )
            }
        }

    }
}