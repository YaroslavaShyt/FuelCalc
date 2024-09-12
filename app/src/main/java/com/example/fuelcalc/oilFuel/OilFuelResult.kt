package com.example.fuelcalc.oilFuel

data class OilFuelResult (
    val workingOilFuel: OilFuel = OilFuel(),
    val lowerHeatOfCombustion : Double = 0.0,
)