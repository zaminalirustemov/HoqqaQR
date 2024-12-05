package az.lahza.hoqqaqr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import az.lahza.hoqqaqr.R

@Composable
fun CloseButton(onDismiss: () -> Unit) {
    IconButton(onClick = onDismiss) {
        Image(
            painter = painterResource(id = R.drawable.ic_close_24),
            contentDescription = stringResource(id = R.string.close)
        )
    }
}