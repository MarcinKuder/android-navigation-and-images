package pl.ansnt.navigationandimages.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(modifier: Modifier = Modifier, imageId: Int, onOpenURL: (String) -> Unit) {
    val imgWidth = 800
    val imgHeight = 600
    val url = "https://picsum.photos/seed/$imageId/$imgWidth/$imgHeight"

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(32.dp)
    ) {
        AsyncImage(
            model = url,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(imgWidth / imgHeight.toFloat())
                .clip(MaterialTheme.shapes.medium),
            contentDescription = "Random image",
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextButton ( onClick = {
            onOpenURL(url) }){
            Text("Open in browser")
        }
    }
}