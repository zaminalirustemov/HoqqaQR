package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.ui.theme.Dimens

/**
 * A composable that displays a header with a title, typically used in the main screen of the app.
 *
 * This component aligns the header text to the left, applies a custom font style, and adds some padding below.
 * The title is read from the string resources.
 *
 *
 * @see stringResource for getting the text from the `R.string.qr_code_title`.
 * @see FontFamily for custom font handling (using the 'manrope_medium' font).
 */
@Composable
fun Header() {
    // Define the common text style
    val headerTextStyle = TextStyle(
        fontSize = Dimens._22SP,
        fontFamily = FontFamily(Font(R.font.manrope_medium))
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimens._16DP),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.qr_code_title),
            style = headerTextStyle
        )
    }
}