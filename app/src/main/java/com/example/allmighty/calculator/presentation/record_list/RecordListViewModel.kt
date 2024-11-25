package com.example.allmighty.calculator.presentation.record_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.data.model.toRecordUi
import com.example.allmighty.calculator.presentation.model.RecordUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordListViewModel @Inject constructor(
    private val recordDao: RecordDao
) : ViewModel() {
    private val _state = MutableStateFlow(RecordListState())
    val state = _state
        .onStart {
            getAllRecord()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RecordListState()
        )

    fun onAction(event: RecordListAction) {
        when (event) {
            RecordListAction.OnCreateRecord -> {
                val recordUiMutableList = _state.value.recordUiList.toMutableList()

                recordUiMutableList.add(
                    RecordUi()
                )

                _state.update {
                    it.copy(
                        recordUiList = recordUiMutableList
                    )
                }
            }
        }
    }

    private fun getAllRecord() {
        recordDao.getAllRecord()
            .onEach { recordList ->
                _state.update {
                    it.copy(
                        recordUiList = recordList.map { record -> record.toRecordUi() }
                    )
                }
            }
            .launchIn(viewModelScope)
    }

}