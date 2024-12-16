package az.lahza.hoqqaqr.ui.components.snack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import az.lahza.hoqqaqr.ui.state.SnackState
import kotlinx.coroutines.delay


@Composable
fun HoqqaSnack(
    state: SnackState,
    duration: Long = 3000L,
    containerColor: Color = Color(0xFF0D7DF2),
    contentColor: Color = Color.White,
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 12.dp,
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(delayMillis = 300),
        expandFrom = Alignment.Top
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(delayMillis = 300),
        shrinkTowards = Alignment.Top
    )
) {
    val showSnack by state.showSnack.collectAsState()
    if (showSnack) {
        LaunchedEffect(key1 = state) {
            delay(duration)
            state.hideSnack()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = state.isNotEmpty() && showSnack,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            Snack(
                message = state.message.value,
                containerColor = containerColor,
                contentColor = contentColor,
                verticalPadding = verticalPadding,
                horizontalPadding = horizontalPadding
            )
        }
    }
}


@Composable
fun Snack(
    message: String?,
    containerColor: Color,
    contentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        color = containerColor,
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .padding(all = verticalPadding)
                .padding(horizontal = horizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = message ?: "???",
                color = contentColor,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

