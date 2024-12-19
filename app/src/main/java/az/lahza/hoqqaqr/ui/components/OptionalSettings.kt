package az.lahza.hoqqaqr.ui.components

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.ui.theme.Dimens
import kotlinx.coroutines.delay

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
    onClearClick: (String) -> Unit
) {
    val context = LocalContext.current
    val settingsList = getSettingsList(context)

    var isButtonEnabled by remember { mutableStateOf(true) }
    var triggerEffect by remember { mutableStateOf(false) }

    LaunchedEffect(triggerEffect) {
        if (triggerEffect) {
            delay(100)  // Simulate a delay for asynchronous operations
            isButtonEnabled = true  // Re-enable the button after delay
            triggerEffect = false  // Reset the trigger
        }
    }

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
            settingsList.forEach { setting ->
                val isClearSettings = setting == stringResource(R.string.clear_settings_label)

                AnimatedVisibility(
                    visible = !isClearSettings || isSettingsChanged,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    SettingButton(
                        setting = setting,
                        selectedSetting = selectedSetting,
                    ) {
                        if (isButtonEnabled) {
                            onClearClick(setting)
                            isButtonEnabled = false
                            triggerEffect = true
                        }
                    }
                }
            }
        }
    }
}

private fun getSettingsList(context: Context) = listOf(
    context.getString(R.string.dot_color_label),
    context.getString(R.string.background_color_label),
    context.getString(R.string.logo_label),
    context.getString(R.string.clear_settings_label)
)