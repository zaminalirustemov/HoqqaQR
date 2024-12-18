package az.lahza.hoqqaqr.ui.components.snack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import az.lahza.hoqqaqr.ui.state.SnackState

/**
 * Provides a remembered instance of [SnackState].
 * This function ensures that [SnackState] is retained across recompositions,
 * making it suitable for managing the visibility and content of snack messages in the UI.
 *
 * @return A remembered [SnackState] instance.
 */
@Composable
fun rememberSnackState() = remember { SnackState() }