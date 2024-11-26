package com.example.allmighty.calculator.presentation.round

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allmighty.calculator.data.db.RecordDao
import com.example.allmighty.calculator.data.model.Round
import com.example.allmighty.calculator.presentation.model.TrumpSuit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditRoundViewModel @Inject constructor(
    private val recordDao: RecordDao,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(RoundState())
    val state = _state
        .onStart {
            initState()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RoundState()
        )

    fun onAction(action: RoundAction) {
        when (action) {
            is RoundAction.OnMightyPlayerIndexChange -> {
                onMightyPlayerChange(action.mightyPlayerIndex)
            }

            is RoundAction.OnFriendPlayerIndexChange -> {
                onFriendPlayerChange(action.friendPlayerIndex)
            }

            is RoundAction.OnPledgeNumberChange -> {
                onPledgeNumberChange(action.pledgeNumber)
            }

            is RoundAction.OnActualNumberChange -> {
                onActualNumberChange(action.actualNumber)
            }

            is RoundAction.OnTrumpSuitChange -> {
                onTrumpSuitChange(action.trumpSuitIndex)
            }

            is RoundAction.OnRoundButtonClick -> {
                onRoundButtonClick()
            }
        }
    }

    private fun initState() {
        viewModelScope.launch {
            val recordUiId = savedStateHandle.get<String>("recordUiId")
            val roundIndex =
                savedStateHandle.get<Int>("roundIndex") ?: throw NullPointerException("Round does not Exist")

            val record = recordUiId?.let {
                recordDao.getRecordById(it)
            }

            record?.let { currentRecord ->
                val currentRound = currentRecord.roundList[roundIndex]

                _state.update {
                    it.copy(
                        playerNameList = currentRecord.playerList,
                        mightyPlayerIndex = currentRound.mightyPlayerIndex,
                        friendPlayerIndex = currentRound.friendPlayerIndex,
                        trumpSuitIndex = TrumpSuit.entries.indexOf(TrumpSuit.valueOf(currentRound.trumpSuit)),
                        pledgeNumber = currentRound.pledgeNumber,
                        actualNumber = currentRound.actualNumber
                    )
                }
            }

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

    private fun onRoundButtonClick() {

        val round = Round(
            mightyPlayerIndex = _state.value.mightyPlayerIndex,
            friendPlayerIndex = _state.value.friendPlayerIndex,
            trumpSuit = TrumpSuit.entries[_state.value.trumpSuitIndex].name,
            pledgeNumber = _state.value.pledgeNumber,
            actualNumber = _state.value.actualNumber
        )

        viewModelScope.launch {
            val recordUiId = savedStateHandle.get<String>("recordUiId")
            val roundIndex =
                savedStateHandle.get<Int>("roundIndex") ?: throw NullPointerException("Round does not Exist")

            val record = recordUiId?.let {
                recordDao.getRecordById(it)
            }

            if (record == null) {
                throw NullPointerException("Record not found")
            }

            val roundList = record.roundList.toMutableList()
            roundList[roundIndex] = round

            val newRecord = record.copy(
                roundList = roundList
            )

            recordDao.updateRecord(newRecord)
        }

    }

}