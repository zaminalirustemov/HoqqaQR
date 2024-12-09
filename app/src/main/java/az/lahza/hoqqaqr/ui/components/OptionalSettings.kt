package az.lahza.hoqqaqr.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.ui.theme.Dimens

/**
 * A composable that displays a list of optional settings for the user to modify.
 *
 * The component shows a title ("Optional Settings") and then dynamically displays a list of options,
 * including dot color, background color, logo, and a clear settings option. The visibility of the
 * options changes based on whether a setting has been modified.
 *
 * - The `Clear Settings` option is always visible if the user has made changes.
 * - The other options are only visible if the settings have been modified or when the list is first shown.
 *
 * The settings are presented as buttons, which trigger a callback when clicked.
 * The callback provides the specific setting that was selected or cleared.
 *
 * @param isSettingsChanged Boolean flag to track if any settings have been modified.
 * @param selectedSetting The currently selected setting, or `null` if no setting is selected.
 * @param onClearClick A callback function that is triggered when a setting is selected, passing the
 *                     setting name as a string.
 *
 * @see SettingButton for how the individual buttons are displayed.
 *
 * @note The `onClearClick` callback accepts a `String` representing the setting name for flexibility
 *       and to handle different settings dynamically.
 */
@Composable
fun OptionalSettings(
    isSettingsChanged: Boolean,
    selectedSetting: String?,
    onClearClick: (String) -> Unit  // Callback that now accepts a String parameter
) {
    Column {

        // Title text
        Text(
            text = stringResource(R.string.optional_settings),
            modifier = Modifier.padding(bottom = Dimens.MediumLarge),
            fontSize = Dimens._18SP,
            fontFamily = FontFamily(Font(R.font.manrope_bold))
        )

        // Options list
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.ExtraLarge),
        ) {
            listOf(
                stringResource(R.string.dot_color_label),
                stringResource(R.string.background_color_label),
                stringResource(R.string.logo_label),
                stringResource(R.string.clear_settings_label)
            ).forEach { setting ->
                val isClearSettings = setting == stringResource(R.string.clear_settings_label)

                AnimatedVisibility(
                    visible = !isClearSettings || isSettingsChanged,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    SettingButton(
                        setting = setting,
                        selectedSetting = selectedSetting,
                    ) { onClearClick(setting) }
                }
            }
        }
    }
}
