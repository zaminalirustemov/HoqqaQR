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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.ui.state.SnackState
import az.lahza.hoqqaqr.ui.theme.Dimens
import az.lahza.hoqqaqr.ui.theme.MainBlue
import kotlinx.coroutines.delay

/**
 * Displays a custom snack bar with configurable properties and animations.
 *
 * This composable function provides a customizable snack bar that appears over
 * the existing content. It supports custom entry and exit animations and allows
 * adjustment of its appearance through parameters such as padding and colors.
 * The snack visibility is managed by [SnackState], and it automatically hides
 * after a specified duration.
 *
 * @param state The [SnackState] that controls the visibility and content of the snack.
 * @param duration The duration in milliseconds for which the snack should remain visible before hiding.
 * @param containerColor The background color of the snack container.
 * @param contentColor The color of the text content inside the snack.
 * @param verticalPadding The vertical padding applied inside the snack.
 * @param horizontalPadding The horizontal padding applied inside the snack.
 * @param enterAnimation The animation played when the snack becomes visible.
 * @param exitAnimation The animation played when the snack disappears.
 */
@Composable
fun HoqqaSnack(
    state: SnackState,
    duration: Long = 3000L,
    containerColor: Color = MainBlue,
    contentColor: Color = Color.White,
    verticalPadding: Dp = Dimens.ExtraLarge,
    horizontalPadding: Dp = Dimens.ExtraLarge,
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(delayMillis = 300),
        expandFrom = Alignment.Top
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(delayMillis = 300),
        shrinkTowards = Alignment.Top
    )
) {
    val isShowingSnack by state.showSnack.collectAsState()

    // Handle the snack visibility with a side effect
    LaunchedEffect(isShowingSnack) {
        if (isShowingSnack) {
            delay(duration)
            state.hideSnack()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = state.isNotEmpty() && isShowingSnack,
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

/**
 * A basic composable function that constructs the UI for a snack message.
 *
 * This function creates a [Surface] that holds a [Text] composable for displaying
 * a message. It uses a [Row] to align the text properly within the padded area.
 * The text can display a fallback message if none is provided. The function allows
 * customization of the snack's appearance via its parameters.
 *
 * @param message The message to display inside the snack. If null, a default message
 *                from resources (R.string.no_message_provided) is used.
 * @param containerColor The color of the snack's background container.
 * @param contentColor The color of the text displayed in the snack.
 * @param verticalPadding The vertical padding applied within the snack container.
 * @param horizontalPadding The horizontal padding applied within the snack container.
 */
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
                .padding(vertical = verticalPadding)
                .padding(horizontal = horizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = message ?: stringResource(R.string.no_message_provided),
                color = contentColor,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = Dimens.ExtraLarge),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}