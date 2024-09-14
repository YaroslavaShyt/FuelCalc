package com.example.fuelcalc.oilFuel

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fuelcalc.widgets.InputRow
import com.example.fuelcalc.widgets.ResultRow

@Composable
fun TabTwo(viewModel: OilFuelViewModel) {
    var oilFuelResult by remember { mutableStateOf(OilFuelResult()) }
    var inputH by remember { mutableStateOf("") }
    var inputC by remember { mutableStateOf("") }
    var inputS by remember { mutableStateOf("") }
    var inputO by remember { mutableStateOf("") }
    var inputV by remember { mutableStateOf("") }
    var inputW by remember { mutableStateOf("") }
    var inputA by remember { mutableStateOf("") }
    var inputQ by remember { mutableStateOf("") }

    Text("Мобільний калькулятор для перерахунку елементарного складу та нижчої теплоти згоряння мазуту на робочу масу для складу гочої маси мазуту")
    Spacer(modifier = Modifier.height(20.dp))

//    Row {
//        InputRow(textState = inputH, baseText = "H", upperIndexText = "Г") { inputH = it }
//        InputRow(textState = inputC, baseText = "C", upperIndexText = "Г") { inputC = it }
//        InputRow(textState = inputS, baseText = "S", upperIndexText = "Г") { inputS = it }
//        InputRow(textState = inputO, baseText = "O", upperIndexText = "Г") { inputO = it }
//    }
//    Row {
//        InputRow(textState = inputV, baseText = "V", upperIndexText = "Г") {inputV = it}
//        InputRow(textState = inputW, baseText = "W", upperIndexText = "Г") {inputW = it}
//        InputRow(textState = inputA, baseText = "A", upperIndexText = "Г") {inputA = it}
//        InputRow(textState = inputQ, baseText = "Q", upperIndexText = "daf", lowerIndexText = "i") {inputQ = it}
//    }
    Spacer(modifier = Modifier.height(20.dp))

    Button(onClick = {
        oilFuelResult = viewModel.count(
            OilFuel(
                H = inputH.toDoubleOrNull() ?: 0.0,
                C = inputC.toDoubleOrNull() ?: 0.0,
                S = inputS.toDoubleOrNull() ?: 0.0,
                O = inputO.toDoubleOrNull() ?: 0.0
            )
        )
    }) {
        Text(text = "Обчислити")
    }

    Text(text = "Склад робочої маси мазуту:")

    Row {
        ResultRow(baseText = "H", upperIndexText = "P", result = "${oilFuelResult.workingOilFuel.H}")
        ResultRow(baseText = "C", upperIndexText = "P", result = "${oilFuelResult.workingOilFuel.C}")
        ResultRow(baseText = "S", upperIndexText = "P", result = "${oilFuelResult.workingOilFuel.S}")
    }
    Row {
        ResultRow(baseText = "O", upperIndexText = "P", result = "${oilFuelResult.workingOilFuel.O}")
        ResultRow(baseText = "V", upperIndexText = "P", result = "${oilFuelResult.workingOilFuel.V}")
        ResultRow(baseText = "A", upperIndexText = "P", result = "${oilFuelResult.workingOilFuel.A}")
    }

    Text(text = "Нижча теплота згоряння мазуту:")
    Spacer(modifier = Modifier.height(20.dp))
    ResultRow(baseText = "Q", upperIndexText = "", result = "${oilFuelResult.lowerHeatOfCombustion}")
}