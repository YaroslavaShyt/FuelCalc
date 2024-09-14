package com.example.fuelcalc.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CalculatorKeyboard(onButtonClick: (String) -> Unit, onEqualsClick: () -> Unit){
    Column {
        Row {
            RoundCalculatorButton(text = "1", onClick = {onButtonClick("1")})
            RoundCalculatorButton(text = "2", onClick = {onButtonClick("2")})
            RoundCalculatorButton(text = "3", onClick = {onButtonClick("3")})
        }
        Row {
            RoundCalculatorButton(text = "4") {

            }
            RoundCalculatorButton(text = "5") {

            }
            RoundCalculatorButton(text = "6") {

            }
        }
        Row {
            RoundCalculatorButton(text = "7") {

            }
            RoundCalculatorButton(text = "8") {

            }
            RoundCalculatorButton(text = "9") {

            }
        }
        Row {
            RoundCalculatorButton(text = "0") {

            }
            RoundCalculatorButton(text = ".") {

            }
            RoundCalculatorButton(text = "=",
                color = Color.Cyan.copy(alpha = 0.5f), pressedColor = Color.Magenta,
                onClick = onEqualsClick,
                )

            }
        }
    }
