package com.example.fuelcalc.oilFuel


class OilFuelViewModel {
    val oilFuelControlExample: OilFuel = OilFuel(85.0, 11.2, 2.5, 0.8, 333.3, 2.0, 0.15, 40.40)
    val oilFuelVariantData: OilFuel = OilFuel(76.4, 1.5, 1.7, 1.3, 1.3, 13.3, 5.0,)

    fun count(oilFuel: OilFuel) : OilFuelResult{
        val C = oilFuel.C * countWorkingMassCombustible(oilFuel.W, oilFuel.A)
        val H = oilFuel.H * countWorkingMassCombustible(oilFuel.W, oilFuel.A)
        val O = oilFuel.O * countWorkingMassCombustible(oilFuel.W, oilFuel.A / 10)
        val S = oilFuel.S * countWorkingMassCombustible(oilFuel.W, oilFuel.A)
        val A = oilFuel.A * countWorkingMassDry(oilFuel.W)
        val V = oilFuel.V * countWorkingMassDry(oilFuel.W)

        val Q = countQ(oilFuel.Q, oilFuel.W, A)

        return OilFuelResult(
            OilFuel(
                C, H, S, O, V, oilFuel.W, A), Q
        )
    }

    private fun countWorkingMassDry(W: Double) : Double{
        return 100 / (100 - W)
    }

    private fun countWorkingMassCombustible(W: Double, A: Double) : Double{
        return (100 - W - A) / 100
    }

    private fun countQ(Q: Double, W: Double, A: Double) : Double{
        return Q * ((100 - W - A)/100) - 0.025 * W
    }
}