package com.example.fuelcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fuelcalc.fuel.FuelView

import com.example.fuelcalc.fuel.FuelViewModel
import com.example.fuelcalc.ui.theme.FuelCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FuelCalcTheme {
                FuelView(FuelViewModel())
            }
        }
    }
}



