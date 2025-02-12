package az.lahza.hoqqaqr.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.ui.theme.ButtonWhite
import az.lahza.hoqqaqr.ui.theme.Dimens
import az.lahza.hoqqaqr.ui.theme.MainBlue
import az.lahza.hoqqaqr.utils.shareQrCode

/**
 * Displays a row of action buttons for saving and sharing a QR code.
 *
 * This composable displays two buttons side by side:
 * - **Save to Gallery**: Triggers the provided `onSaveToGallery` lambda when clicked. It allows the user to save the generated QR code to the device's gallery.
 * - **Share**: Initiates the sharing functionality for the QR code bitmap by invoking the `shareQrCode` function. The share button is clickable if the `qrBitmap` is non-null.
 *
 * The layout ensures that the buttons are properly spaced, with the "Save to Gallery" button taking more width compared to the "Share" button.
 *
 * @param onSaveToGallery A lambda function to be invoked when the "Save to Gallery" button is clicked. This function handles saving the QR code to the device's gallery.
 * @param qrBitmap The bitmap representation of the QR code that is shared when the "Share" button is clicked. The `qrBitmap` should not be null for the sharing action to be triggered.
 * @param onError A callback function that is invoked when an error occurs during the sharing process. It passes an error message as a parameter, which can be used to notify the user of any issues.
 */
@Composable
fun ActionButtons(
    onSaveToGallery: () -> Unit,
    qrBitmap: Bitmap?,
    onError: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onSaveToGallery() },
            modifier = Modifier.weight(0.6f),
            colors = ButtonDefaults.buttonColors(containerColor = MainBlue),
            shape = RoundedCornerShape(Dimens.ExtraLarge)
        ) {
            Text(
                text = stringResource(id = R.string.save_to_gallery),
                color = Color.White
            )
        }


        Spacer(modifier = Modifier.width(Dimens.MediumLarge))

        val context = LocalContext.current
        Button(
            onClick = {
                shareQrCode(
                    qrBitmap,
                    context,
                ) { onError.invoke(it) }
            },
            modifier = Modifier.weight(0.4f),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonWhite),
            shape = RoundedCornerShape(Dimens.ExtraLarge)
        ) {
            Text(text = stringResource(id = R.string.share), color = Color.Black)
        }
    }
}