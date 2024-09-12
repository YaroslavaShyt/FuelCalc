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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fuelcalc.ui.theme.FuelCalcTheme

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FuelCalcTheme {
        FuelView(FuelViewModel())
    }
}

@Composable
fun FuelView(viewModel: FuelViewModel) {

    val scrollState = rememberScrollState()
    var selectedTabIndex by remember { mutableStateOf(1) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                when(selectedTabIndex){
                    0 -> TabOne(viewModel)
                    1 -> TabTwo()
                }


            }
        }
    }
}

@Composable
fun TabOne(viewModel: FuelViewModel){
    var fuelResult by remember {
        mutableStateOf(FuelResult())
    }
    Text("Мобільний калькулятор для розрахунку складу сухої та горючої маси палива та нижчої теплоти згоряння")
    Spacer(modifier = Modifier.height(20.dp))
    Row {
        InputRow(baseText = "H", upperIndexText = "P")
        InputRow(baseText = "C", upperIndexText = "P")
        InputRow(baseText = "S", upperIndexText = "P")
        InputRow(baseText = "N", upperIndexText = "P")


    }
    Row {
        InputRow(baseText = "O", upperIndexText = "P")
        InputRow(baseText = "W", upperIndexText = "P")
        InputRow(baseText = "A", upperIndexText = "P")


    }
    Spacer(modifier = Modifier.height(20.dp))
    Button(onClick = {
        fuelResult = viewModel.countResult(viewModel.fuelControlExample)
    }) {
        Text(text = "Обчислити")
    }
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
fun TabTwo(){
    Text("Мобільний калькулятор для перерахунку елементарного складу та нижчої теплоти згоряння мазуту на робочу масу для складу гочої маси мазуту")
    Spacer(modifier = Modifier.height(20.dp))
    Row {
        InputRow(baseText = "H", upperIndexText = "Г")
        InputRow(baseText = "C", upperIndexText = "Г")
        InputRow(baseText = "S", upperIndexText = "Г")
        InputRow(baseText = "O", upperIndexText = "Г")

    }
    Row {
        InputRow(baseText = "V", upperIndexText = "Г")
        InputRow(baseText = "W", upperIndexText = "Г")
        InputRow(baseText = "A", upperIndexText = "Г")
        InputRow(baseText = "Q", upperIndexText = "daf", lowerIndexText = "i")



    }
    Spacer(modifier = Modifier.height(20.dp))
    Button(onClick = {

    }) {
        Text(text = "Обчислити")
    }
    Text(text = "Коефіцієнт переходу від робочої до сухої маси:")
    Text(text = "Коефіцієнт переходу від робочої до горючої маси: ")
    Text(text = "Склад сухої маси палива: ")
    Row {
        ResultRow(baseText = "H", upperIndexText = "C", result = "")
        ResultRow(baseText = "C", upperIndexText = "C", result = "")
        ResultRow(baseText = "S", upperIndexText = "C", result = "")
    }
    Row {
        ResultRow(baseText = "N", upperIndexText = "C", result = "")
        ResultRow(baseText = "O", upperIndexText = "C", result = "")
        ResultRow(baseText = "A", upperIndexText = "C", result = "")
    }
    Text(text = "Перевірка: ")
    Spacer(modifier = Modifier.height(20.dp))

    Text(text = "Склад горючої маси палива: ")

}

@Composable
fun TextWithIndex(baseText: String, upperIndexText: String, lowerIndexText: String = "") {
    Box(
        modifier = Modifier.width(30.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = baseText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = upperIndexText,
            fontSize = 14.sp,
            modifier = Modifier
                .offset(x = 20.dp, y = (-8).dp)
        )
        Text(
            text = lowerIndexText,
            fontSize = 14.sp,
            modifier = Modifier
                .offset(x = 18.dp, y = (8).dp)
        )
    }
}

@Composable
fun InputRow(baseText: String, upperIndexText: String, lowerIndexText: String = "") {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Row {
        TextWithIndex(
            baseText = baseText,
            upperIndexText = upperIndexText,
            lowerIndexText = lowerIndexText,
        )
        Text(text = "=")
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(40.dp)
                .padding(horizontal = 5.dp)
        ) {
            TextField(value = text, onValueChange = { newText ->
                text = newText
            })
        }
        Text(text = "%")
    }
}

@Composable
fun ResultRow(
    baseText: String,
    upperIndexText: String,
    lowerIndexText: String = "",
    result: String
) {
    Row {
        TextWithIndex(
            baseText = baseText,
            upperIndexText = upperIndexText,
            lowerIndexText = lowerIndexText,
        )
        Text(text = "=")
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(40.dp)
                .padding(horizontal = 5.dp)
        ) {
            Text(text = result)

        }
    }
}