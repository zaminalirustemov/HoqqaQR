package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.lahza.hoqqaqr.R

/**
 * A composable button that triggers the QR code generation process when clicked.
 *
 * This button is styled with a rounded corner and has a blue background with white text.
 * It expands to fill the width of its container and has a fixed height.
 *
 * @param modifier Modifier to customize the button's appearance or layout.
 * @param onClick Lambda function to handle the button's click event, typically for triggering QR code generation.
 */
@Composable
fun GenerateQrButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0D7DF2),
                contentColor = Color.White,
                disabledContainerColor = Color(0x800D7DF2),
                disabledContentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.generate_qr_code_title)
            )
        }
    }
}