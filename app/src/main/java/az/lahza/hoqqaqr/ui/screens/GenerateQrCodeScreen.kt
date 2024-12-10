package az.lahza.hoqqaqr.ui.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.constants.MimeTypes
import az.lahza.hoqqaqr.extensions.empty
import az.lahza.hoqqaqr.ui.components.ColorPickerDialogs
import az.lahza.hoqqaqr.ui.components.ContentInputField
import az.lahza.hoqqaqr.ui.components.GenerateQrButton
import az.lahza.hoqqaqr.ui.components.Header
import az.lahza.hoqqaqr.ui.components.OptionalSettings
import az.lahza.hoqqaqr.ui.components.QrCodeDialog
import az.lahza.hoqqaqr.ui.theme.Dimens
import az.lahza.hoqqaqr.utils.generateQRCode

/**
 * A composable screen for generating QR codes with customizable options, such as dot color,
 * background color, and logo inclusion.
 *
 * This screen provides an input field for content, options for customizing the QR code's appearance,
 * and a button for generating the QR code. The screen also includes color pickers for customizing the
 * dot and background colors, and an option to add a logo. Once the QR code is generated, it can be
 * previewed in a dialog and saved to the gallery.
 *
 * ### Features:
 * - Allows users to input content for the QR code.
 * - Provides customizable options to adjust dot color, background color, and logo.
 * - Displays a preview of the generated QR code in a dialog.
 * - Saves the generated QR code to the gallery.
 *
 * ### State Management:
 * The screen maintains various state variables to track the content, colors, logo, and QR code state:
 * - `content`: The input text for generating the QR code.
 * - `dotColor`: The color of the QR code's dots.
 * - `backgroundColor`: The background color of the QR code.
 * - `logoBitmap`: The optional logo image to be included in the QR code.
 * - `selectedSetting`: Tracks the currently selected setting for customization.
 * - `isSettingsChanged`: Boolean indicating whether the settings have been modified.
 * - `showQrCodeDialog`: Controls the visibility of the QR code preview dialog.
 * - `bitmap`: Holds the generated QR code bitmap.
 * - `isClearSettingsClicked`: Boolean indicating if the settings have been reset.
 *
 * ### Color Pickers:
 * - Provides separate dialogs for selecting dot color and background color using `ColorPickerDialogs`.
 *
 * ### Usage:
 * ```kotlin
 * GenerateQrCodeScreen(innerPadding = PaddingValues())
 * ```
 *
 * @param innerPadding Padding values for the inner content of the screen.
 */
@Composable
fun GenerateQrCodeScreen(innerPadding: PaddingValues) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    // State management
    var content by remember { mutableStateOf(String.empty()) }
    var dotColor by remember { mutableStateOf(Color.Black) }
    var backgroundColor by remember { mutableStateOf(Color.White) }
    var logoBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var selectedSetting by remember { mutableStateOf<String?>(null) }
    var isSettingsChanged by remember { mutableStateOf(false) }
    var showDotColorPickerDialog by remember { mutableStateOf(false) }
    var showBackgroundColorPickerDialog by remember { mutableStateOf(false) }
    var showQrCodeDialog by remember { mutableStateOf(false) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isClearSettingsClicked by remember { mutableStateOf(false) }
    var selectedDotColor by remember { mutableStateOf(dotColor) }
    var selectedBackgroundColor by remember { mutableStateOf(backgroundColor) }

    // Launchers for handling image selection
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            logoBitmap = BitmapFactory.decodeStream(inputStream)
        }
    }

    // Check if settings changed
    isSettingsChanged =
        dotColor != Color.Black || backgroundColor != Color.White || logoBitmap != null

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .pointerInput(Unit) {
            detectTapGestures(onTap = { focusManager.clearFocus() })
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens._16DP)
        ) {
            Header()
            ContentInputField(content, onValueChange = { content = it })
            Spacer(modifier = Modifier.height(Dimens._20DP))
            OptionalSettings(
                isSettingsChanged = isSettingsChanged,
                selectedSetting = selectedSetting,
                onClearClick = { setting ->
                    when (setting) {
                        context.getString(R.string.dot_color_label) ->
                            showDotColorPickerDialog = true

                        context.getString(R.string.background_color_label) ->
                            showBackgroundColorPickerDialog = true

                        context.getString(R.string.logo_label) ->
                            launcher.launch(MimeTypes.IMAGE)

                        context.getString(R.string.clear_settings_label) -> {
                            dotColor = Color.Black
                            selectedDotColor = Color.Black
                            backgroundColor = Color.White
                            selectedBackgroundColor = Color.White
                            logoBitmap = null
                            isClearSettingsClicked = true
                            isSettingsChanged = false
                        }
                    }

                    selectedSetting = null
                }
            )
        }

        GenerateQrButton(
            modifier = Modifier
                .padding(horizontal = Dimens._16DP, vertical = Dimens._32DP)
                .align(Alignment.BottomCenter),
            onClick = {
                if (content.isEmpty()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.enter_content_toast),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    bitmap = generateQRCode(
                        content, dotColor, backgroundColor, logo = logoBitmap
                    )
                    showQrCodeDialog = true
                }
            }
        )
    }

    // Color picker dialogs
    ColorPickerDialogs(
        showDotColorPickerDialog = showDotColorPickerDialog,
        showBackgroundColorPickerDialog = showBackgroundColorPickerDialog,
        selectedDotColor = selectedDotColor,
        selectedBackgroundColor = selectedBackgroundColor,
        onDotColorDismiss = { showDotColorPickerDialog = false },
        onBackgroundColorDismiss = { showBackgroundColorPickerDialog = false },
        onDotColorSelected = { selectedColor ->
            dotColor = selectedColor
            selectedDotColor = selectedColor
        },
        onBackgroundColorSelected = { selectedColor ->
            backgroundColor = selectedColor
            selectedBackgroundColor = selectedColor
        })

    QrCodeDialog(
        bitmap = bitmap,
        showQrCodeDialog = showQrCodeDialog,
        onDismiss = { showQrCodeDialog = false }
    )
}
