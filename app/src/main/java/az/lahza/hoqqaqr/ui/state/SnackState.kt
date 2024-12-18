package az.lahza.hoqqaqr.ui.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Manages the state for displaying snack messages within the application.
 *
 * This class encapsulates the logic necessary to control snack visibility and manage message content.
 * It uses a combination of mutable state and state flows to update and observe snack properties reactively.
 *
 * @property message The currently displayed message in the snack. It is nullable, allowing for no message.
 * @property showSnack A flow representing the visibility of the snack. It is observed to trigger UI updates.
 */
class SnackState {
    private val _message = mutableStateOf<String?>(null)
    val message: State<String?> = _message

    private val _showSnack = MutableStateFlow(false)
    val showSnack: StateFlow<Boolean> = _showSnack.asStateFlow()

    fun showSnack(message: String) {
        _message.value = message
        _showSnack.value = true
    }

    fun hideSnack() {
        _showSnack.value = false
    }
    fun isNotEmpty(): Boolean {
        return _message.value != null
    }
}