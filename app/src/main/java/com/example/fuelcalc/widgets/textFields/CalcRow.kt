package com.example.fuelcalc.widgets.textFields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalcRow(
    onClick: () -> Unit,
    selectedField: String,
    baseText: String,
    upperIndexText: String,
    isSelected: Boolean,
    onClearClick: () -> Unit
) {
    Box(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        InputRow(
            selectedField,
            baseText = baseText,
            upperIndexText = upperIndexText,
            isSelected = isSelected,
            onClearClick = onClearClick
        )
    }
}