package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

/**
 * A composable that displays a slider for adjusting RGB color components,
 * with real-time value updates displayed above the slider.
 *
 * This slider allows users to adjust individual color components (Red, Green, or Blue),
 * with the thumb and active track color reflecting the selected component. The value is
 * shown between 0 and 255, representing the intensity of the color component.
 *
 * @param value The current value of the color component (0f to 1f range).
 * @param onValueChange Lambda to handle changes in the slider value.
 * @param color The color for the slider's thumb and active track.
 * @param label The label indicating the color component (e.g., "Red", "Green", "Blue").
 */
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