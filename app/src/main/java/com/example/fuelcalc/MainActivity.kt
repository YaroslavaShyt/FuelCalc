package com.example.fuelcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.fuelcalc.fuel.FuelViewModel
import com.example.fuelcalc.fuel.widgets.TabOne
import com.example.fuelcalc.oilFuel.OilFuelViewModel
import com.example.fuelcalc.oilFuel.widgets.TabTwo
import com.example.fuelcalc.ui.theme.FuelCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FuelCalcTheme {
                MainView(FuelViewModel(), OilFuelViewModel())
            }
        }
    }
}



@Composable
fun MainView(fuelViewModel: FuelViewModel, oilFuelViewModel: OilFuelViewModel) {
    val scrollState = rememberScrollState()
    var selectedTabIndex by  remember {mutableIntStateOf(0)}
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
            TabRow(selectedTabIndex = selectedTabIndex, indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color.Cyan.copy(alpha = 0.5f)
                )
            }) {
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FuelCalcTheme {
        MainView(FuelViewModel(), OilFuelViewModel())
    }
}


