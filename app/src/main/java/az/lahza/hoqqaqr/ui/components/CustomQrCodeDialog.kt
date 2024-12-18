package az.lahza.hoqqaqr.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.window.Dialog
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.ui.theme.Dimens

/**
 * A composable function that displays a dialog containing a generated QR code,
 * with options to save the QR code to the gallery or share it.
 *
 * The dialog includes:
 * - A close button to dismiss the dialog.
 * - A title ("QR Code") displayed above the QR code.
 * - The QR code image, which is provided as a [Bitmap].
 * - Action buttons that allow saving the QR code to the gallery or sharing it, with error handling.
 *
 * @param qrBitmap The generated QR code image as a [Bitmap] to be displayed in the dialog.
 *        This is nullable; if null, appropriate error handling should be triggered.
 * @param onDismiss A lambda function that is triggered when the dialog is dismissed.
 * @param onSaveToGallery A lambda function that is triggered when the "Save to Gallery" button is clicked.
 * @param onError A lambda function that is triggered when an error occurs during the sharing or saving process.
 *        It passes an error message as a string, which can be used to notify the user of the issue.
 */
@Composable
fun CustomQrCodeDialog(
    qrBitmap: Bitmap?,
    onDismiss: () -> Unit,
    onSaveToGallery: () -> Unit,
    onError: (String) -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens._16DP)
                .background(Color.White, shape = RoundedCornerShape(Dimens.ExtraLarge))
        ) {
            Column(
                modifier = Modifier.padding(Dimens._16DP),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CloseButton { onDismiss() }
                    }

                    Text(
                        text = stringResource(id = R.string.qr_code),
                        fontSize = Dimens._18SP,
                        fontFamily = FontFamily(Font(R.font.manrope_bold))
                    )
                }

                qrBitmap?.asImageBitmap()?.let {
                    Image(
                        bitmap = it,
                        contentDescription = stringResource(id = R.string.qr_code),
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(Dimens._16DP)
                    )
                }

                Spacer(modifier = Modifier.height(Dimens._16DP))

                // Buttons (Save to Gallery and Share)
                ActionButtons(
                    onSaveToGallery = onSaveToGallery,
                    qrBitmap = qrBitmap,
                    onError = { message ->
                        onError.invoke(message)
                    }
                )
            }
        }
    }
}