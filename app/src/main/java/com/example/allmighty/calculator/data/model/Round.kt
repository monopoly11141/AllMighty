package com.example.allmighty.calculator.data.model

import com.example.allmighty.calculator.presentation.model.DisplayableNumber
import com.example.allmighty.calculator.presentation.model.toDisplayableNumber
import com.example.allmighty.calculator.presentation.core.util.PlayerUtil.PLAYER_COUNT
import com.example.allmighty.calculator.presentation.core.util.PledgeUtil.PLEDGE_DEFAULT_NUMBER

data class Round(
    val mightyPlayerIndex: Int = -1,
    val friendPlayerIndex: Int = -1,
    val trumpSuit: String = "",
    val pledgeNumber: Int = -1,
    val actualNumber: Int = -1,
)

fun Round.getScoreChange(): List<DisplayableNumber> {
    val score = if (this.actualNumber >= this.pledgeNumber) {
        this.pledgeNumber + this.actualNumber - ((PLEDGE_DEFAULT_NUMBER - 1) * 2)

    } else {
        this.actualNumber - this.pledgeNumber
    }

    return (0 until PLAYER_COUNT).map { index ->
        when (index) {
            mightyPlayerIndex -> {
                score * 2
            }

            friendPlayerIndex -> {
                score
            }

            else -> {
                score * -1
            }
        }
    }.map { i -> i.toDisplayableNumber() }
}