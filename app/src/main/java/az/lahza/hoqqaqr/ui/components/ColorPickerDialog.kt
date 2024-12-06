package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import az.lahza.hoqqaqr.R

/**
 * A customizable color picker dialog that allows users to select a color by adjusting
 * RGB (Red, Green, Blue) sliders. The selected color is dynamically displayed in the UI,
 * and the user can confirm their selection by clicking a button.
 *
 * The dialog appears as an overlay and allows for dismissing by tapping outside of it.
 * Once the user selects a color, the selected color is returned through the `onColorSelected` callback.
 *
 * This composable provides a simple and intuitive way to pick a color using a slider-based UI.
 *
 * @param initialColor The initial color displayed in the dialog. It determines the starting
 *                     values for the RGB sliders.
 * @param onDismiss A callback that is invoked when the dialog is dismissed (either by pressing
 *                  the close button or tapping outside).
 * @param onColorSelected A callback that is invoked when the user confirms their color selection.
 *                        The selected color is passed as a parameter.
 */
@Composable
fun ColorPickerDialog(
    initialColor: Color,
    onDismiss: () -> Unit,
    onColorSelected: (Color) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.select_color),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.manrope_bold)),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                val red = remember { mutableFloatStateOf(initialColor.red) }
                val green = remember { mutableFloatStateOf(initialColor.green) }
                val blue = remember { mutableFloatStateOf(initialColor.blue) }

                // Red Slider
                ColorSlider(
                    value = red.floatValue,
                    onValueChange = { red.floatValue = it },
                    color = Color(0xFFE10000),
                    label = stringResource(id = R.string.red_label)
                )

                // Green Slider
                ColorSlider(
                    value = green.floatValue,
                    onValueChange = { green.floatValue = it },
                    color = Color(0xFF00E100),
                    label = stringResource(id = R.string.green_label)
                )

                // Blue Slider
                ColorSlider(
                    value = blue.floatValue,
                    onValueChange = { blue.floatValue = it },
                    color = Color(0xFF0000E1),
                    label = stringResource(id = R.string.blue_label)
                )

                Spacer(modifier = Modifier.height(16.dp))

                val selectedColor = Color(red.floatValue, green.floatValue, blue.floatValue)

                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
                        .size(50.dp)
                        .background(selectedColor, RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onColorSelected(selectedColor)
                        onDismiss()
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D7DF2)),
                ) {
                    Text(text = stringResource(id = R.string.select_color), color = Color.White)
                }
            }
        }
    }
}