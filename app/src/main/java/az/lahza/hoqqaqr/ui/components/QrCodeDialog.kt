package az.lahza.hoqqaqr.ui.components

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.utils.saveBitmapToGallery

/**
 * Displays a dialog with the generated QR code, and provides functionality to save the QR code to the device gallery.
 *
 * This composable shows a custom dialog (`CustomQrCodeDialog`) with the QR code image and two main actions:
 * - **Dismiss**: Closes the dialog.
 * - **Save to Gallery**: Attempts to save the QR code image to the device's gallery. A toast message is shown
 *   indicating whether the save operation was successful or not.
 *
 * The dialog is only shown when the `showQrCodeDialog` flag is `true` and the `bitmap` is not null.
 *
 *
 * ### Behavior:
 *
 * - If the `showQrCodeDialog` is `true` and the `bitmap` is not null, the `CustomQrCodeDialog` is shown.
 * - The user can click the "Save to Gallery" button, which triggers an attempt to save the bitmap to the gallery.
 * - If the bitmap is saved successfully, a toast message is shown to inform the user that the QR code has been saved.
 * - If the save operation fails, a toast message is shown indicating the failure.
 *
 * ### Example:
 * ```kotlin
 * QrCodeDialog(
 *    bitmap = generatedQrCodeBitmap,
 *    showQrCodeDialog = true,
 *    onDismiss = { /* Handle dialog dismiss */ }
 * )
 * ```
 *
 * @param bitmap The QR code image to display and save.
 * @param showQrCodeDialog Boolean flag to control visibility of the dialog.
 * @param onDismiss Lambda function to handle dismissing the dialog.
 *
 * @see CustomQrCodeDialog for the dialog UI.
 * @see saveBitmapToGallery for the gallery saving logic.
 *
 */
@Composable
fun QrCodeDialog(
    bitmap: Bitmap?,
    showQrCodeDialog: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    if (showQrCodeDialog && bitmap != null) {
        CustomQrCodeDialog(
            qrBitmap = bitmap,
            onDismiss = onDismiss,
            onSaveToGallery = {
                // Try saving the bitmap to the gallery and respond based on the result
                val isSaved =
                    saveBitmapToGallery(context, bitmap, context.getString(R.string.my_qr_code))
                val message =
                    if (isSaved) R.string.qr_code_saved_to_gallery else R.string.qr_code_save_failed

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        )
    }
}