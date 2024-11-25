package com.example.allmighty.calculator.presentation.record

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.data.model.toRecordUi
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

            is RecordAction.UpdateRecord -> {
                onAddRoundClick()
            }
        }
    }

    private fun onAddRoundClick() {

        viewModelScope.launch {
            val recordUiId =
                savedStateHandle.get<String>("recordUiId") ?: throw NullPointerException("recordUiID is null")

            val record = recordDao.getRecordById(recordUiId) ?: throw NullPointerException("Record is null")

            _state.update {
                it.copy(
                    recordUi = record.toRecordUi()
                )
            }
        }

    }
}