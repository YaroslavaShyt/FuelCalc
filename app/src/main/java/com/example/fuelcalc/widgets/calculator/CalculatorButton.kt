package com.example.fuelcalc.widgets.calculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fuelcalc.ui.theme.FuelCalcTheme

@Composable
fun RoundCalculatorButton(
    text: String,
    color: Color = Color.White,
    pressedColor: Color = Color.LightGray,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(15.dp).border(BorderStroke(1.dp, Color.Transparent), shape = CircleShape)) {
        Box(
            modifier = Modifier
                .size(85.dp)
                .background(if (isPressed) pressedColor else color, CircleShape)
                .border(BorderStroke(1.dp, Color.LightGray), shape = CircleShape)
                .clickable(
                    onClick = {
                        isPressed = true
                        onClick()
                        isPressed = false
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 50.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoundCalculatorButton() {
    FuelCalcTheme {
        RoundCalculatorButton(text = "5") {
        }
    }

}
