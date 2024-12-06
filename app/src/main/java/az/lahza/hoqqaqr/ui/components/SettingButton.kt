package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import az.lahza.hoqqaqr.R

/**
 * A composable button component for displaying a selectable setting option.
 *
 * The button is styled with an outlined border that changes thickness based on whether the setting
 * is currently selected. When clicked, it triggers a callback function with the name of the setting.
 *
 * This component is typically used to present a list of configurable settings, allowing the user
 * to select or clear specific options dynamically.
 *
 * ### Features:
 * - Dynamically styled border based on selection state.
 * - Customizable content via the `setting` parameter.
 * - Callback mechanism (`onClearClick`) to handle click events, passing the selected setting name.
 *
 * @param setting The name of the setting to display on the button.
 * @param selectedSetting The currently selected setting. Used to apply different styling when
 *                        the button's `setting` matches the `selectedSetting`.
 * @param onClearClick A callback function invoked when the button is clicked, passing the `setting`
 *                     name as an argument.
 *
 * ### Example:
 * ```kotlin
 * SettingButton(
 *     setting = "Dot Color",
 *     selectedSetting = "Dot Color",
 *     onClearClick = { setting -> println("Cleared: $setting") }
 * )
 * ```
 */
@Composable
fun SettingButton(
    setting: String,
    selectedSetting: String?,
    onClearClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onClearClick(setting)
            },
            border = if (selectedSetting == setting) {
                BorderStroke(2.dp, Color.Gray)
            } else BorderStroke(1.dp, Color.LightGray),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = setting,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.manrope_medium))
            )
        }
    }
}
