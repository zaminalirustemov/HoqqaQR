package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.lahza.hoqqaqr.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentInputField(content: String, onValueChange: (String) -> Unit) {
    // Define common text styles to avoid redundancy
    val titleTextStyle = TextStyle(
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.manrope_bold))
    )

    val labelTextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.manrope_medium))
    )

    Column {
        Text(
            text = stringResource(R.string.generate_qr_code_title),
            modifier = Modifier.padding(bottom = 24.dp),
            style = titleTextStyle,
        )

        Text(
            text = stringResource(R.string.content_label),
            modifier = Modifier.padding(bottom = 8.dp),
            style = labelTextStyle,
        )

        OutlinedTextField(
            value = content,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray, unfocusedBorderColor = Color.LightGray
            )
        )
    }
}
