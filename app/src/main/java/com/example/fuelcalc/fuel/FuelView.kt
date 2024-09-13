package com.example.fuelcalc.fuel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fuelcalc.oilFuel.OilFuel
import com.example.fuelcalc.oilFuel.OilFuelResult
import com.example.fuelcalc.oilFuel.OilFuelViewModel
import com.example.fuelcalc.ui.theme.FuelCalcTheme

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FuelCalcTheme {
        FuelView(FuelViewModel(), OilFuelViewModel())
    }
}

@Composable
fun FuelView(fuelViewModel: FuelViewModel, oilFuelViewModel: OilFuelViewModel) {
    val scrollState = rememberScrollState()
    var selectedTabIndex by remember { mutableStateOf(1) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            TabRow(selectedTabIndex = selectedTabIndex) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text("Паливо") }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text("Мазут") }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            when (selectedTabIndex) {
                0 -> TabOne(fuelViewModel)
                1 -> TabTwo(oilFuelViewModel)
            }
        }
    }
}

@Composable
fun TabOne(viewModel: FuelViewModel) {
    var fuelResult by remember { mutableStateOf(FuelResult()) }

    // Define states for each input field
    var inputH by remember { mutableStateOf("") }
    var inputC by remember { mutableStateOf("") }
    var inputS by remember { mutableStateOf("") }
    var inputN by remember { mutableStateOf("") }
    var inputO by remember { mutableStateOf("") }
    var inputW by remember { mutableStateOf("") }
    var inputA by remember { mutableStateOf("") }

    Text("Мобільний калькулятор для розрахунку складу сухої та горючої маси палива та нижчої теплоти згоряння")
    Spacer(modifier = Modifier.height(20.dp))

    Row {
        InputRow(textState = inputH, baseText = "H", upperIndexText = "P") { inputH = it }
        InputRow(textState = inputC, baseText = "C", upperIndexText = "P") { inputC = it }
        InputRow(textState = inputS, baseText = "S", upperIndexText = "P") { inputS = it }
        InputRow(textState = inputN, baseText = "N", upperIndexText = "P") { inputN = it }
    }
    Row {
        InputRow(textState = inputO, baseText = "O", upperIndexText = "P") { inputO = it }
        InputRow(textState = inputW, baseText = "W", upperIndexText = "P") { inputW = it }
        InputRow(textState = inputA, baseText = "A", upperIndexText = "P") { inputA = it }
    }
    Spacer(modifier = Modifier.height(20.dp))

    Button(onClick = {
        fuelResult = viewModel.countResult(
            Fuel(
                H = inputH.toDoubleOrNull() ?: 0.0,
                C = inputC.toDoubleOrNull() ?: 0.0,
                S = inputS.toDoubleOrNull() ?: 0.0,
                N = inputN.toDoubleOrNull() ?: 0.0,
                O = inputO.toDoubleOrNull() ?: 0.0,
                W = inputW.toDoubleOrNull() ?: 0.0,
                A = inputA.toDoubleOrNull() ?: 0.0
            )
        )
    }) {
        Text(text = "Обчислити")
    }

    // Display results
    Text(text = "Коефіцієнт переходу від робочої до сухої маси: ${fuelResult.coeffOfTransFromWorkingToDryMass}")
    Text(text = "Коефіцієнт переходу від робочої до горючої маси: ${fuelResult.coeffOfTransFromWorkingToCombMass}")
    Text(text = "Склад сухої маси палива: ")
    Row {
        ResultRow(baseText = "H", upperIndexText = "C", result = "${fuelResult.fuelDryMass.H}")
        ResultRow(baseText = "C", upperIndexText = "C", result = "${fuelResult.fuelDryMass.C}")
        ResultRow(baseText = "S", upperIndexText = "C", result = "${fuelResult.fuelDryMass.S}")
    }
    Row {
        ResultRow(baseText = "N", upperIndexText = "C", result = "${fuelResult.fuelDryMass.N}")
        ResultRow(baseText = "O", upperIndexText = "C", result = "${fuelResult.fuelDryMass.O}")
        ResultRow(baseText = "A", upperIndexText = "C", result = "${fuelResult.fuelDryMass.A}")
    }
    Text(text = "Перевірка: ${fuelResult.checkSum1}")
    Spacer(modifier = Modifier.height(20.dp))

    Text(text = "Склад горючої маси палива: ")
    Row {
        ResultRow(baseText = "H", upperIndexText = "Г", result = "${fuelResult.fuelCombustionMass.H}")
        ResultRow(baseText = "C", upperIndexText = "Г", result = "${fuelResult.fuelCombustionMass.C}")
        ResultRow(baseText = "S", upperIndexText = "Г", result = "${fuelResult.fuelCombustionMass.S}")
    }
    Row {
        ResultRow(baseText = "N", upperIndexText = "Г", result = "${fuelResult.fuelCombustionMass.N}")
        ResultRow(baseText = "O", upperIndexText = "Г", result = "${fuelResult.fuelCombustionMass.O}")
    }
    Text(text = "Перевірка: ${fuelResult.checkSum2}")
    Spacer(modifier = Modifier.height(20.dp))

    Text(text = "Нижча теплота згоряння для робочої маси: ${fuelResult.lowerHeatOfCombustion}")
    Text(text = "Нижча теплота згоряння для сухої маси: ${fuelResult.dryMass}")
    Text(text = "Нижча теплота згоряння для горючої маси: ${fuelResult.combustionMass}")
}

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

    Row {
        InputRow(textState = inputH, baseText = "H", upperIndexText = "Г") { inputH = it }
        InputRow(textState = inputC, baseText = "C", upperIndexText = "Г") { inputC = it }
        InputRow(textState = inputS, baseText = "S", upperIndexText = "Г") { inputS = it }
        InputRow(textState = inputO, baseText = "O", upperIndexText = "Г") { inputO = it }
    }
    Row {
        InputRow(textState = inputV, baseText = "V", upperIndexText = "Г") {inputV = it}
        InputRow(textState = inputW, baseText = "W", upperIndexText = "Г") {inputW = it}
        InputRow(textState = inputA, baseText = "A", upperIndexText = "Г") {inputA = it}
        InputRow(textState = inputQ, baseText = "Q", upperIndexText = "daf", lowerIndexText = "i") {inputQ = it}
    }
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

@Composable
fun InputRow(textState: String, baseText: String, upperIndexText: String, lowerIndexText: String = "", onValueChange: (String) -> Unit) {
    Row {
        TextWithIndex(
            baseText = baseText,
            upperIndexText = upperIndexText,
            lowerIndexText = lowerIndexText
        )
        Text(text = "=")
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(40.dp)
                .padding(horizontal = 5.dp)
        ) {
            TextField(value = textState, onValueChange = onValueChange)
        }
        Text(text = "%")
    }
}

@Composable
fun TextWithIndex(baseText: String, upperIndexText: String, lowerIndexText: String = "") {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = baseText,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        if (upperIndexText.isNotEmpty()) {
            Text(
                text = upperIndexText,
                fontSize = 12.sp,
                modifier = Modifier.offset(y = (-4).dp)
            )
        }
        if (lowerIndexText.isNotEmpty()) {
            Text(
                text = lowerIndexText,
                fontSize = 12.sp,
                modifier = Modifier.offset(y = 4.dp)
            )
        }
    }
}

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
