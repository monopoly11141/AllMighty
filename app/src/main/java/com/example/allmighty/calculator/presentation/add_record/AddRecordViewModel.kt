package com.example.allmighty.calculator.presentation.add_record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.domain.model.Record
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
class AddRecordViewModel @Inject constructor(
    private val recordDao: RecordDao
) : ViewModel() {
    private val _state = MutableStateFlow(AddRecordState())
    val state = _state
        .onStart {
            initPlayerList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AddRecordState()
        )

    fun onAction(action: AddRecordAction) {
        when (action) {
            is AddRecordAction.OnRecordTitleChange -> {
                onRecordTitleChange(action.recordTitle)
            }

            is AddRecordAction.OnPlayerNameChange -> {
                onPlayerNameChange(action.index, action.playerName)
            }

            is AddRecordAction.OnAddRecord -> {
                onAddRecordAction()
            }
        }
    }

    private fun initPlayerList() {

        _state.update {
            it.copy(
                playerNameList = List(PLAYER_COUNT) { "" }
            )
        }

    }

    private fun onRecordTitleChange(recordTitle: String) {
        _state.update {
            it.copy(
                recordTitle = recordTitle
            )
        }
    }

    private fun onPlayerNameChange(index: Int, playerName: String) {
        val playerNameMutableList = _state.value.playerNameList.toMutableList()
        playerNameMutableList[index] = playerName

        _state.update {
            it.copy(
                playerNameList = playerNameMutableList
            )
        }
    }

    private fun onAddRecordAction() {
        val record = Record(
            title = _state.value.recordTitle,
            playerList = _state.value.playerNameList,
            playerScoreList = List(PLAYER_COUNT) { 0 }
        )
        viewModelScope.launch {
            recordDao.insertRecord(record)
        }
    }
}