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
