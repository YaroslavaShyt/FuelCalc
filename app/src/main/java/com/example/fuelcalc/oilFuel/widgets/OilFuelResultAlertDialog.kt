package com.example.fuelcalc.oilFuel.widgets


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.fuelcalc.oilFuel.models.OilFuelResult


@Composable
fun OilFuelResultAlertDialog(onDismiss: () -> Unit, oilFuelResult: OilFuelResult) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = "Результат")
        },
        text = {
            Column {
                Text(text = "Склад робочої маси мазуту:")
                Text(
                    "H = ${oilFuelResult.workingOilFuel.H}, \n" +
                            "C = ${oilFuelResult.workingOilFuel.C}, \n" +
                            "S = ${oilFuelResult.workingOilFuel.S}, \n" +
                            "O = ${oilFuelResult.workingOilFuel.O}, \n" +
                            "V = ${oilFuelResult.workingOilFuel.V}, \n" +
                            "A = ${oilFuelResult.workingOilFuel.A}"
                )
                Text(text = "Нижча теплота згоряння мазуту: ${oilFuelResult.lowerHeatOfCombustion}")
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
