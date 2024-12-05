package az.lahza.hoqqaqr.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ColorPickerDialogs(
    showDotColorPickerDialog: Boolean,
    showBackgroundColorPickerDialog: Boolean,
    selectedDotColor: Color,
    selectedBackgroundColor: Color,
    onDotColorDismiss: () -> Unit,
    onBackgroundColorDismiss: () -> Unit,
    onDotColorSelected: (Color) -> Unit,
    onBackgroundColorSelected: (Color) -> Unit,
) {
    if (showDotColorPickerDialog) {
        ColorPickerDialog(
            initialColor = selectedDotColor,
            onDismiss = onDotColorDismiss,
            onColorSelected = onDotColorSelected
        )
    }

    if (showBackgroundColorPickerDialog) {
        ColorPickerDialog(
            initialColor = selectedBackgroundColor,
            onDismiss = onBackgroundColorDismiss,
            onColorSelected = onBackgroundColorSelected
        )
    }
}