package com.example.allmighty.calculator.presentation.add_round

sealed interface AddRoundAction {
    data object OnAddRoundClick: AddRoundAction
}