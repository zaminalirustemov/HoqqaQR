package az.lahza.hoqqaqr.ui.components.snack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import az.lahza.hoqqaqr.ui.state.SnackState


@Composable
fun rememberSnackState() = remember { SnackState() }