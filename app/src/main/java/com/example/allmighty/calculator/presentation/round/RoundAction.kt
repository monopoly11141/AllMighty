package com.example.allmighty.calculator.presentation.round

sealed interface RoundAction {
    data class OnMightyPlayerIndexChange(val mightyPlayerIndex: Int) : RoundAction
    data class OnFriendPlayerIndexChange(val friendPlayerIndex: Int) : RoundAction
    data class OnTrumpSuitChange(val trumpSuitIndex: Int) : RoundAction
    data class OnPledgeNumberChange(val pledgeNumber: Int) : RoundAction
    data class OnActualNumberChange(val actualNumber: Int) : RoundAction
    data object OnRoundButtonClick : RoundAction
}