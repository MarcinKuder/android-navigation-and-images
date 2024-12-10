package pl.ansnt.navigationandimages.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun GridScreen(modifier: Modifier = Modifier, onImageClick: (Int) -> Unit) {
    val imgWidth = 800
    val imgHeight = 600
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(32) { imageId ->
            val imgUrl = "https://picsum.photos/seed/$imageId/$imgWidth/$imgHeight"
            TextButton (
                onClick = { onImageClick(imageId) },
                shape = RectangleShape,
                contentPadding = PaddingValues(0.dp),
            ) {
                AsyncImage(
                    model = imgUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(imgWidth / imgHeight.toFloat())
                        .wrapContentHeight()
                )
            }
        }
    }
}
