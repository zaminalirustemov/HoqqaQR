package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import az.lahza.hoqqaqr.R

/**
 * A button component that displays a close icon.
 *
 * This composable provides a close button that, when clicked, triggers the provided
 * `onDismiss` lambda function. The button uses an icon (a close symbol) sourced
 * from the drawable resources and is fully accessible with a content description
 * for screen readers.
 *
 * The button is designed to be used in scenarios where a close action is needed,
 * such as closing a dialog or dismissing a modal.
 *
 * @param onDismiss A lambda function that is invoked when the close button is clicked.
 *                  Typically used to close or dismiss a UI element (e.g., dialog, sheet).
 */
@Composable
fun CloseButton(onDismiss: () -> Unit) {
    IconButton(onClick = onDismiss) {
        Image(
            painter = painterResource(id = R.drawable.ic_close_24),
            contentDescription = stringResource(id = R.string.close)
        )
    }
}