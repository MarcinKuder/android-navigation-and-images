package pl.ansnt.navigationandimages.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun GridScreen(modifier: Modifier = Modifier, onImageClick: (Int) -> Unit) {
    TextButton (
        onClick = { onImageClick(1) },
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
    ) {
        Text("Details")
    }
}
