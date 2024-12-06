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

/**
 * A composable function that renders a labeled text input field for content entry.
 * The field is accompanied by a title and label, providing a structured UI for text input.
 *
 * This composable is designed to create a clean and intuitive content input area
 * with customizable text. It includes a title, label, and a large editable text field
 * with a rounded border. Ideal for cases where user input is needed for generating content,
 * such as QR codes.
 *
 * @param content The current value of the input field.
 * @param onValueChange Lambda function that is called when the input field value changes.
 *                     It updates the parent state with the new content.
 */
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
