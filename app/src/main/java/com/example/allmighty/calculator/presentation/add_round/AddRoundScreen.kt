package com.example.allmighty.calculator.presentation.add_round

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allmighty.calculator.presentation.add_round.component.RadioGroup
import com.example.allmighty.calculator.presentation.add_round.component.SaveRoundButton
import com.example.allmighty.calculator.presentation.add_round.component.TrickNumberPicker
import com.example.allmighty.calculator.presentation.add_round.component.playerNameList
import com.example.allmighty.calculator.presentation.model.TrumpSuit
import com.example.allmighty.calculator.presentation.util.PledgeUtil.HIGH_BOUND
import com.example.allmighty.calculator.presentation.util.PledgeUtil.LOW_BOUND
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun AddRoundScreenRoot(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddRoundViewModel = hiltViewModel()
) {
    AddRoundScreen(
        modifier = modifier,
        navController = navController,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun AddRoundScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: AddRoundState,
    onAction: (AddRoundAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RadioGroup(
            text = "마이티 플레이어: ",
            optionList = state.playerNameList,
            selectedIndex = state.mightyPlayerIndex
        ) { index ->
            onAction(AddRoundAction.OnMightyPlayerIndexChange(index))
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary
        )

        RadioGroup(
            text = "친구 플레이어: ",
            optionList = state.playerNameList,
            selectedIndex = state.friendPlayerIndex
        ) { index ->
            onAction(AddRoundAction.OnFriendPlayerIndexChange(index))
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary
        )

        RadioGroup(
            text = "기루다: ",
            optionList = TrumpSuit.entries.map { it.name },
            selectedIndex = state.trumpSuitIndex
        ) { index ->
            onAction(AddRoundAction.OnTrumpSuitChange(index))
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrickNumberPicker(
                text = "공약 수",
                defaultValue = state.pledgeNumber,
                lowBound = LOW_BOUND,
                highBound = HIGH_BOUND
            ) { pledgeNumber ->
                onAction(AddRoundAction.OnPledgeNumberChange(pledgeNumber))
            }

            TrickNumberPicker(
                text = "여당 득점",
                defaultValue = state.actualNumber,
                lowBound = LOW_BOUND,
                highBound = HIGH_BOUND
            ) { actualNumber ->
                onAction(AddRoundAction.OnActualNumberChange(actualNumber))
            }
        }

        SaveRoundButton(
            text = "라운드 저장",
            onClick = {
                onAction(AddRoundAction.OnAddRoundClick)
                navController.popBackStack()
            }
        )

    }
}

@PreviewLightDark
@Composable
private fun AddRoundScreenPreview() {
    AllMightyTheme {
        AddRoundScreen(
            navController = rememberNavController(),
            state = AddRoundState(
                playerNameList = playerNameList
            ),
            onAction = {}
        )
    }
}