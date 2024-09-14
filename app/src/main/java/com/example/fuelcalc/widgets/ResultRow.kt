package com.example.fuelcalc.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fuelcalc.widgets.TextWithIndex

@Composable
fun ResultRow(baseText: String, upperIndexText: String, result: String) {
    Row {
        TextWithIndex(
            baseText = baseText,
            upperIndexText = upperIndexText
        )
        Text(text = "=")
        Text(text = result)
        Text(text = "%")
    }
}