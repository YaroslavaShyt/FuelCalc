package com.example.fuelcalc.oilFuel

import com.example.fuelcalc.oilFuel.models.OilFuel
import com.example.fuelcalc.oilFuel.models.OilFuelResult


class OilFuelViewModel {
    val oilFuelControlExample = OilFuel(85.0, 11.2, 2.5, 0.8, 333.3, 2.0, 0.15, 40.40)
    val oilFuelVariantData = OilFuel(76.4, 1.5, 1.7, 1.3, 1.3, 13.3, 5.0)

    fun count(oilFuel: OilFuel): OilFuelResult {
        val workingMassCombustible = countWorkingMassCombustible(oilFuel.W, oilFuel.A)
        val workingMassDry = countWorkingMassDry(oilFuel.W)

        return OilFuelResult(
            workingOilFuel = OilFuel(
                C = oilFuel.C * workingMassCombustible,
                H = oilFuel.H * workingMassCombustible,
                O = oilFuel.O * countWorkingMassCombustible(oilFuel.W, oilFuel.A / 10),
                S = oilFuel.S * workingMassCombustible,
                V = oilFuel.V * workingMassDry,
                A = oilFuel.A * workingMassDry
            ),
            lowerHeatOfCombustion = countQ(oilFuel.Q, oilFuel.W, oilFuel.A)
        )
    }

    private fun countWorkingMassDry(W: Double) = (100 - W) / 100

    private fun countWorkingMassCombustible(W: Double, A: Double) = (100 - W - A) / 100

    private fun countQ(Q: Double, W: Double, A: Double) = Q * countWorkingMassCombustible(W, A) - 0.025 * W
}
