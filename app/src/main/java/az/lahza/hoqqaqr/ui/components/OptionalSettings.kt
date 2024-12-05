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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.lahza.hoqqaqr.R

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
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.manrope_bold))
        )

        // Options list
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
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
