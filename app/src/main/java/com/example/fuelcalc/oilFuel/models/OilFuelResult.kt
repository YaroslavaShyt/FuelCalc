package com.example.fuelcalc.oilFuel.models

data class OilFuelResult (
    val workingOilFuel: OilFuel = OilFuel(),
    val lowerHeatOfCombustion : Double = 0.0,
)