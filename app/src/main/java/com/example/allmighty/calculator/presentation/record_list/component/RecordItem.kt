package com.example.allmighty.calculator.presentation.record_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.allmighty.calculator.presentation.model.RecordUi
import com.example.allmighty.calculator.presentation.model.toDisplayableTime
import com.example.allmighty.calculator.presentation.record.previewRecordUi
import com.example.allmighty.ui.theme.AllMightyTheme

@Composable
fun RecordItem(
    modifier: Modifier = Modifier,
    recordUi: RecordUi,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Row(
        modifier = modifier
            .clickable(onClick = onItemClick)
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = recordUi.title,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp
        )

        Text(
            text = recordUi.createdTime.value.toDisplayableTime().formatted,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp
        )

        IconButton(onClick = { onDeleteClick() }) {

            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete Record Button"
            )

        }
    }


}

@PreviewLightDark
@Composable
private fun RecordItemPreview() {
    AllMightyTheme {
        RecordItem(
            recordUi = previewRecordUi,
            onItemClick = {},
            onDeleteClick = {}
        )
    }
}