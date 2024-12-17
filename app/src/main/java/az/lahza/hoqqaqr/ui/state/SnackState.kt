package az.lahza.hoqqaqr.ui.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SnackState {
    private val _message = mutableStateOf<String?>(null)
    val message: State<String?> = _message

    private val _showSnack = MutableStateFlow(false)
    val showSnack: StateFlow<Boolean> = _showSnack.asStateFlow()

    fun addMessage(message: String) {
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