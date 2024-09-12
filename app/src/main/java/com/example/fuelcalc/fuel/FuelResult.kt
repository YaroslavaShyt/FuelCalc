package com.example.fuelcalc.fuel

data class FuelResult(
    val coeffOfTransFromWorkingToDryMass: Double = 0.0,
    val coeffOfTransFromWorkingToCombMass: Double = 0.0,
    val fuelDryMass: Fuel = Fuel(),
    val checkSum1: Double = 0.0,
    val fuelCombustionMass: Fuel = Fuel(),
    val checkSum2: Double = 0.0,
    val lowerHeatOfCombustion: Double = 0.0,
    val dryMass: Double = 0.0,
    val combustionMass: Double = 0.0,
)