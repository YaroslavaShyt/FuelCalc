package com.example.fuelcalc.fuel

import com.example.fuelcalc.fuel.models.Fuel
import com.example.fuelcalc.fuel.models.FuelResult


class FuelViewModel {
    val fuelControlExample = Fuel(21.1, 1.9, 2.6, 7.1, 0.2, 14.1, 53.0)
    val fuelVariantData = Fuel(76.4, 1.5, 1.7, 1.3, 1.3, 13.3, 5.0)

    fun countResult(fuel: Fuel): FuelResult {
        val dryMassCoeff = countDryMassCoeff(fuel.W)
        val combMassCoeff = countCombMassCoeff(fuel.W, fuel.A)

        val dryComponents = applyCoeffToComponents(fuel, dryMassCoeff)
        val combComponents = applyCoeffToComponents(fuel, combMassCoeff)

        val dryCheckSum = dryComponents.H + dryComponents.C + dryComponents.S +
                dryComponents.N + dryComponents.O + dryComponents.A

        val combCheckSum = combComponents.H + combComponents.C + combComponents.S +
                combComponents.N + combComponents.O

        val lowerHeatCombustion = countLowerHeatOfCombustion(fuel) / 1000
        val dryMass = countAdjustedMass(lowerHeatCombustion, fuel.W, dryMassCoeff)
        val combustionMass = countAdjustedMass(lowerHeatCombustion, fuel.W, combMassCoeff)

        return FuelResult(
            dryMassCoeff,
            combMassCoeff,
            Fuel(
                dryComponents.C,
                dryComponents.H,
                dryComponents.S,
                dryComponents.O,
                dryComponents.N,
                dryComponents.A,
            ),
            dryCheckSum,
            Fuel(
                combComponents.C,
                combComponents.H,
                combComponents.S,
                combComponents.O,
                combComponents.N,
            ),
            combCheckSum,
            lowerHeatCombustion,
            dryMass,
            combustionMass
        )
    }

    private fun countDryMassCoeff(W: Double) = 100 / (100 - W)

    private fun countCombMassCoeff(W: Double, A: Double) = 100 / (100 - W - A)

    private fun applyCoeffToComponents(fuel: Fuel, coeff: Double) = Fuel(
        fuel.C * coeff,
        fuel.H * coeff,
        fuel.S * coeff,
        fuel.N * coeff,
        fuel.O * coeff,
        fuel.A * coeff
    )

    private fun countLowerHeatOfCombustion(fuel: Fuel): Double {
        return 339 * fuel.C + 1030 * fuel.H - 108.8 * (fuel.O - fuel.S) - 25 * fuel.W
    }

    private fun countAdjustedMass(lowerHeatCombustion: Double, W: Double, coeff: Double): Double {
        return (lowerHeatCombustion + 0.025 * W) * coeff
    }
}

