package com.example.fuelcalc.fuel.widgets


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.fuelcalc.fuel.models.FuelResult

@Composable
fun FuelResultAlertDialog(onDismiss: () -> Unit, fuelResult: FuelResult) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = "Результат")
        },
        text = {
            Column {
                Text("Коефіцієнт переходу від робочої до сухої маси: ${fuelResult.coeffOfTransFromWorkingToDryMass}")
                Text("Коефіцієнт переходу від робочої до горючої маси:  ${fuelResult.coeffOfTransFromWorkingToCombMass}")
                Text("Склад сухої маси палива: ")
                Text(
                    "H = ${fuelResult.fuelDryMass.H}, \n" +
                            "C = ${fuelResult.fuelDryMass.C}, \n" +
                            "S = ${fuelResult.fuelDryMass.S}, \n" +
                            "N = ${fuelResult.fuelDryMass.N}, \n" +
                            "O = ${fuelResult.fuelDryMass.O}, \n" +
                            "A = ${fuelResult.fuelDryMass.A}"
                )
                Text("Склад горючої маси палива: ")
                Text(
                    "H = ${fuelResult.fuelCombustionMass.H}, \n" +
                            "C = ${fuelResult.fuelCombustionMass.C}, \n" +
                            "S = ${fuelResult.fuelCombustionMass.S}, \n" +
                            "N = ${fuelResult.fuelCombustionMass.N}, \n" +
                            "O = ${fuelResult.fuelCombustionMass.O}"
                )
                Text("Нижча теплота згоряння для робочої маси: ${fuelResult.lowerHeatOfCombustion}")
                Text("Нижча теплота згоряння для сухої маси:${fuelResult.dryMass} ")
                Text("Нижча теплота згоряння для горючої маси: ${fuelResult.combustionMass}")
            }

        },
        confirmButton = {
            Button(
                colors = ButtonColors(
                    containerColor = Color.Cyan.copy(alpha = 0.5f),
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.Cyan.copy(alpha = 0.5f),
                ),
                onClick = onDismiss
            ) {
                Text("OK")
            }
        },
    )

}
