package com.example.fuelcalc.oilFuel.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fuelcalc.oilFuel.OilFuelViewModel
import com.example.fuelcalc.widgets.textFields.CalcRow
import com.example.fuelcalc.widgets.calculator.CalculatorKeyboard

@Composable
fun TabTwo(viewModel: OilFuelViewModel) {
    var inputH by remember { mutableStateOf("") }
    var inputC by remember { mutableStateOf("") }
    var inputS by remember { mutableStateOf("") }
    var inputO by remember { mutableStateOf("") }
    var inputV by remember { mutableStateOf("") }
    var inputW by remember { mutableStateOf("") }
    var inputA by remember { mutableStateOf("") }
    var inputQ by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val openDialog = remember { mutableStateOf(false) }


    var selectedField by remember { mutableStateOf("H") }

    Text("Мобільний калькулятор для перерахунку елементарного складу " +
            "та нижчої теплоти згоряння мазуту на робочу масу для складу гочої маси мазуту")
    Spacer(modifier = Modifier.height(20.dp))

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
                    upperIndexText = "Г",
                    isSelected = selectedField == "H",
                    onClearClick = {inputH = inputH.dropLast(1)}
                )
                CalcRow(
                    onClick = { selectedField = "C" },
                    selectedField = inputC,
                    baseText = "C",
                    upperIndexText = "Г",
                    isSelected = selectedField == "C",
                    onClearClick = {inputC = inputC.dropLast(1)}
                )
                CalcRow(
                    onClick = { selectedField = "S" },
                    selectedField = inputS,
                    baseText = "S",
                    upperIndexText = "Г",
                    isSelected = selectedField == "S",
                    onClearClick = {inputS = inputS.dropLast(1)}
                )
                CalcRow(
                    onClick = { selectedField = "O" },
                    selectedField = inputO,
                    baseText = "O",
                    upperIndexText = "Г",
                    isSelected = selectedField == "O",
                    onClearClick = {inputO = inputO.dropLast(1)}
                )
                CalcRow(
                    onClick = { selectedField = "V" },
                    selectedField = inputV,
                    baseText = "V",
                    upperIndexText = "Г",
                    isSelected = selectedField == "V",
                    onClearClick = {inputV = inputV.dropLast(1)}
                )
                CalcRow(
                        onClick = { selectedField = "W" },
                selectedField = inputW,
                baseText = "W",
                upperIndexText = "Г",
                isSelected = selectedField == "W",
                onClearClick = {inputW = inputW.dropLast(1)}
                )
                CalcRow(
                    onClick = { selectedField = "A" },
                    selectedField = inputA,
                    baseText = "A",
                    upperIndexText = "Г",
                    isSelected = selectedField == "A",
                    onClearClick = {inputA = inputA.dropLast(1)}
                )
                CalcRow(
                    onClick = { selectedField = "Q" },
                    selectedField = inputQ,
                    baseText = "Q",
                    upperIndexText = "Г",
                    isSelected = selectedField == "Q",
                    onClearClick = {inputQ = inputQ.dropLast(1)}
                )


            }
        }
        CalculatorKeyboard(
            onButtonClick = { value ->
                when (selectedField) {
                    "H" -> inputH += value
                    "C" -> inputC += value
                    "S" -> inputS += value
                    "V" -> inputV += value
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
        OilFuelResultAlertDialog(
            onDismiss = { openDialog.value = false },
             oilFuelResult = viewModel.count(viewModel.oilFuelControlExample)
//            oilFuelResult = viewModel.count(
//                OilFuel(
//                    C = inputC.toDoubleOrNull() ?: 0.0,
//                    H = inputH.toDoubleOrNull() ?: 0.0,
//                    S = inputS.toDoubleOrNull() ?: 0.0,
//                    O = inputO.toDoubleOrNull() ?: 0.0,
//                    V = inputV.toDoubleOrNull() ?: 0.0,
//                    A = inputA.toDoubleOrNull() ?: 0.0,
//                    W = inputW.toDoubleOrNull() ?: 0.0,
//                )
//            )

        )
    }

}