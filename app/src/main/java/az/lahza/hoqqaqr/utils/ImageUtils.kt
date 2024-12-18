package az.lahza.hoqqaqr.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.constants.AppConstants
import az.lahza.hoqqaqr.constants.MimeTypes
import az.lahza.hoqqaqr.extensions.empty
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

/**
 * Saves the provided Bitmap to the device's gallery.
 *
 * On API 29 and later, the image is saved to MediaStore and accessed via URI.
 * On earlier versions, EXTERNAL_CONTENT_URI is used directly.
 *
 * @param context The context of the application.
 * @param bitmap The bitmap image of the QR code to be saved.
 * @param fileName The name of the file to be saved (optional). If not provided, a default name is used.
 * @return Returns `true` if the operation was successful, otherwise `false`.
 */
fun saveBitmapToGallery(
    context: Context, bitmap: Bitmap, fileName: String = String.empty()
): Boolean {
    val contentResolver = context.contentResolver
    val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    } else {
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }

    val fileNameToUse = fileName.ifEmpty {
        context.getString(R.string.default_file_name)
    }

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "$fileNameToUse.png")
        put(MediaStore.Images.Media.MIME_TYPE, MimeTypes.IMAGE_PNG)
        put(MediaStore.Images.Media.WIDTH, bitmap.width)
        put(MediaStore.Images.Media.HEIGHT, bitmap.height)
    }

    return try {
        val imageUri = contentResolver.insert(imageCollection, contentValues)
        imageUri?.let { uri ->
            val outputStream: OutputStream? = contentResolver.openOutputStream(uri)
            outputStream?.use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
            true
        } ?: false
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

}

/**
 * Shares the QR code image using an intent.
 *
 * The QR code bitmap is saved to a temporary file, and its URI is retrieved using FileProvider.
 * This URI is then shared via an intent, allowing the user to share the image. If any errors occur,
 * such as when the QR code bitmap is null or an exception happens during file handling, the onError
 * callback is invoked with an appropriate error message.
 *
 * @param qrBitmap The bitmap image of the QR code to be shared.
 * @param context The context of the application.
 * @param onError A callback function that is invoked when an error occurs. It receives a string
 *        parameter indicating the error message, which can be used to notify the user of the failure.
 */
fun shareQrCode(
    qrBitmap: Bitmap?,
    context: Context,
    onError: (String) -> Unit,
) {
    if (qrBitmap == null) {
        onError.invoke(context.getString(R.string.qr_code_invalid))
        return
    }

    try {
        // Prepare the file for saving the QR code image
        val file = File(context.cacheDir, AppConstants.QR_CODE_PNG)
        FileOutputStream(file).use { outputStream ->
            qrBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        }

        // Get the URI for the file using FileProvider
        val uri: Uri =
            FileProvider.getUriForFile(context, AppConstants.FILE_PROVIDER_AUTHORITY, file)

        // Create the sharing intent
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = MimeTypes.IMAGE_PNG
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, AppConstants.SHARE_SUBJECT)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }

        // Launch the chooser for sharing
        context.startActivity(Intent.createChooser(intent, AppConstants.SHARE_CHOOSER_TITLE))

    } catch (e: IOException) {
        e.printStackTrace()
        onError.invoke(context.getString(R.string.qr_code_share_failed))
    } catch (e: Exception) {
        // Handle any other unexpected exceptions
        e.printStackTrace()
        onError.invoke(context.getString(R.string.qr_code_share_failed))
    }
}