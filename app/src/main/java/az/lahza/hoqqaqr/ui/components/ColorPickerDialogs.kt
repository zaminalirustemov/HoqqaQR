package az.lahza.hoqqaqr.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * This composable manages the visibility and color selection logic for two distinct dialogs:
 * one for selecting the dot color and another for selecting the background color.
 * The dialogs are shown or hidden based on the corresponding boolean flags, and the selected
 * colors are passed to the parent via callback functions for further processing or UI updates.
 *
 * @param showDotColorPickerDialog A boolean flag that determines whether the dot color picker dialog
 *                                 should be shown. If `true`, the dot color picker dialog is displayed.
 * @param showBackgroundColorPickerDialog A boolean flag that determines whether the background color
 *                                        picker dialog should be shown. If `true`, the background color
 *                                        picker dialog is displayed.
 * @param selectedDotColor The currently selected color for the dot. This color is used as the initial
 *                         color in the dot color picker dialog.
 * @param selectedBackgroundColor The currently selected color for the background. This color is used
 *                                as the initial color in the background color picker dialog.
 * @param onDotColorDismiss A callback that is invoked when the dot color picker dialog is dismissed.
 *                          Typically used to hide the dialog when the user cancels or confirms an action.
 * @param onBackgroundColorDismiss A callback that is invoked when the background color picker dialog
 *                                 is dismissed. Used to close the dialog when the user cancels or confirms.
 * @param onDotColorSelected A callback invoked when the user selects a new color for the dot. The
 *                           selected color is passed as an argument to the callback.
 * @param onBackgroundColorSelected A callback invoked when the user selects a new color for the background.
 *                                  The selected color is passed as an argument to the callback.
 *
 * @see ColorPickerDialog for details on how individual color picker dialogs are constructed.
 *
 */
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