package az.lahza.hoqqaqr.ui.components

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.utils.saveBitmapToGallery

/**
 * Displays a dialog with the generated QR code, and provides functionality to save the QR code to the device gallery.
 *
 * This composable shows a custom dialog (`CustomQrCodeDialog`) with the QR code image and two main actions:
 * - **Dismiss**: Closes the dialog.
 * - **Save to Gallery**: Attempts to save the QR code image to the device's gallery.
 *   Depending on the outcome of the save operation, appropriate toast messages are displayed to inform the user.
 *
 * The dialog is only shown when the `showQrCodeDialog` flag is `true` and the `bitmap` is not null.
 *
 * ### Behavior:
 * - If the `showQrCodeDialog` is `true` and the `bitmap` is not null, the `CustomQrCodeDialog` is shown.
 * - The user can click the "Save to Gallery" button, which triggers an attempt to save the bitmap to the gallery.
 * - On successful save, `onSuccess` is invoked with a success message, and a toast message is shown to the user.
 * - On failure to save, `onError` is invoked with an error message, and a toast message is shown to the user.
 *
 * ### Example:
 * ```kotlin
 * QrCodeDialog(
 *    bitmap = generatedQrCodeBitmap,
 *    showQrCodeDialog = true,
 *    onDismiss = { /* Handle dialog dismiss */ },
 *    onSuccess = { message -> /* Handle success outcome */ },
 *    onError = { message -> /* Handle error outcome */ }
 * )
 * ```
 *
 * @param bitmap The QR code image to display and save.
 * @param showQrCodeDialog Boolean flag to control visibility of the dialog.
 * @param onDismiss Lambda function to handle dismissing the dialog.
 * @param onSuccess Lambda function invoked when the save operation is successful. It passes a success message.
 * @param onError Lambda function invoked when the save operation fails. It passes an error message.
 *
 * @see CustomQrCodeDialog for the dialog UI.
 * @see saveBitmapToGallery for the gallery saving logic.
 */
@Composable
fun QrCodeDialog(
    bitmap: Bitmap?,
    showQrCodeDialog: Boolean,
    onDismiss: () -> Unit,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit,
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

                if (isSaved) onSuccess.invoke(context.getString(message))
                else onError.invoke(context.getString(message))
            },
            onError = onError
        )
    }
}