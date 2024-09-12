package com.example.fuelcalc.fuel


class FuelViewModel {
    val fuelControlExample: Fuel = Fuel(21.1, 1.9, 2.6, 7.1, 0.2, 14.1, 53.0,)
    val fuelVariantData: Fuel = Fuel(76.4, 1.5, 1.7, 1.3, 1.3, 13.3, 5.0,)


    fun countResult(
        fuel: Fuel
    ) : FuelResult {
        val coeffOfTransFromWorkingToDryMass = countCoefficientOfTransFromWorkingToDryMass(fuel.W)
        val coeffOfTransFromWorkingToCombMass = countCoefficientOfTransFromWorkingToCombustibleMass(fuel.W, fuel.A)

        val H_c = multiplyComponentsByCoeffOfTransFromWorkingToDryMass(fuel.H, coeffOfTransFromWorkingToDryMass)
        val C_c = multiplyComponentsByCoeffOfTransFromWorkingToDryMass(fuel.C, coeffOfTransFromWorkingToDryMass)
        val S_c = multiplyComponentsByCoeffOfTransFromWorkingToDryMass(fuel.S, coeffOfTransFromWorkingToDryMass)
        val N_c = multiplyComponentsByCoeffOfTransFromWorkingToDryMass(fuel.N, coeffOfTransFromWorkingToDryMass)
        val O_c = multiplyComponentsByCoeffOfTransFromWorkingToDryMass(fuel.O, coeffOfTransFromWorkingToDryMass)
        val A_c = multiplyComponentsByCoeffOfTransFromWorkingToDryMass(fuel.A, coeffOfTransFromWorkingToDryMass)
        val checkSum1 = H_c + C_c + S_c + N_c + O_c + A_c

        val H_b = multiplyComponentsByCoeffOfTransFromWorkingToCombMass(fuel.H, coeffOfTransFromWorkingToCombMass)
        val C_b = multiplyComponentsByCoeffOfTransFromWorkingToCombMass(fuel.C, coeffOfTransFromWorkingToCombMass)
        val S_b = multiplyComponentsByCoeffOfTransFromWorkingToCombMass(fuel.S, coeffOfTransFromWorkingToCombMass)
        val N_b = multiplyComponentsByCoeffOfTransFromWorkingToCombMass(fuel.N, coeffOfTransFromWorkingToCombMass)
        val O_b = multiplyComponentsByCoeffOfTransFromWorkingToCombMass(fuel.O, coeffOfTransFromWorkingToCombMass)

        val checkSum2 = H_b + C_b + S_b + N_b + O_b

        val lowerHeatOfCombustion = countLowerHeatOfCombustion(fuel.C, fuel.H, fuel.O, fuel.S, fuel.W,) / 1000

        val dryMass = countDryMass(lowerHeatOfCombustion, fuel.W, coeffOfTransFromWorkingToDryMass)
        val combustionMass = countCombustibleMass(lowerHeatOfCombustion, fuel.W, coeffOfTransFromWorkingToCombMass )

        return FuelResult(coeffOfTransFromWorkingToDryMass,
            coeffOfTransFromWorkingToCombMass,
            Fuel(C_c, H_c, S_c, O_c, N_c, A_c),
            checkSum1,
            Fuel(C_b, H_b, S_b, O_b, N_b,),
            checkSum2,
            lowerHeatOfCombustion ,
            dryMass,
            combustionMass
            )

    }


    private fun countCoefficientOfTransFromWorkingToDryMass(W: Double): Double {
        return 100 / (100 - W)
    }

    private fun countCoefficientOfTransFromWorkingToCombustibleMass(W: Double, A: Double): Double {
        return 100 / (100 - W - A)

    }

    private fun multiplyComponentsByCoeffOfTransFromWorkingToDryMass(component: Double, coefficientOfTransFromWorkingToDryMass: Double): Double {
        return component * coefficientOfTransFromWorkingToDryMass
    }

    private fun multiplyComponentsByCoeffOfTransFromWorkingToCombMass(component: Double, coeffOfTransFromWorkingToCombMass: Double): Double {
        return component * coeffOfTransFromWorkingToCombMass
    }

    private fun countLowerHeatOfCombustion(C: Double, H: Double, O: Double, S: Double, W: Double): Double {
        return 339 * C + 1030 * H - 108.8 * (O - S) - 25 * W
    }

    private fun countDryMass(lowerHeatOfCombustion: Double, W: Double, coefficientOfTransFromWorkingToDryMass: Double): Double {
        return (lowerHeatOfCombustion + 0.025 * W) * coefficientOfTransFromWorkingToDryMass
    }

    private fun countCombustibleMass(lowerHeatOfCombustion: Double, W: Double, coefficientOfTransFromWorkingToConsumbMass: Double): Double {
        return (lowerHeatOfCombustion + 0.025 * W) * coefficientOfTransFromWorkingToConsumbMass
    }



}