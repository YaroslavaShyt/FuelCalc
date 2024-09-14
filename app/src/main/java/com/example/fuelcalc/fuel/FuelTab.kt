package com.example.fuelcalc.fuel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fuelcalc.FuelView
import com.example.fuelcalc.oilFuel.OilFuelViewModel
import com.example.fuelcalc.ui.theme.FuelCalcTheme
import com.example.fuelcalc.widgets.CalculatorKeyboard
import com.example.fuelcalc.widgets.InputRow


@Composable
fun TabOne(viewModel: FuelViewModel) {
    var inputH by remember { mutableStateOf("") }
    var inputC by remember { mutableStateOf("") }
    var inputS by remember { mutableStateOf("") }
    var inputN by remember { mutableStateOf("") }
    var inputO by remember { mutableStateOf("") }
    var inputW by remember { mutableStateOf("") }
    var inputA by remember { mutableStateOf("") }

    var selectedField by remember { mutableStateOf("H") }

    Text(
        "Мобільний калькулятор для розрахунку складу сухої та " +
                "горючої маси палива та нижчої теплоти згоряння"
    )
    Spacer(modifier = Modifier.height(20.dp))

    Column {
        Box(
            modifier = Modifier.clickable {
                selectedField = "H"
            }
        ) {
            InputRow(
                inputH,
                baseText = "H",
                upperIndexText = "P",
                isSelected = selectedField == "H",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.clickable {
                selectedField = "C"
            }
        ) {
            InputRow(
                inputC,
                baseText = "C",
                upperIndexText = "P",
                isSelected = selectedField == "C",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.clickable {
                selectedField = "S"
            }
        ) {
            InputRow(
                inputS,
                baseText = "S",
                upperIndexText = "P",
                isSelected = selectedField == "S",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.clickable {
                selectedField = "N"
            }
        ) {
            InputRow(
                inputN,
                baseText = "N",
                upperIndexText = "P",
                isSelected = selectedField == "N",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.clickable {
                selectedField = "O"
            }
        ) {
            InputRow(
                inputO,
                baseText = "O",
                upperIndexText = "P",
                isSelected = selectedField == "O",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.clickable {
                selectedField = "W"
            }
        ) {
            InputRow(
                inputW,
                baseText = "W",
                upperIndexText = "P",
                isSelected = selectedField == "W",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.clickable {
                selectedField = "A"
            }
        ) {
            InputRow(
                inputA,
                baseText = "A",
                upperIndexText = "P",
                isSelected = selectedField == "A",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        CalculatorKeyboard(
            onButtonClick = { value ->
                when (selectedField) {
                    "H" -> inputH += value
                    "C" -> inputC += value
                    "S" -> inputS += value
                    "N" -> inputN += value
                    "O" -> inputO += value
                    "W" -> inputW += value
                    "A" -> inputA += value
                }
            },
            onEqualsClick = {
                // Calculation logic here
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FuelCalcTheme {
        FuelView(FuelViewModel(), OilFuelViewModel())
    }
}




