package com.example.allmighty.calculator.presentation.add_round

sealed interface AddRoundAction {
    data class OnMightyPlayerIndexChange(val mightyPlayerIndex: Int) : AddRoundAction
    data class OnFriendPlayerIndexChange(val friendPlayerIndex: Int) : AddRoundAction
    data class OnTrumpSuitChange(val trumpSuit: String) : AddRoundAction
    data class OnPledgeNumberChange(val pledgeNumber: Int) : AddRoundAction
    data class OnActualNumberChange(val actualNumber: Int) : AddRoundAction
    data object OnAddRoundClick: AddRoundAction
}