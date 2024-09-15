package com.example.fuelcalc.fuel.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fuelcalc.fuel.FuelViewModel
import com.example.fuelcalc.fuel.models.Fuel
import com.example.fuelcalc.widgets.textFields.CalcRow
import com.example.fuelcalc.widgets.calculator.CalculatorKeyboard


@Composable
fun TabOne(viewModel: FuelViewModel) {
    var inputH by remember { mutableStateOf("") }
    var inputC by remember { mutableStateOf("") }
    var inputS by remember { mutableStateOf("") }
    var inputN by remember { mutableStateOf("") }
    var inputO by remember { mutableStateOf("") }
    var inputW by remember { mutableStateOf("") }
    var inputA by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val openDialog = remember { mutableStateOf(false) }


    var selectedField by remember { mutableStateOf("H") }

    Text(
        "Мобільний калькулятор для розрахунку складу сухої та " +
                "горючої маси палива та нижчої теплоти згоряння"
    )

    Column {
        Box(modifier = Modifier.height(200.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                CalcRow(
                    onClick = { selectedField = "H" },
                    selectedField = inputH,
                    baseText = "H",
                    upperIndexText = "P",
                    isSelected = selectedField == "H",
                    onClearClick = { inputH = inputH.dropLast(1) }
                )
                CalcRow(
                    onClick = { selectedField = "C" },
                    selectedField = inputC,
                    baseText = "C",
                    upperIndexText = "P",
                    isSelected = selectedField == "C",
                    onClearClick = { inputC = inputC.dropLast(1) }
                )
                CalcRow(
                    onClick = { selectedField = "S" },
                    selectedField = inputS,
                    baseText = "S",
                    upperIndexText = "P",
                    isSelected = selectedField == "S",
                    onClearClick = { inputS = inputS.dropLast(1) }
                )

                CalcRow(
                    onClick = { selectedField = "N" },
                    selectedField = inputN,
                    baseText = "N",
                    upperIndexText = "P",
                    isSelected = selectedField == "N",
                    onClearClick = { inputN = inputN.dropLast(1) }
                )
                CalcRow(
                    onClick = { selectedField = "O" },
                    selectedField = inputO,
                    baseText = "O",
                    upperIndexText = "P",
                    isSelected = selectedField == "O",
                    onClearClick = { inputO = inputO.dropLast(1) }
                )
                CalcRow(
                    onClick = { selectedField = "W" },
                    selectedField = inputW,
                    baseText = "W",
                    upperIndexText = "P",
                    isSelected = selectedField == "W",
                    onClearClick = { inputW = inputW.dropLast(1) }
                )

                CalcRow(
                    onClick = { selectedField = "A" },
                    selectedField = inputA,
                    baseText = "A",
                    upperIndexText = "P",
                    isSelected = selectedField == "A",
                    onClearClick = { inputA = inputA.dropLast(1) }
                )
            }
        }

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
                openDialog.value = true
            }
        )
    }
    if (openDialog.value) {
        FuelResultAlertDialog(
            onDismiss = { openDialog.value = false },
            // fuelResult = viewModel.countResult(viewModel.fuelControlExample)
            fuelResult = viewModel.countResult(
                Fuel(
                    C = inputC.toDoubleOrNull() ?: 0.0,
                    H = inputH.toDoubleOrNull() ?: 0.0,
                    S = inputS.toDoubleOrNull() ?: 0.0,
                    O = inputO.toDoubleOrNull() ?: 0.0,
                    N = inputN.toDoubleOrNull() ?: 0.0,
                    A = inputA.toDoubleOrNull() ?: 0.0,
                    W = inputW.toDoubleOrNull() ?: 0.0,
                )
            )

        )
    }
}










