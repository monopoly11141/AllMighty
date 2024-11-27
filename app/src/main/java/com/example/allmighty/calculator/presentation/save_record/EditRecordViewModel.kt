package com.example.allmighty.calculator.presentation.save_record

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.data.model.Record
import com.example.allmighty.calculator.presentation.core.util.PlayerUtil.PLAYER_COUNT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditRecordViewModel @Inject constructor(
    private val recordDao: RecordDao,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(SaveRecordState())
    val state = _state
        .onStart {
            initState()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            SaveRecordState()
        )


    fun onAction(action: SaveRecordAction) {
        when (action) {
            is SaveRecordAction.OnRecordTitleChange -> {
                onRecordTitleChange(action.recordTitle)
            }

            is SaveRecordAction.OnPlayerNameChange -> {
                onPlayerNameChange(action.index, action.playerName)
            }

            is SaveRecordAction.OnSaveRecord -> {
                val recordUiId = savedStateHandle.get<String>("recordUiId") ?: ""
                onEditRecord(recordUiId)
            }
        }
    }

    private fun initState() {
        viewModelScope.launch {
            val recordUiId = savedStateHandle.get<String>("recordUiId") ?: ""

            val record = recordDao.getRecordById(recordUiId) ?: throw NullPointerException("Record does not exist")

            _state.update {
                it.copy(
                    playerNameList = record.playerList,
                    recordTitle = record.title
                )
            }
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

    private fun onEditRecord(recordId: String) {
        val record = Record(
            id = recordId.toInt(),
            title = _state.value.recordTitle,
            playerList = _state.value.playerNameList,
            playerScoreList = List(PLAYER_COUNT) { 0 }
        )
        viewModelScope.launch {
            recordDao.updateRecord(record)
        }
    }
}