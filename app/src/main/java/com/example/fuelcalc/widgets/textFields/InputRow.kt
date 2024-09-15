package com.example.fuelcalc.widgets.textFields


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun InputRow(
    textValue: String,
    baseText: String,
    upperIndexText: String,
    lowerIndexText: String = "",
    isSelected: Boolean,
    onClearClick: () -> Unit
) {
Box(modifier = Modifier.width(500.dp)){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextWithIndex(
            baseText = baseText,
            upperIndexText = upperIndexText,
            lowerIndexText = lowerIndexText
        )
        Text(text = "=")

        Text(
            text = textValue,
            modifier = Modifier
                .width(250.dp)
                .padding(8.dp)
                .drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = if (isSelected) Color.Cyan.copy(alpha = 0.5f) else Color.LightGray,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                },
            style = androidx.compose.ui.text.TextStyle(
                color = Color.Black,
                fontSize = 15.sp
            ),

            )

        ClearFieldButton {
                onClearClick()
        }
    }
}

}
