package az.lahza.hoqqaqr.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.utils.shareQrCode

@Composable
fun ActionButtons(
    onSaveToGallery: () -> Unit,
    qrBitmap: Bitmap?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onSaveToGallery() },
            modifier = Modifier.weight(0.6f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D7DF2)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.save_to_gallery),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        val context = LocalContext.current
        Button(
            onClick = { shareQrCode(qrBitmap, context) },
            modifier = Modifier.weight(0.4f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F2F5)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = stringResource(id = R.string.share), color = Color.Black)
        }
    }
}