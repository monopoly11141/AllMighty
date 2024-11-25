package com.example.allmighty.calculator.presentation.add_round

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.data.model.Round
import com.example.allmighty.calculator.presentation.model.TrumpSuit
import com.example.allmighty.calculator.presentation.util.PledgeUtil.PLEDGE_DEFAULT_NUMBER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRoundViewModel @Inject constructor(
    private val recordDao: RecordDao,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddRoundState())
    val state = _state
        .onStart {
            initPlayerList()
            initPledgeNumber()
            initActualNumber()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AddRoundState()
        )

    fun onAction(action: AddRoundAction) {
        when (action) {
            is AddRoundAction.OnMightyPlayerIndexChange -> {
                onMightyPlayerChange(action.mightyPlayerIndex)
            }

            is AddRoundAction.OnFriendPlayerIndexChange -> {
                onFriendPlayerChange(action.friendPlayerIndex)
            }

            is AddRoundAction.OnPledgeNumberChange -> {
                onPledgeNumberChange(action.pledgeNumber)
            }

            is AddRoundAction.OnActualNumberChange -> {
                onActualNumberChange(action.actualNumber)
            }

            is AddRoundAction.OnTrumpSuitChange -> {
                onTrumpSuitChange(action.trumpSuitIndex)
            }

            is AddRoundAction.OnAddRoundClick -> {
                onAddRoundClick()
            }
        }
    }

    private fun initPlayerList() {
        viewModelScope.launch {
            val recordUiId = savedStateHandle.get<String>("recordUiId")

            val record = recordUiId?.let {
                recordDao.getRecordById(it)
            }

            record?.let { thisRecord ->
                _state.update {
                    it.copy(
                        playerNameList = thisRecord.playerList
                    )
                }
            }

        }
    }

    private fun initPledgeNumber() {
        _state.update {
            it.copy(
                pledgeNumber = PLEDGE_DEFAULT_NUMBER
            )
        }
    }


    private fun initActualNumber() {
        _state.update {
            it.copy(
                actualNumber = PLEDGE_DEFAULT_NUMBER
            )
        }
    }

    private fun onMightyPlayerChange(mightyPlayerIndex: Int) {
        _state.update {
            it.copy(
                mightyPlayerIndex = mightyPlayerIndex
            )
        }
    }

    private fun onFriendPlayerChange(friendPlayerIndex: Int) {
        _state.update {
            it.copy(
                friendPlayerIndex = friendPlayerIndex
            )
        }
    }

    private fun onPledgeNumberChange(pledgeNumber: Int) {
        _state.update {
            it.copy(
                pledgeNumber = pledgeNumber
            )
        }
    }

    private fun onActualNumberChange(actualNumber: Int) {
        _state.update {
            it.copy(
                actualNumber = actualNumber
            )
        }
    }

    private fun onTrumpSuitChange(trumpSuitIndex: Int) {
        _state.update {
            it.copy(
                trumpSuitIndex = trumpSuitIndex
            )
        }
    }

    private fun onAddRoundClick() {

        val round = Round(
            mightyPlayerIndex = _state.value.mightyPlayerIndex,
            friendPlayerIndex = _state.value.friendPlayerIndex,
            trumpSuit = TrumpSuit.entries[_state.value.trumpSuitIndex].name,
            pledgeNumber = _state.value.pledgeNumber,
            actualNumber = _state.value.actualNumber
        )

        viewModelScope.launch {
            val recordUiId = savedStateHandle.get<String>("recordUiId")

            val record = recordUiId?.let {
                recordDao.getRecordById(it)
            }

            if (record == null) {
                throw NullPointerException("Record not found")
            }

            val newRecord = record.copy(
                roundList = record.roundList.plus(round)
            )

            recordDao.updateRecord(newRecord)
        }

    }

}