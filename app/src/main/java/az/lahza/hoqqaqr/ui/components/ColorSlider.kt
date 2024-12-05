package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ColorSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    color: Color,
    label: String
) {
    // Label Text for the Slider
    Text(
        text = "$label: ${(value * 255).toInt()}",
        fontSize = 14.sp
    )

    // Slider Component for Color Adjustment
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 0f..1f,
        modifier = Modifier.fillMaxWidth(),
        colors = SliderDefaults.colors(
            thumbColor = color,
            activeTrackColor = color,
            inactiveTrackColor = Color.LightGray,
        )
    )
}