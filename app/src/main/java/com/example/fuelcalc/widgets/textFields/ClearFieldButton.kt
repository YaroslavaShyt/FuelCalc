package com.example.fuelcalc.widgets.textFields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fuelcalc.ui.theme.FuelCalcTheme

@Composable
fun ClearFieldButton(onClick: ()->Unit){
    Box(modifier = Modifier.padding(15.dp).border(BorderStroke(1.dp, Color.Transparent), shape = CircleShape)) {
        Box(
            modifier = Modifier
                .size(35.dp)
                .background(Color.Cyan.copy(alpha = 0.5f), CircleShape)
                .border(BorderStroke(1.dp, Color.LightGray), shape = CircleShape)
                .clickable(
                    onClick = {
                        onClick()
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "x",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 15.sp)
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewClearFieldButton() {
    FuelCalcTheme {
        ClearFieldButton {

        }
    }

}